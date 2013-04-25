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
    public String naytaLomake(@RequestParam(value="viiteTyyppi", required=false) String viiteTyyppi) {

        if(viiteTyyppi != null) {
            
            if (viiteTyyppi.equals("article")) {
                return "form-article";
            }
            
            if (viiteTyyppi.equals("book")) {
                return "form-book";
            }
            
            if (viiteTyyppi.equals("inproceedings")) {
                return "form-inproceedings";
            }
            
        }
        
        // Defaultti-lomake
        return "form-article";
    }

    @RequestMapping(value="lisaa", method = RequestMethod.POST)
    public String lisaaArtikkeli(
            @RequestParam(value = "viiteTyyppi", required = false) String viiteTyyppi,
            @RequestParam String id,
            @RequestParam String author,
            @RequestParam String title,
            @RequestParam(value = "journal", required = false) String journal,
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
            @RequestParam(value = "organization", required = false) String organization,
            @RequestParam(value = "paivitysMoodi", required = false) Boolean paivitysMoodi,
            ModelMap model) {
        
        ArrayList<String> viestit = new ArrayList<String>();
        boolean idOnJo = tallentaja.onkoArtikkeli(id);
        
        if(paivitysMoodi != null) {
            idOnJo = false;
            model.addAttribute("paivitysMoodi", true);
        }
        
        if (idOnJo || id.equals("") || viiteTyyppi == null||author.equals("") || title.equals("") || year.equals("") 
                || (viiteTyyppi.equals("article") && journal.equals(""))
                || (viiteTyyppi.equals("book") && (editor.equals("") || publisher.equals(""))) 
                || (viiteTyyppi.equals("inproceedings") && booktitle.equals(""))) {
            model.addAttribute("viiteTyyppi", viiteTyyppi);
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
            model.addAttribute("organization", organization);
            model.addAttribute("volume", volume);
            
            String message = "";
            if (viiteTyyppi == null) {
                message = "Viitteen tyyppi pit\u00e4\u00e4 olla valittuna";
                viestit.add(message);
                return "form-article";
            }
            else {
                if (idOnJo) {
                    message = "Viite kyseisell\u00e4 id:ll\u00e4 on jo tallennettu";
                    viestit.add(message);
                }
                if (id.equals("")) {
                    message = "Id on pakollinen kentt\u00e4";
                    viestit.add(message);
                }
                if (author.equals("")) {
                    message = "Kirjoittaja on pakollinen kentt\u00e4";
                    viestit.add(message);
                }
                if (title.equals("")) {
                    message = "Otsikko on pakollinen kentt\u00e4";
                    viestit.add(message);
                }
                if (year.equals("")) {
                    message = "Vuosi on pakollinen kentt\u00e4";
                    viestit.add(message);
                }
                if (viiteTyyppi.equals("article") && journal.equals("")) {
                    message = "Journaali on pakollinen kentt\u00e4 artikkeli-tyyppisess\u00e4 viitteess\u00e4";
                    viestit.add(message);
                }
                if (viiteTyyppi.equals("book") && (editor.equals("") || publisher.equals(""))) {
                    message = "Ediittori ja kustantaja ovat pakollisia kentti\u00e4 kirja-tyyppisess\u00e4 viittess\u00e4";
                    viestit.add(message);
                }
                if (viiteTyyppi.equals("inproceedings") && booktitle.equals("")) {
                    message = "Nimike on pakollinen kentt\u00e4 konferenssi-tyyppisess\u00e4 viitteess\u00e4";
                    viestit.add(message);
                }
            }
            
            model.addAttribute("messages", viestit);
            if (viiteTyyppi.equals("inproceedings")) {
                return "form-inproceedings";
            }
            else if (viiteTyyppi.equals("book")) {
                return "form-book";
            }
            else {
                return "form-article";
            }
        }
        
        Article viite = new Article();
        
        if (viiteTyyppi.equals("article")) {
            
            viite = new Article(viiteTyyppi, id, author, title, year, journal, null, null, null);
            
        } else if (viiteTyyppi.equals("book")) {
            
            viite = new Article(viiteTyyppi, id, author, title, year, null, editor, publisher, null);
            
        } else if (viiteTyyppi.equals("inproceedings")) {
            
            viite = new Article(viiteTyyppi, id, author, title, year, null, null, null, booktitle);
            
        } else {
            model.addAttribute("title", "Poikkeus");
            model.addAttribute("message", "Viitteen tyyppi " + viiteTyyppi + " on tuntematon!");
            return "message";
        }

        /* Valinnaiset */
        if (address != null && !address.isEmpty()) {
            viite.setAddress(address);
        }
        
        if (edition != null && !edition.isEmpty()) {
            viite.setEdition(edition);
        }
        
        if (editor != null && !editor.isEmpty()) {
            viite.setEditor(editor);
        }
        
        if (key != null && !key.isEmpty()) {
            viite.setKey(key);
        }

        if (month != null && !month.isEmpty()) {
            viite.setMonth(month);
        }

        if (note != null && !note.isEmpty()) {
            viite.setNote(note);
        }
        
        if (number != null && !number.isEmpty()) {
            viite.setNumber(number);
        }

        if (organization != null && !organization.isEmpty()) {
            viite.setOrganization(organization);
        }
        
        if (pages != null && !pages.isEmpty()) {
            viite.setPages(pages);
        }
        
        if (publisher != null && !publisher.isEmpty()) {
            viite.setPublisher(publisher);
        }
        
        if (series != null && !series.isEmpty()) {
            viite.setSeries(series);
        }
        
        if (volume != null && !volume.isEmpty()) {
            viite.setVolume(volume);
        }
        
        if(paivitysMoodi != null) {
            
            try {
                tallentaja.paivitaViite(viite);
            } catch (Exception e) {
                model.addAttribute("title", "Poikkeus");
                model.addAttribute("message", e.getMessage());
                return "message"; 
            }
            model.addAttribute("title", "P\u00e4ivitetty!");
            model.addAttribute("message", id + " p\u00e4ivitetty onnistuneesti!");
            return "message";
            
        } else {
        
            try {
                tallentaja.tallennaArtikkeli(viite);
            } catch (Exception e) {
                model.addAttribute("title", "Poikkeus");
                model.addAttribute("message", e.getMessage());
                return "message"; 
            }
            model.addAttribute("title", "Lis\u00e4tty!");
            model.addAttribute("message", id + " lis\u00e4tty onnistuneesti!");
            return "message";
        }
       
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
    
    @RequestMapping(value="paivita/{id}", method = RequestMethod.POST)
    public String paivitaViite(Model model, @PathVariable(value="id") String id) {
        
        Article article;
        try {
            ArtikkelinTallentaja tallentaja = new ArtikkelinTallentaja();
            article = tallentaja.haeIdlla(id);
        } catch (Exception e) {
            model.addAttribute("title", "Poikkeus");
            model.addAttribute("message", e.getMessage());
            return "message"; 
        }
        
        model.addAttribute("id", article.getId());
        model.addAttribute("author", article.getAuthor());
        model.addAttribute("title", article.getTitle());
        model.addAttribute("year", article.getYear());
        
        if (article.getViiteTyyppi().equals("article")) {
            model.addAttribute("journal", article.getJournal());
            
            model.addAttribute("pages", article.getPages());
            
            model.addAttribute("publisher", article.getPublisher());
        }
            
        if (article.getViiteTyyppi().equals("book")) {
            model.addAttribute("editor", article.getEditor());
            model.addAttribute("publisher", article.getPublisher());
            
            model.addAttribute("edition", article.getEdition());
            model.addAttribute("series", article.getSeries());
            
        }
        
        if (article.getViiteTyyppi().equals("inproceedings")) {
            model.addAttribute("booktitle", article.getBooktitle());
            
            model.addAttribute("editor", article.getEditor());
            model.addAttribute("organization", article.getOrganization());
            model.addAttribute("pages", article.getPages());
            model.addAttribute("publisher", article.getPublisher());
            model.addAttribute("series", article.getSeries());
        }
        
        model.addAttribute("address", article.getAddress());
        model.addAttribute("key", article.getKey());
        model.addAttribute("month", article.getMonth());
        model.addAttribute("note", article.getNote());
        model.addAttribute("number", article.getNumber());
        model.addAttribute("volume", article.getVolume());
 
        
        model.addAttribute("viiteTyyppi", article.getViiteTyyppi());
        model.addAttribute("paivitysMoodi", true);
        
        if (article.getViiteTyyppi().equals("inproceedings")) {
            return "form-inproceedings";
        }
        else if (article.getViiteTyyppi().equals("book")) {
            return "form-book";
        }
        else {
            return "form-article";
        }
    }
}