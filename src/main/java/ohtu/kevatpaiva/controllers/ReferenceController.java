package ohtu.kevatpaiva.controllers;

import java.util.ArrayList;
import java.util.List;
import ohtu.kevatpaiva.Article;
import ohtu.kevatpaiva.ViiteTyyppi;
import ohtu.kevatpaiva.ViiteTyyppiTehdas;
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
            
        @RequestParam("type") String type,
        ModelMap model) {

        // RequestBody:lla?
        
        /*
         * TODO: refactor type to viite
         */
        
        ViiteTyyppi vt = ViiteTyyppiTehdas.luoViiteTyyppi(type);
        
        // pieni validointi
        
        // 
        
        
        
        //boolean idOnJo = tallentaja.onkoArtikkeli(id);
        
        /*
        
        
        if (idOnJo || id.equals("") || author.equals("") || title.equals("") || year.equals("") 
                || (type.equals("article") && journal.equals(""))
                || (type.equals("book") && (editor.equals("") || publisher.equals(""))) 
                || (type.equals("inproceedings") && booktitle.equals(""))) {
            //model.addAttribute("type", type);
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
            
            String message;
            if (idOnJo) {
                message = "Viite kyseisellä id:llä on jo tallennettu";
            }
            else if (type.equals("article") && journal.equals("")) {
                message = "Journaali on pakollinen kenttä";
            }
            else if (type.equals("book") && (editor.equals("") || publisher.equals(""))) {
                message = "Ediittori ja julkaisija ovat pakollisia kenttiä";
            }
            else if (type.equals("inproceedings") && booktitle.equals("")) {
                message = "Kirjan otsikko on pakollinen kenttä";
            }
            else {
                message = "Id, kirjoittaja, otsikko ja vuosi ovat pakollisia kenttiä";
            }
            model.addAttribute("message", message);
            return "form-article";
        }
        
        
        Article artikkeli = new Article(id, author, title, year);
        
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
        */
        
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
}