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
        String syote = "aÄöO";
        String odotettu = "a\\\"{A}\\\"{o}O";

        String tulos = Muunto.muunnaMuoto(syote, false);

        System.out.println("syöte:    " + syote);
        System.out.println("odotettu: " + odotettu);
        System.out.println("tulos:    " + tulos);

        assertEquals("Skandien muuntaminen onnistuu.", odotettu, tulos);
    }
    
    @Test
    public void muunnaKokoJono() {
        String syote = "Otsikko, ääkköset";
        String odotettu = "{O}tsikko, \\\"{a}\\\"{a}kk\\\"{o}set";

        String tulos = Muunto.muunnaMuoto(syote, true);
        assertEquals("Otsikkomerkkijono ääkkösillä muunnetaan oikein", odotettu, tulos);
    }
}