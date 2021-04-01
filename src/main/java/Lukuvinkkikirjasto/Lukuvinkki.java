package Lukuvinkkikirjasto;

public class Lukuvinkki {
    private String otsikko;
    private String linkki;

    public Lukuvinkki(String otsikko, String linkki) {
        this.otsikko = otsikko;
        this.linkki = linkki;
    }

    @Override
    public String toString() {
        return otsikko + " " + linkki;
    }
}
