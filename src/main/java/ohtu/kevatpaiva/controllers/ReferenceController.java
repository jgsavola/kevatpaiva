package ohtu.kevatpaiva.controllers;

import java.util.ArrayList;
import java.util.List;
import ohtu.kevatpaiva.Reference;
import ohtu.kevatpaiva.ViiteTyyppiTehdas;
import ohtu.kevatpaiva.tallennus.Tallentaja;
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
    private Tallentaja tallentaja;

    public ReferenceController() {
        this.tallentaja = new Tallentaja();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String naytaPaasivu() {

        return "start";
    }
    
    @RequestMapping(value="lomake", method = RequestMethod.GET)
    public String naytaLomake(Model model) {
        
        model.addAttribute("viiteTyypit", ViiteTyyppiTehdas.luoViiteTyyppiLista());
        return "lomake";
    }

    @RequestMapping(value = "lomake/{viiteTyyppi}", method = RequestMethod.GET)
    public String naytaLomakeViiteTyypilla(@PathVariable("viiteTyyppi") String viiteTyyppi, Model model) {
        
        model.addAttribute("viiteTyypit", ViiteTyyppiTehdas.luoViiteTyyppiLista());
        model.addAttribute("viiteTyyppi", ViiteTyyppiTehdas.luoViiteTyyppi(viiteTyyppi));
        return "lomake";
    }
    
    @RequestMapping(value="lisaa", method = RequestMethod.POST)
    public String lisaaArtikkeli(
            @RequestParam String viiteTyyppi,
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
        
        boolean idOnJo = tallentaja.onkoReference(id);
        
        if (idOnJo || id.equals("") || author.equals("") || title.equals("") || year.equals("") 
                || (viiteTyyppi.equals("article") && journal.equals(""))
                || (viiteTyyppi.equals("book") && (editor.equals("") || publisher.equals(""))) 
                || (viiteTyyppi.equals("inproceedings") && booktitle.equals(""))) {
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
            else if (viiteTyyppi.equals("article") && journal.equals("")) {
                message = "Journaali on pakollinen kenttä";
            }
            else if (viiteTyyppi.equals("book") && (editor.equals("") || publisher.equals(""))) {
                message = "Ediittori ja julkaisija ovat pakollisia kenttiä";
            }
            else if (viiteTyyppi.equals("inproceedings") && booktitle.equals("")) {
                message = "Kirjan otsikko on pakollinen kenttä";
            }
            else {
                message = "Id, kirjoittaja, otsikko ja vuosi ovat pakollisia kenttiä";
            }
              model.addAttribute("viiteTyypit", ViiteTyyppiTehdas.luoViiteTyyppiLista());
              model.addAttribute("viiteTyyppi", ViiteTyyppiTehdas.luoViiteTyyppi(viiteTyyppi));
            model.addAttribute("message", message);
            return "lomake";
        }
        
        
        Reference artikkeli = new Reference(viiteTyyppi, id, author, title, year);
        
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
            tallentaja.tallennaReferencet(artikkeli);
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

        List<Reference> artikkelit;

        try {
            artikkelit = tallentaja.listaaReferencet();
        } catch (Exception e) {
            model.addAttribute("title", "Poikkeus");
            model.addAttribute("message", e.getMessage());
            return "message";
        }

        ArrayList<Reference> articles = new ArrayList();
        for (Reference artikkeli : artikkelit) {
            articles.add(artikkeli);
        }

        model.addAttribute("artikkelit", articles);

        return "list";
     }
    
    @RequestMapping(value="haebibtex", method = RequestMethod.GET)
    public String tulostaBibTeX(Model model) {
        
        List<Reference> artikkelit;
        try {

            artikkelit = tallentaja.listaaReferencet();
        } catch (Exception e) {
            model.addAttribute("title", "Poikkeus");
            model.addAttribute("message", e.getMessage());
            return "message"; 
        }
        
       ArrayList<String> bibit = new ArrayList<String>();
        for (Reference artikkeli : artikkelit) {
            String bibtex = artikkeli.toBibTeX();
            bibit.add(bibtex);
        }
         
        model.addAttribute("bibit", bibit);

        return "bibtex";
    }
    
    @RequestMapping(value="haebibtex/{id}", method = RequestMethod.GET)
    public String tulostaHaettuBibTeX(Model model, @PathVariable(value="id") String id) {
        
        Reference article;
        try {

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