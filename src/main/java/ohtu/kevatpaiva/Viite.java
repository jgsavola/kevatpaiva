package ohtu.kevatpaiva;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Sisältää kaikki BibTex-viittauksiin käytettävät kentät.
 *
 */
@Entity
@Table(name = "viite")
public class Viite {
    @Id
    private String id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ViiteTyyppi tyyppi;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="VIITE_ID")
    private Set<Kentta> kentat;

    public Viite() {
        kentat = new HashSet<Kentta>();
    }

    public Viite(String id, ViiteTyyppi tyyppi, Set<Kentta> kentat) {
        this.id = id;
        this.tyyppi = tyyppi;
        this.kentat = kentat;
    }

    public Viite(String id, ViiteTyyppi tyyppi, Kentta... kentat) {
        this();
        this.id = id;
        this.tyyppi = tyyppi;
        this.kentat.addAll(Arrays.asList(kentat));
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ViiteTyyppi getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(ViiteTyyppi tyyppi) {
        this.tyyppi = tyyppi;
    }

    public Set<Kentta> getKentat() {
        return kentat;
    }

    public void setKentat(Set<Kentta> kentat) {
        this.kentat = kentat;
    }

    public void lisaaKentta(Kentta kentta) {
        kentat.add(kentta);
    }

    /**
     * Muodosta Viitteen BibTeX-esitys.
     *
     * @return Merkkijono BibTeX-muodossa.
     */
    public String toBibTeX() {
        String s = "@" + this.tyyppi.getNimi() + "{" + this.getId() + ",\n";

        for (Kentta kentta : kentat) {
            s += "    " + kentta.toBibTeX() + ",\n";
        }

        /**
         * Yök.
         */
        if (s.length() > 2) {
            s = s.substring(0, s.length() - 2);
        }

        s += "\n}";

        return s;
    }
}
