package Lukuvinkkikirjasto;

import java.util.Scanner;

import Lukuvinkkikirjasto.dao.FileLukuvinkkiDao;
import Lukuvinkkikirjasto.dao.LukuvinkkiDao;

public class Main {
    public static void main(String[] args) throws Exception {
        IO textUI = new TextUI(new Scanner(System.in));

        textUI.print("Hello Lukuvinkkikirjasto!");

        String komento = textUI.nextLine("Komento :");
        textUI.error("Komentoa \"" + komento + "\" ei löydy!");

        LukuvinkkiDao testiDao = new FileLukuvinkkiDao("testi.txt");
        testiDao.lisaa(new Lukuvinkki("Lukuvinkki 1", "http://linkki1.link"));
        testiDao.lisaa(new Lukuvinkki("Lukuvinkki 2", "http://linkki2.link"));
    }
}