package ohtu.kevatpaiva;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Luokka BibTeX-kenttien muodon käsittelyyn.
 *
 * Toistaiseksi toteutettu skandien koodaus BibTeX/LaTeX-muotoon ja
 * isojen kirjainten suojaus.
 *
 * @author ninbarlu
 * @author jgsavola
 */
public class Muunto {
    private static final Pattern isotKirjaimetPattern = Pattern.compile("([\\p{javaUpperCase}])");
    private static final HashMap<String, String> skandiMap = new HashMap<String, String>();

    /**
     * Muunto-luokan staattinen alustus.
     *
     * Lisää tähän LaTex/BibTeX-muunnokset skandeille. (Toki kelpaavat
     * muutkin merkit kuin skandit.
     */
    static {
        skandiMap.put("å", "\\aa");
        skandiMap.put("ä", "\\\"{a}");
        skandiMap.put("ö", "\\\"{o}");
        skandiMap.put("Å", "\\AA");
        skandiMap.put("Ä", "\\\"{A}");
        skandiMap.put("Ö", "\\\"{O}");
    }

    /**
     * Muunna <code>merkkijono</code> BibTeX-muotoon.
     *
     * Jos <code>title</code> on <code>true</code>, tee tarvittavat
     * muunnokset isoille kirjaimmille, kuten BibTeX:n "title"-kenttä vaatii.
     *
     * FIXME: Nyt isojen kirjainten suojaaminen suojaa otsikoissa myös
     * isot skandit, joten skandien suojaamisen jälkeen molemmat suojaukset
     * on käytössä (Ö ==> {Ö} ==> {\"{O}}). Onko tämä ok?
     *
     * @param merkkijono Muunnettava merkkijono.
     * @param title Onko kyseessä otsikkokenttä ("title").
     * @return Muunnettu merkkijono.
     */
    public static String muunnaMuoto(String merkkijono, boolean title) {
        String tulos = merkkijono;

        if (title) {
            tulos = suojaaIsotKirjaimet(tulos);
        }

        tulos = suojaaSkandit(tulos);

        return tulos;
    }

    /**
     * Suojaa isot kirjaimet aaltosuluilla.
     *
     * TODO: Joskus isoa kirjainta ei tarvitse suojata, esim. tekstikentän
     * ensimmäistä kirjainta. Tämän voisi ehkä myöhemmin huomioida, jotta
     * lopputulos olisi helpompi ihmisten käyttöön.
     *
     * @param merkkijono Muunnettava merkkijono.
     * @return Muunnettu merkkijono.
     */
    private static String suojaaIsotKirjaimet(String merkkijono) {
        Matcher m = isotKirjaimetPattern.matcher(merkkijono);
        return m.replaceAll("{$1}");
    }

    /**
     * Suojaa "skandit" yksi kerrallaan.
     *
     * Metodi käyttää staattista <code>skandiMap</code>-taulukkoa.
     *
     * @param merkkijono Suojattava merkkijono.
     * @return Skandisuojattu merkkijono.
     */
    private static String suojaaSkandit(String merkkijono) {
        String tulos = merkkijono;

        for (String skandi : skandiMap.keySet()) {
            tulos = tulos.replace(skandi, skandiMap.get(skandi));
        }

        return tulos;
    }
}
