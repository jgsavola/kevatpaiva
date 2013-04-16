package ohtu.kevatpaiva;

/**
 * Tältä tehtaalta saadaan alustettuja Kentta-olioita ViiteTyypin avulla.
 */
public class KenttaTehdas {
    private static ViiteTyyppi viiteTyyppi;

    public KenttaTehdas(ViiteTyyppi viiteTyyppi) {
        this.viiteTyyppi = viiteTyyppi;
    }

    public Kentta luoKentta(String nimi, String arvo) {
        KenttaTyyppi kenttaTyyppi = viiteTyyppi.getKenttaTyyppi(nimi);
        if (kenttaTyyppi == null) {
            throw new IllegalArgumentException("Tuntematon kentän nimi " + nimi + " viitetyypissä " + viiteTyyppi.getNimi());
        }

        return new Kentta(kenttaTyyppi, arvo);
    }
}
