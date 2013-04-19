/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kevatpaiva.tallennus;

import java.util.List;
import ohtu.kevatpaiva.KenttaTehdas;
import ohtu.kevatpaiva.KenttaTyyppi;
import ohtu.kevatpaiva.Viite;
import ohtu.kevatpaiva.ViiteTyyppi;
import ohtu.kevatpaiva.ViiteTyyppiTehdas;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jgsavola
 */
public class ViitteenTallentajaTest {
    private final ViiteTyyppiTehdas viiteTyyppiTehdas;
    
    public ViitteenTallentajaTest() {
        this.viiteTyyppiTehdas = new ViiteTyyppiTehdas();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of tallenna method, of class ViitteenTallentaja.
     */
    @Test
    public void useammanTallentajanLuominenOnnistuu() throws Exception {
        ViitteenTallentaja instance1 = new ViitteenTallentaja();
        ViitteenTallentaja instance2 = new ViitteenTallentaja();
        
        ViiteTyyppi artikkeliTyyppi = viiteTyyppiTehdas.luoViiteTyyppi("article");
        ViiteTyyppi kirjaTyyppi = viiteTyyppiTehdas.luoViiteTyyppi("book");

        KenttaTyyppi kt1 = new KenttaTyyppi("kt1", "kt1 selite", false);
        KenttaTyyppi kt2 = new KenttaTyyppi("kt2", "kt2 selite", false);              
        
        instance1.tallenna(kt1);

        instance2.tallenna(kt2);
    }

}
