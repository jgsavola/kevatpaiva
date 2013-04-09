package ohtu.kevatpaiva;

/**
 *
 * @author ninbarlu
 */
public class MuunnaBibTexMuoto {

    private static String okt = "abcdefghijklmnopqrstuvxyz0123456789 .,-";
    private static String isot = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
    private static String skandit = "åäöÅÄÖ";
    private static String[] skmuunto = {"\\\"{o}","\\\"{a}","\\\"{o}","\\\"{O}","\\\"{A}","\\\"{O}"};
    
    public static String muunnaMuoto(String merkkijono, boolean title) {
        
        String tulos = new String();
        String muunnos = new String();
        int i;
        for (i =0; i < merkkijono.length(); i++ ) {
            char merkki = merkkijono.charAt(i);
            if (okt.indexOf(merkki) > -1) {
                muunnos = "" + merkki;
            } else if (isot.indexOf(merkki) > -1) {
                muunnos = muunnaIsot(merkki, title);
            } else if (skandit.indexOf(merkki) > -1) {
                muunnos = muunnaSkandit(merkki, skandit.indexOf(merkki));
            } else
                muunnos = muunnaMuut(merkki);
            tulos += muunnos;
        }
        return tulos;
    }
    
    private static String muunnaIsot(char merkki, boolean title) {
        if (title) {
            return "{" + merkki + "}";
        } else
            return "" + merkki;
    }
    
    private static String muunnaSkandit(char merkki, int i) {
        return skmuunto[i];
    }

    private static String muunnaMuut(char merkki) {
        return "" + merkki;
    }

}
