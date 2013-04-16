package ohtu.kevatpaiva;

/**
 * Viitteen kenttätyypit
 * 
 * Nimi on tunniste esim "title", "author"
 */
public class KenttaTyyppi {
    
    private String nimi;
    private String selite;
    private boolean pakollinen;

    public KenttaTyyppi() {
    }

    public KenttaTyyppi(String nimi, String selite, boolean pakollinen) {
        this.nimi = nimi;
        this.selite = selite;
        this.pakollinen = pakollinen;
    }
    
    
    
    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getSelite() {
        return selite;
    }

    public void setSelite(String selite) {
        this.selite = selite;
    }

    public boolean isPakollinen() {
        return pakollinen;
    }

    public void setPakollinen(boolean pakollinen) {
        this.pakollinen = pakollinen;
    }
    
    
}
