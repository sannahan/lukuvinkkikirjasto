package lukuvinkkikirjasto;

import java.io.File;
import java.util.Scanner;

import dao.*;
import domain.Sovellus;
import io.IO;
import io.TextIO;
import ui.TextUI;

public class Main {
    public static void main(String[] args) throws Exception {
        IO textIO = new TextIO(new Scanner(System.in));

        LukuvinkkiDao lukuvinkkiDao = new FileLukuvinkkiDao(new File("testi.txt"));
        // LukuvinkkiDao lukuvinkkiDao = new FileLukuvinkkiDao(new File("demo.txt"));
        
        Sovellus sovellus = new Sovellus(lukuvinkkiDao);
        TextUI kayttoliittyma = new TextUI(textIO, sovellus);

        kayttoliittyma.suorita();
    }
}