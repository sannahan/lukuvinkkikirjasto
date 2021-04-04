package domain;

public class Oletus implements Vinkki {
    private Tyyppi tyyppi;
    private String otsikko;
    private String linkki;

    public Oletus(String otsikko, String linkki) {
        this.tyyppi = Tyyppi.OLETUS;
        this.otsikko = otsikko;
        this.linkki = linkki;
    }

    public Tyyppi getTyyppi() {
        return this.tyyppi;
    }

    public String getOtsikko() {
        return this.otsikko;
    }

    public String getLinkki() {
        return this.linkki;
    }
}
