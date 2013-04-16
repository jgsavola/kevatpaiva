package ohtu.kevatpaiva;

/**
 * Tältä tehtaalta saadaan alustettuja viite-olioita, jolla kentät valmiina.
 */
public class ViiteTyyppiTehdas {
    
    public static ViiteTyyppi luoViiteTyyppi(String vt) {

        if (vt.equals("article")) {
            return luoArtikkeliTyyppi();
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
    
}
