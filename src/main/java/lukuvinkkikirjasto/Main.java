package lukuvinkkikirjasto;

import ui.TextUI;
import ui.Sovellus;
import ui.IO;
import java.io.File;
import java.util.Scanner;

import dao.*;

public class Main {
    public static void main(String[] args) throws Exception {
        IO textUI = new TextUI(new Scanner(System.in));
        LukuvinkkiDao lukuvinkkiDao = new FileLukuvinkkiDao(new File("testi.txt"));

        Sovellus app = new Sovellus(textUI, lukuvinkkiDao);
        app.suorita();
    }
}