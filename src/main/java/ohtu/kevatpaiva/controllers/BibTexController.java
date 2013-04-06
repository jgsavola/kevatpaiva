/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author heidi
 */

@Controller
@RequestMapping("haebibtex")
public class BibTexController {
    
    @RequestMapping(method = RequestMethod.GET)
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
}
