package ohtu.kevatpaiva;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kentta")
public class Kentta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private KenttaTyyppi tyyppi;

    @Column
    private String arvo;

    public Kentta() {
    }

    Kentta(KenttaTyyppi tyyppi, String arvo) {
        this.tyyppi = tyyppi;
        this.arvo = arvo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public KenttaTyyppi getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(KenttaTyyppi tyyppi) {
        this.tyyppi = tyyppi;
    }

    public String getArvo() {
        return arvo;
    }

    public void setArvo(String arvo) {
        this.arvo = arvo;
    }

    @Override
    public int hashCode() {
        return tyyppi.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kentta other = (Kentta) obj;
        if (this.tyyppi != other.tyyppi && (this.tyyppi == null || !this.tyyppi.equals(other.tyyppi))) {
            return false;
        }
        return true;
    }

    /**
     * Muodosta kentän BibTeX-esitys muodossa "nimi = {arvo}".
     *
     * @return Merkkijono BibTeX-muodossa.
     */
    public String toBibTeX() {
        return tyyppi.getNimi() + " = {" + Muunto.muunnaMuoto(arvo, false) + "}";
    }
}
