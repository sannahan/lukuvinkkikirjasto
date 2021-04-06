package dao;

import java.util.List;

import domain.Vinkki;

public interface LukuvinkkiDao {
    void lisaa(Vinkki vinkki);
    List<Vinkki> listaa();
    void poista(int tunnus);
    List<String> listaaOtsikot();
}
