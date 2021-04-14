package dao;

import java.util.List;

import domain.Vinkki;

public interface LukuvinkkiDao {
    void lisaa(Vinkki vinkki);
    List<Vinkki> listaa();
    Vinkki poista(int tunnus);
    List<String> listaaOtsikot();
    int getMaara();
}
