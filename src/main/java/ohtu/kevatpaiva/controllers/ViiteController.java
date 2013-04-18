package ohtu.kevatpaiva.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import ohtu.kevatpaiva.Kentta;
import ohtu.kevatpaiva.KenttaTehdas;
import ohtu.kevatpaiva.KenttaTyyppi;
import ohtu.kevatpaiva.Viite;
import ohtu.kevatpaiva.ViiteTyyppi;
import ohtu.kevatpaiva.ViiteTyyppiTehdas;
import ohtu.kevatpaiva.tallennus.ViitteenTallentaja;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Viitekontrolleri
 */
@Controller
@RequestMapping("/")
public class ViiteController {

    private ViitteenTallentaja tallentaja;
    
    @Autowired
    private HttpServletRequest request;

    public ViiteController() {

        this.tallentaja = new ViitteenTallentaja();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String naytaPaasivu() {

        return "start";
    }

    @RequestMapping(value = "lomake", method = RequestMethod.GET)
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

    @RequestMapping(value = "lisaa", method = RequestMethod.POST)
    public String lisaaViite(@RequestParam("viiteTyyppi") String viiteTyyppi, ModelMap model) {

        /**
         * Hommaa lomakkeen kentät request-objektilta. Tässä pitäisi olla koko
         * lomakkeen sisältö!
         */
        Map<String, String[]> parameterMap = request.getParameterMap();

        //boolean idOnJo = tallentaja.onkoViite(parameterMap.get("id")[0]);
        
        ViiteTyyppi vt = ViiteTyyppiTehdas.luoViiteTyyppi(parameterMap.get("viiteTyyppi")[0]);
        KenttaTehdas kt = new KenttaTehdas(vt);
        Set<KenttaTyyppi> skt = vt.getKenttaTyypit();
        Set<Kentta> sk = new HashSet<Kentta>();
        
        for (Iterator<KenttaTyyppi> it = skt.iterator(); it.hasNext();) {
            
            KenttaTyyppi kety = it.next();

            if(parameterMap.containsKey(kety.getNimi()) && !parameterMap.get(kety.getNimi())[0].isEmpty()) {
                sk.add(kt.luoKentta(kety.getNimi(), parameterMap.get(kety.getNimi())[0]));
            }
        }

        Viite viite = new Viite(parameterMap.get("id")[0], vt, sk);
       
        /*
        model.addAttribute("otsikko", "DEBUG!!");
        model.addAttribute("viesti", sk.toString());
        return "viesti";
        */
        
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
*   */   
      
        try {

           tallentaja.tallenna(viite);

        } catch (NonUniqueObjectException exception) {
           model.addAttribute("viesti", "Viite kyseisellä id:llä on jo tallennettu");
           return "poikkeus"; 
        } catch (Exception e) {
           e.printStackTrace();
           model.addAttribute("viesti", e.getMessage());
           return "poikkeus"; 
        } 

        model.addAttribute("otsikko", "Lisätty!");
        model.addAttribute("viesti", parameterMap.get("id")[0] + " lisätty onnistuneesti!");

        return "viesti";
    }

    @RequestMapping(value = "listaa", method = RequestMethod.GET)
    public String listaaViitteet(Model model) {

        List<Viite> viitteet;

        try {
            
            viitteet = tallentaja.listaa();
            
        } catch (Exception e) {
            
            e.printStackTrace();
            model.addAttribute("message", e.getMessage());
            return "poikkeus";
        }

        model.addAttribute("viiteLista", viitteet);
        return "lista";
    }
    
    @RequestMapping(value = "haebibtex", method = RequestMethod.GET)
    public String tulostaBibTeX(Model model) {

        List<Viite> viitteet;
        
        try {
            
            viitteet = tallentaja.listaa();
            
        } catch (Exception e) {
            
            e.printStackTrace();
            model.addAttribute("viesti", e.getMessage());
            return "poikkeus";
        }

        ArrayList<String> bibit = new ArrayList<String>();
        for (Viite viite : viitteet) {
            String bibtex = viite.toBibTeX();
            bibit.add(bibtex);
        }

        model.addAttribute("bibit", bibit);
        return "bibtex";
    }

    @RequestMapping(value = "haebibtex/{id}", method = RequestMethod.GET)
    public String tulostaHaettuBibTeX(@PathVariable(value = "id") String id, Model model) {

        Viite viite;
        
        try {
            
            viite = tallentaja.haeIdlla(id);
            
        } catch (Exception e) {
            
            e.printStackTrace();
            model.addAttribute("viesti", e.getMessage());
            
            return "poikkeus";
        }

        String bibtex = viite.toBibTeX();
        ArrayList<String> bibit = new ArrayList<String>();
        bibit.add(bibtex);

        model.addAttribute("bibit", bibit);
        return "bibtex";
    }
}