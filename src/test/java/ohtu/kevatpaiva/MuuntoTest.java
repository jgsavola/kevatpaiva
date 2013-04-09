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
        assertEquals("{O}tsikko, \\\"{a}\\\"{a}kk\\\"{o}set", Muunto.muunnaMuoto("Otsikko, ääkköset", true));
    }
    
}