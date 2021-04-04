import java.util.Scanner;

import dao.FileLukuvinkkiDao;
import dao.LukuvinkkiDao;

public class Main {
    public static void main(String[] args) throws Exception {
        IO textUI = new TextUI(new Scanner(System.in));
        LukuvinkkiDao lukuvinkkiDao = new FileLukuvinkkiDao("testi.txt");

        Sovellus app = new Sovellus(textUI, lukuvinkkiDao);
        app.suorita();
    }
}