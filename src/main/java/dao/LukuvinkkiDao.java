package dao;

import java.util.List;

import domain.Vinkki;
import domain.Lukuvinkki;

public interface LukuvinkkiDao {
    // TODO kumpaa tapaa halutaan käyttää?
    // boolean lisaa(Lukuvinkki lukuvinkki);
    void lisaa(Vinkki vinkki);
    List<Vinkki> listaa();
}
