/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kevatpaiva;

import ohtu.kevatpaiva.tallennus.ArtikkelinTallentaja;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author heidi
 */
public class TallentajaTest {
    ArtikkelinTallentaja tallentaja;
    
    public TallentajaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        tallentaja = new ArtikkelinTallentaja();
    }
    
    @After
    public void tearDown() {
    }
    
    @Ignore
    @Test
    public void testaaViitteenPoistoa() throws Exception {
        if (tallentaja.onkoArtikkeli("K00")) {
            tallentaja.poistaViite("K00");
        }

        Article article3 = new Article("K00", "Karl, D.", "Funktion und Bedeutung einer weisen Frau im alten Ägypten", "2000");
        tallentaja.tallennaArtikkeli(article3);
        
        tallentaja.poistaViite("K00");
        
        assertFalse(tallentaja.onkoArtikkeli("K00"));
    }
    
    @Ignore
    @Test
    public void testaaViitteenTallennustaTallentajanKautta() throws Exception {
        
        if (tallentaja.onkoArtikkeli("K00")) {
            tallentaja.poistaViite("K00");
        }

        Article article2 = new Article("K00", "Karl, D.", "Funktion und Bedeutung einer weisen Frau im alten Ägypten", "2000");
        tallentaja.tallennaArtikkeli(article2);
        
        assertTrue(tallentaja.onkoArtikkeli("K00"));
        
        tallentaja.poistaViite("K00");
    }
}
