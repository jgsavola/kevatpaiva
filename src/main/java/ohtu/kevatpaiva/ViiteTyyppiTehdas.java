package ohtu.kevatpaiva;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ohtu.kevatpaiva.tallennus.ViitteenTallentaja;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Tältä tehtaalta saadaan alustettuja viite-olioita, jolla kentät valmiina.
 */
public class ViiteTyyppiTehdas {
    private ViitteenTallentaja tallentaja;

    public ViiteTyyppiTehdas() {
        tallentaja = new ViitteenTallentaja();
    }
    
    public List<ViiteTyyppi> luoViiteTyyppiLista() {
        return Arrays.asList(
                luoArtikkeliTyyppi(),
                luoKirjaTyyppi(),
                luoKonferenssijulkaisuTyyppi());
    }
    
    public ViiteTyyppi luoViiteTyyppi(String viiteTyyppiId) {
        Session session = tallentaja.getSession();
        Transaction tx = null;
        
        ViiteTyyppi vt = lataa(viiteTyyppiId);
        if (vt != null) {
            return vt;
        }
        
        try {
            
            if (vt != null) {
                return vt;
            }
        
            if (viiteTyyppiId.equals("article")) {
                vt = luoArtikkeliTyyppi();
            } else if (viiteTyyppiId.equals("book")) {
                vt = luoKirjaTyyppi();
            } else if (viiteTyyppiId.equals("inproceedings")) {
                vt = luoKonferenssijulkaisuTyyppi();
            } else {
                throw new IllegalArgumentException("Tuntematon viitetyyppi: " + viiteTyyppiId);
            }

            tx = session.beginTransaction();
            session.save(vt);
            tx.commit();            
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
        
        return vt;
    }
    
    private ViiteTyyppi luoArtikkeliTyyppi() {
        
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

    private ViiteTyyppi luoKirjaTyyppi() {
        
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
    
    private ViiteTyyppi luoKonferenssijulkaisuTyyppi() {
        
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

    private ViiteTyyppi lataa(String viiteTyyppiId) {
        Session session = tallentaja.getSession();

        Transaction tx = null;
        ViiteTyyppi vt = null;
        try {
            tx = session.beginTransaction();
            vt = (ViiteTyyppi)session.load(ViiteTyyppi.class, viiteTyyppiId);
            tx.commit();
        } catch (Exception ex) {
                System.out.println("oli jo olemassa? " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }

        return vt;
    }
}
