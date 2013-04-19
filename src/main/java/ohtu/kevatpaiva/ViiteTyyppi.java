package ohtu.kevatpaiva;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Viitteen tyyppi
 *
 * Esim. "article", "book" jolla kenttiä "title","author" jne.
 *
 */
@Entity
@Table(name = "viitetyyppi")
public class ViiteTyyppi {

    @Id
    private String nimi;
    
    @Column
    private String selitys;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="VIITETYYPPI_ID")
    private Set<KenttaTyyppi> kenttaTyypit;

    public ViiteTyyppi() {
        kenttaTyypit = new HashSet<KenttaTyyppi>();
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

    void lisaaKenttaTyyppi(KenttaTyyppi kenttaTyyppi) {
        this.kenttaTyypit.add(kenttaTyyppi);
    }

    public Set<KenttaTyyppi> getKenttaTyypit() {
        return kenttaTyypit;
    }

    public void setKenttaTyypit(Set<KenttaTyyppi> kenttaTyypit) {
        this.kenttaTyypit = kenttaTyypit;
    }

    KenttaTyyppi getKenttaTyyppi(String kentanNimi) {
        for (KenttaTyyppi kt : kenttaTyypit) {
            if (kt.getNimi().equals(kentanNimi)) {
                return kt;
            }
        }

        return null;
    }
}
