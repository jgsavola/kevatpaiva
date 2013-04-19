package ohtu.kevatpaiva.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
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

    //private static final Logger logger = Logger.getLogger(ViiteController.class);
    
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
    public String lisaaViite(ModelMap model) {

        /**
         * Hommaa lomakkeen kentät request-objektilta. Tässä pitäisi olla koko
         * lomakkeen sisältö!
         */
        Map<String, String[]> parameterMap = request.getParameterMap();

        String viitteenTyyppi;
        try {
            viitteenTyyppi = parameterMap.get("viiteTyyppi")[0];
        } catch (Exception e) {
            model.addAttribute("viiteTyypit", ViiteTyyppiTehdas.luoViiteTyyppiLista());
            model.addAttribute("virhe", "Viitteen tyyppi puuttui!");
            return "lomake";
        }

        
        String id = parameterMap.get("id")[0];
        
        //String viitteenTyyppi = parameterMap.get("viiteTyyppi")[0];
        
        boolean idOnJo = tallentaja.onkoViite(id);
        
        ViiteTyyppi vt = ViiteTyyppiTehdas.luoViiteTyyppi(viitteenTyyppi);
        KenttaTehdas kt = new KenttaTehdas(vt);
        Set<KenttaTyyppi> skt = vt.getKenttaTyypit();
        Set<Kentta> sk = new HashSet<Kentta>();
        
        for (Iterator<KenttaTyyppi> it = skt.iterator(); it.hasNext();) {
            
            KenttaTyyppi kety = it.next();

            if(parameterMap.containsKey(kety.getNimi()) && !parameterMap.get(kety.getNimi())[0].isEmpty()) {
                sk.add(kt.luoKentta(kety.getNimi(), parameterMap.get(kety.getNimi())[0]));
                model.addAttribute(kety.getNimi(), parameterMap.get(kety.getNimi())[0]);
            }
        }

        if (    viitteenTyyppi.equals("") || idOnJo || id.equals("") || parameterMap.get("author")[0].equals("") || parameterMap.get("title")[0].equals("") || parameterMap.get("year")[0].equals("") 
                || (viitteenTyyppi.equals("article") && parameterMap.get("journal")[0].equals(""))
                || (viitteenTyyppi.equals("book") && (parameterMap.get("editor")[0].equals("") || parameterMap.get("publisher")[0].equals(""))) 
                || (viitteenTyyppi.equals("inproceedings") && parameterMap.get("booktitle")[0].equals(""))) {
               
            String virhe;
            if (idOnJo) {
                virhe = "Viite kyseisellä id:llä on jo tallennettu";
            }
            else if (viitteenTyyppi.equals("article") && parameterMap.get("journal")[0].equals("")) {
                virhe = "Journaali on pakollinen kenttä";
            }
            else if (viitteenTyyppi.equals("book") && (parameterMap.get("editor")[0].equals("") || parameterMap.get("publisher")[0].equals(""))) {
                virhe = "Ediittori ja julkaisija ovat pakollisia kenttiä";
            }
            else if (viitteenTyyppi.equals("inproceedings") && parameterMap.get("booktitle")[0].equals("")) {
                virhe = "Kirjan otsikko on pakollinen kenttä";
            }
            else if (viitteenTyyppi.equals("")) {
                virhe = "Viitteen tyyppi puuttui!";
            }
            else {
                virhe = "Id, kirjoittaja, otsikko ja vuosi ovat pakollisia kenttiä";
            }
            
            model.addAttribute("viiteTyypit", ViiteTyyppiTehdas.luoViiteTyyppiLista());
            model.addAttribute("viiteTyyppi", vt);
            model.addAttribute("virhe", virhe);
            return "lomake";
        }

        Viite viite = new Viite(id, vt, sk);
        
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
        model.addAttribute("viesti", id + " lisätty onnistuneesti!");

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