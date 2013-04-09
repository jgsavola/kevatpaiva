package ohtu.kevatpaiva;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author ninbarlu
 */
public class MuunnaBibTexMuotoTest {
    
    @Test
    public void muunnaIsotTitle() {
        assertEquals("te{S}t{I}", MuunnaBibTexMuoto.muunnaMuoto("teStI", true));
    }
    
    @Test
    public void muunnaIsotEiTitle() {
        assertEquals("teStI", MuunnaBibTexMuoto.muunnaMuoto("teStI", false));
    }
    
    @Test
    public void muunnaSkandit() {
        assertEquals("a\\\"{A}\\\"{o}O", MuunnaBibTexMuoto.muunnaMuoto("aÄöO", false));
    }
    
    @Test
    public void muunnaKokoJono() {
        assertEquals("{O}tsikko, \\\"{a}\\\"{a}kk\\\"{o}set", MuunnaBibTexMuoto.muunnaMuoto("Otsikko, ääkköset", true));
    }
    
}