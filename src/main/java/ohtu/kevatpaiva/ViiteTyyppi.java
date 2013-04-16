package ohtu.kevatpaiva;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Viitteen tyyppi
 * 
 * Esim. "article", "book" jolla kenttiä "title","author" jne.
 * 
 */
public class ViiteTyyppi {
    
    private String nimi;
    private String selitys;
    private List<KenttaTyyppi> kenttaTyypit;

    public ViiteTyyppi() {
        kenttaTyypit = new ArrayList<KenttaTyyppi>();
    }
    
    public ViiteTyyppi(String nimi, String selitys, KenttaTyyppi... kenttaTyypit) {
        this();
        this.nimi = nimi;
        this.selitys = selitys;
        this.kenttaTyypit.addAll(Arrays.asList(kenttaTyypit));
    }
    
    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getSelitys() {
        return selitys;
    }

    public void setSelitys(String selitys) {
        this.selitys = selitys;
    }

    public List<KenttaTyyppi> getKenttaTyypit() {
        return kenttaTyypit;
    }
    
    void lisaaKenttaTyyppi(KenttaTyyppi kenttaTyyppi) {
        this.kenttaTyypit.add(kenttaTyyppi);
    }
   
    
}
