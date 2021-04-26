package lukuvinkkikirjasto;

import java.io.File;
import java.util.Scanner;

import dao.*;
import domain.Sovellus;
import domain.UrlDataService;
import domain.UrlDataServiceImpl;
import io.IO;
import io.TextIO;
import java.io.FileInputStream;
import java.util.Properties;
import ui.TextUI;

public class Main {
    public static void main(String[] args) throws Exception {
        IO textIO = new TextIO(new Scanner(System.in));
        UrlDataService urlService = new UrlDataServiceImpl();
        
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        // String usedFile = properties.getProperty("demoFile");
        String usedFile = properties.getProperty("testFile");

        LukuvinkkiDao lukuvinkkiDao = new FileLukuvinkkiDao(new File(usedFile));
        
        Sovellus sovellus = new Sovellus(lukuvinkkiDao);
        TextUI kayttoliittyma = new TextUI(textIO, sovellus, urlService);

        kayttoliittyma.suorita();
    }
}