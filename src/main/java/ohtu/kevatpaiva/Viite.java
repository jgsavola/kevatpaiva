package ohtu.kevatpaiva;
 
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
/**
 * Sisältää kaikki BibTex-viittauksiin käytettävät kentät.
 *
 */
@Entity
@Table(name = "viite")
public class Viite {
    
    private String id;
    private ViiteTyyppi tyyppi;
    private List<Kentta> kentat;

    public Viite() {
 
    }
 
    public Viite(String id, ViiteTyyppi tyyppi, List<Kentta> kentat) {
        this.id = id;
        this.tyyppi = tyyppi;
        this.kentat = kentat;
    }
 
    @Id
    public String getId() {
        return this.id;
    }
 
    public void setId(String id) {
        this.id = id;
    }

}
