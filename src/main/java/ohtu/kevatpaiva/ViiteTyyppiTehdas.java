package ohtu.kevatpaiva;

/**
 * Tältä tehtaalta saadaan alustettuja viite-olioita, jolla kentät valmiina.
 */
public class ViiteTyyppiTehdas {
    
    public static ViiteTyyppi luoViiteTyyppi(String vt) {

        if (vt.equals("article")) {
            return luoArtikkeliTyyppi();
        }
        
        if (vt.equals("book")) {
            return luoKirjaTyyppi();
        }
        
        if (vt.equals("inproceedings")) {
            return luoKonferenssijulkaisuTyyppi();
        }
        
        throw new IllegalArgumentException("Tuntematon viitetyyppi: " + vt);
  
    }
    
    private static ViiteTyyppi luoArtikkeliTyyppi() {
        
        ViiteTyyppi artikkeliTyyppi = new ViiteTyyppi(
                "article",
                "Artikkeli",
                new KenttaTyyppi("title","Otsikko",true),
                new KenttaTyyppi("author","Kirjoittaja",true),
                new KenttaTyyppi("journal","Julkaisu",true),
                new KenttaTyyppi("year","Vuosi",true),
                new KenttaTyyppi("key","Avain",false),
                new KenttaTyyppi("month","Kuukausi",false),
                new KenttaTyyppi("number","Numero",false),
                new KenttaTyyppi("note","Note",false),
                new KenttaTyyppi("address","Osoite",false),
                new KenttaTyyppi("volume","Nide",false),
                new KenttaTyyppi("pages","Sivut",false));

        return artikkeliTyyppi;
        
    }

    private static ViiteTyyppi luoKirjaTyyppi() {
        
        ViiteTyyppi kirjaTyyppi = new ViiteTyyppi(
                "book",
                "Kirja",
                new KenttaTyyppi("author","Kirjoittaja",true),
                new KenttaTyyppi("editor","Toimittaja",true),
                new KenttaTyyppi("publisher","Julkaisia",true),
                new KenttaTyyppi("title","Otsikko",true),
                new KenttaTyyppi("year","Vuosi",true),
                new KenttaTyyppi("address","Osoite",false),
                new KenttaTyyppi("edition","Painos",false),
                new KenttaTyyppi("key","Avain",false),
                new KenttaTyyppi("month","Kuukausi",false),
                new KenttaTyyppi("note","Huomio",false),
                new KenttaTyyppi("number","Numero",false),
                new KenttaTyyppi("series","Sarja",false),
                new KenttaTyyppi("volume","Nide",false));
        
        return kirjaTyyppi;
    }
    
    private static ViiteTyyppi luoKonferenssijulkaisuTyyppi() {
        
        ViiteTyyppi konferenssijulkaisuTyyppi = new ViiteTyyppi(
                "inproceedings",
                "Konferenssi julkaisu",
                new KenttaTyyppi("author","Kirjoittaja",true),
                new KenttaTyyppi("booktitle","Konferenssi",true),
                new KenttaTyyppi("title","Otsikko",true),
                new KenttaTyyppi("year","Vuosi",true),
                new KenttaTyyppi("address","Osoite",false),
                new KenttaTyyppi("editor","Toimittaja",false),
                new KenttaTyyppi("key","Avain",false),
                new KenttaTyyppi("month","Kuukausi",false),
                new KenttaTyyppi("note","Huomio",false),
                new KenttaTyyppi("number","Numero",false),
                new KenttaTyyppi("organization","Organisaatio",false),
                new KenttaTyyppi("pages","Sivut",false),
                new KenttaTyyppi("publisher","Julkaisia",false),
                new KenttaTyyppi("series","Sarja",false),
                new KenttaTyyppi("volume","Nide",false));
        
        return konferenssijulkaisuTyyppi;
    }
}
