package ohtu.kevatpaiva.controllers;

import java.util.ArrayList;
import java.util.List;
import ohtu.kevatpaiva.Article;
import ohtu.kevatpaiva.tallennus.ArtikkelinTallentaja;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
    public String naytaLomake() {

        return "form-article";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String lisaaArtikkeli(
            @RequestParam String id,
            @RequestParam String author,
            @RequestParam String title,
            @RequestParam(value = "journal", required = false) String journal, // (journal) NOT REQUIRED?
            @RequestParam Integer year,
            @RequestParam(value = "volume", required = false) Integer volume,
            @RequestParam(value = "number", required = false) Integer number,
            @RequestParam(value = "pages", required = false) String pages,
            @RequestParam(value = "publisher", required = false) String publisher,
            @RequestParam(value = "address", required = false) String address,
            ModelMap model) {
        
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
}