package domain;

public abstract class Lukuvinkki {
    private String otsikko;
    private String linkki;
    
    public Lukuvinkki(String otsikko, String linkki) {
        this.otsikko = otsikko;
        this.linkki = linkki;
    }

    public String getOtsikko() {
        return otsikko;
    }

    public String getLinkki() {
        return linkki;
    }
    
    public String toString() {
        return otsikko + ", linkki: " + linkki;
    }
}
