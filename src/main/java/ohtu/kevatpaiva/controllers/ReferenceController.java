package ohtu.kevatpaiva.controllers;

import ohtu.kevatpaiva.Article;
import ohtu.kevatpaiva.tallennus.ArtikkelinTallentaja;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author danielli
 */
@Controller
@RequestMapping("/reference")
public class ReferenceController {
 
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        model.addAttribute("message", "(I am spring)");
        return "form-article";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String lisaaArtikkeli(@RequestParam String id, @RequestParam String author, 
            @RequestParam String title, @RequestParam String journal, @RequestParam int year, 
            @RequestParam int volume, @RequestParam int number, @RequestParam String pages, 
            @RequestParam String publisher) {
        
        Article artikkeli = new Article(id, author, title, year);
        artikkeli.setJournal(journal);
        artikkeli.setVolume(volume);
        artikkeli.setNumber(number);
        artikkeli.setPages(pages);
        artikkeli.setPublisher(publisher);
        ArtikkelinTallentaja tallentaja = new ArtikkelinTallentaja();
        tallentaja.tallennaArtikkeli(artikkeli);
        return "form-article";
        
    }
 
}