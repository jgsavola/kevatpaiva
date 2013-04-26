package ohtu.kevatpaiva;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Luokka BibTeX-kenttien muodon käsittelyyn. 
 * 
 * Skandien koodaus BibTeX/LaTeX-muotoon ja isojen kirjainten suojaus viitteen otsikossa.
 *
 * @author  Kevätpäivä
 * @since   26.4.2013
 */
public class Muunto {
    private static final Pattern isotKirjaimetPattern = Pattern.compile("([\\p{javaUpperCase}])");
    private static final HashMap<String, String> skandiMap = new HashMap<String, String>();

    /**
     * Muunto-luokan staattinen alustus.
     *
     * Lisää tähän LaTex/BibTeX-muodon vaatimat muunnokset.
     */
    static {
        skandiMap.put("\u00e5", "\\aa");
        skandiMap.put("\u00e4", "\\\"{a}");
        skandiMap.put("\u00f6", "\\\"{o}");
        skandiMap.put("\u00c5", "\\AA");
        skandiMap.put("\u00c4", "\\\"{A}");
        skandiMap.put("\u00d6", "\\\"{O}");
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
     * @param title Viitteen otsikkokenttä.
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
