package ohtu.kevatpaiva;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author ninbarlu
 */
public class MuuntoTest {
    
    @Test
    public void muunnaIsotTitle() {
        assertEquals("te{S}t{I}", Muunto.muunnaMuoto("teStI", true));
    }
    
    @Test
    public void muunnaIsotEiTitle() {
        assertEquals("teStI", Muunto.muunnaMuoto("teStI", false));
    }

    @Test
    public void muunnaSkandit() {
        assertEquals("a\\\"{A}\\\"{o}O", Muunto.muunnaMuoto("aÄöO", false));
    }
    
    @Test
    public void muunnaKokoJono() {
        String syote = "Otsikko, ääkköset";
        String odotettu = "{O}tsikko, \\\"{a}\\\"{a}kk\\\"{o}set";

        String tulos = Muunto.muunnaMuoto(syote, true);
        assertEquals("Otsikkomerkkijono ääkkösillä muunnetaan oikein", odotettu, tulos);
    }
}