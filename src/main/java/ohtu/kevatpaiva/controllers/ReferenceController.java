package ohtu.kevatpaiva.controllers;

import java.util.ArrayList;
import java.util.List;
import ohtu.kevatpaiva.Article;
import ohtu.kevatpaiva.tallennus.ArtikkelinTallentaja;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author danielli
 */
@Controller
@RequestMapping("/")
public class ReferenceController {
    private ArtikkelinTallentaja tallentaja;

    public ReferenceController() {
        this.tallentaja = new ArtikkelinTallentaja();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String naytaPaasivu() {

        return "start";
    }
    
    @RequestMapping(value="lomake", method = RequestMethod.GET)
    public String naytaLomake() {

        return "form-article";
    }

    @RequestMapping(value="lisaa", method = RequestMethod.POST)
    public String lisaaArtikkeli(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam String id,
            @RequestParam String author,
            @RequestParam String title,
            @RequestParam(value = "journal", required = false) String journal, // (journal) NOT REQUIRED?
            @RequestParam String year,
            @RequestParam(value = "volume", required = false) String volume,
            @RequestParam(value = "number", required = false) String number,
            @RequestParam(value = "pages", required = false) String pages,
            @RequestParam(value = "publisher", required = false) String publisher,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "booktitle", required = false) String booktitle,
            @RequestParam(value = "editor", required = false) String editor,
            @RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "note", required = false) String note,
            @RequestParam(value = "edition", required = false) String edition,
            @RequestParam(value = "series", required = false) String series,
            ModelMap model) {
        
        ArrayList<String> viestit = new ArrayList<String>();
        boolean idOnJo = tallentaja.onkoArtikkeli(id);
        
        if (idOnJo || id.equals("") || type == null||author.equals("") || title.equals("") || year.equals("") 
                || (type.equals("article") && journal.equals(""))
                || (type.equals("book") && (editor.equals("") || publisher.equals(""))) 
                || (type.equals("inproceedings") && booktitle.equals(""))) {
            model.addAttribute("type", type);
            model.addAttribute("id", id);
            model.addAttribute("author", author);
            model.addAttribute("title", title);
            model.addAttribute("journal", journal);
            model.addAttribute("year", year);
            model.addAttribute("number", number);
            model.addAttribute("pages", pages);
            model.addAttribute("publisher", publisher);
            model.addAttribute("address", address);
            model.addAttribute("booktitle", booktitle);
            model.addAttribute("editor", editor);
            model.addAttribute("month", month);
            model.addAttribute("key", key);
            model.addAttribute("note", note);
            model.addAttribute("edition", edition);
            model.addAttribute("series", series);
            
            String message = "";
            if (type == null) {
                message = "Tyyppi pitää olla valittuna";
                viestit.add(message);
            }
            else {
                if (idOnJo) {
                    message = "Viite kyseisellä id:llä on jo tallennettu";
                    viestit.add(message);
                }
                if (id.equals("")) {
                    message = "Id on pakollinen kenttä";
                    viestit.add(message);
                }
                if (author.equals("")) {
                    message = "Kirjoittaja on pakollinen kenttä";
                    viestit.add(message);
                }
                if (title.equals("")) {
                    message = "Otsikko on pakollinen kenttä";
                    viestit.add(message);
                }
                if (year.equals("")) {
                    message = "Vuosi on pakollinen kenttä";
                    viestit.add(message);
                }
                if (type.equals("article") && journal.equals("")) {
                    message = "Journaali on pakollinen kenttä artikkeli-tyyppisessä viitteessä";
                    viestit.add(message);
                }
                if (type.equals("book") && (editor.equals("") || publisher.equals(""))) {
                    message = "Ediittori ja julkaisija ovat pakollisia kenttiä kirja-tyyppisessä viittessä";
                    viestit.add(message);
                }
                if (type.equals("inproceedings") && booktitle.equals("")) {
                    message = "Kirjan otsikko on pakollinen kenttä konferenssi-tyyppisessä viitteessä";
                    viestit.add(message);
                }
            }
            
            model.addAttribute("messages", viestit);
            return "form-article";
        }
        
        Article artikkeli = new Article(type, id, author, title, year);
        
        // (journal) NOT REQUIRED?
        if (journal != null) {
            artikkeli.setJournal(journal);
        }

        if (volume != null) {
            artikkeli.setVolume(volume);
        }

        if (number != null) {
            artikkeli.setNumber(number);
        }

        if (pages != null) {
            artikkeli.setPages(pages);
        }
        
        if (publisher != null) {
            artikkeli.setPublisher(publisher);
        }
        
        try {
            tallentaja.tallennaArtikkeli(artikkeli);
        } catch (Exception e) {
            model.addAttribute("title", "Poikkeus");
            model.addAttribute("message", e.getMessage());
            return "message"; 
        }
        
        model.addAttribute("title", "Lisätty!");
        model.addAttribute("message", id + " lisätty onnistuneesti!");
        return "message";
    }

    @RequestMapping(value = "listaa", method = RequestMethod.GET)
    public String get(Model model) {

        List<Article> artikkelit;

        try {
            artikkelit = tallentaja.listaaArtikelit();
        } catch (Exception e) {
            model.addAttribute("title", "Poikkeus");
            model.addAttribute("message", e.getMessage());
            return "message";
        }

        ArrayList<Article> articles = new ArrayList();
        for (Article artikkeli : artikkelit) {
            articles.add(artikkeli);
        }

        model.addAttribute("artikkelit", articles);

        return "list";
     }
    
    @RequestMapping(value="haebibtex", method = RequestMethod.GET)
    public String tulostaBibTeX(Model model) {
        
        List<Article> artikkelit;
        try {
            ArtikkelinTallentaja tallentaja = new ArtikkelinTallentaja();
            artikkelit = tallentaja.listaaArtikelit();
        } catch (Exception e) {
            model.addAttribute("title", "Poikkeus");
            model.addAttribute("message", e.getMessage());
            return "message"; 
        }
        
       ArrayList<String> bibit = new ArrayList<String>();
        for (Article artikkeli : artikkelit) {
            String bibtex = artikkeli.toBibTeX();
            bibit.add(bibtex);
        }
         
        model.addAttribute("bibit", bibit);

        return "bibtex";
    }
    
    @RequestMapping(value="haebibtex/{id}", method = RequestMethod.GET)
    public String tulostaHaettuBibTeX(Model model, @PathVariable(value="id") String id) {
        
        Article article;
        try {
            ArtikkelinTallentaja tallentaja = new ArtikkelinTallentaja();
            article = tallentaja.haeIdlla(id);
        } catch (Exception e) {
            model.addAttribute("title", "Poikkeus");
            model.addAttribute("message", e.getMessage());
            return "message"; 
        }
        
        String bibtex = article.toBibTeX();
        ArrayList<String> bibit = new ArrayList<String>();
        bibit.add(bibtex);
         
        model.addAttribute("bibit", bibit);

        return "bibtex";
    }
    
    @RequestMapping(value="poista/{id}", method = RequestMethod.POST)
    public String poistaViite(Model model, @PathVariable(value="id") String id) {
        
        Article article;
        try {
            ArtikkelinTallentaja tallentaja = new ArtikkelinTallentaja();
            tallentaja.poistaViite(id);
        } catch (Exception e) {
            model.addAttribute("title", "Poikkeus");
            model.addAttribute("message", e.getMessage());
            return "message"; 
        }

        return "redirect:../listaa";
    }
}