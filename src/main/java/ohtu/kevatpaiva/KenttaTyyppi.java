package ohtu.kevatpaiva;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Viitteen kenttätyypit
 *
 * Nimi on tunniste esim "title", "author"
 */
@Entity
@Table(name = "kenttatyyppi")
public class KenttaTyyppi {

    @Id
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

    @Override
    public int hashCode() {
        return nimi.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final KenttaTyyppi other = (KenttaTyyppi) obj;
        if ((this.nimi == null) ? (other.nimi != null) : !this.nimi.equals(other.nimi)) {
            return false;
        }
        return true;
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
