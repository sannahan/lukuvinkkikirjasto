package Lukuvinkkikirjasto.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Lukuvinkkikirjasto.Lukuvinkki;

public class FileLukuvinkkiDao implements LukuvinkkiDao {
    private String tiedosto;
    private List<String> lukuvinkit;

    public FileLukuvinkkiDao(String tiedosto) throws Exception {
        lukuvinkit = new ArrayList<>();
        this.tiedosto = tiedosto;
        try {
            Scanner lukija = new Scanner(new File(tiedosto));
            while (lukija.hasNextLine()) {
                String lukuvinkki = lukija.nextLine();
                lukuvinkit.add(lukuvinkki);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(tiedosto));
            writer.close();
        }        
    }

    @Override
    public void lisaa(Lukuvinkki lukuvinkki) {
        String[] palat = lukuvinkki.toString().split(";");
        lukuvinkit.add(palat[0] + ";" + palat[1]);
        tallenna();
    }

    private void tallenna() {
        try (FileWriter writer = new FileWriter(new File(tiedosto))) {
            for (String lukuvinkki : lukuvinkit) {
                writer.write(lukuvinkki + "\n");
            }
        }
        catch (Exception e) {
            System.out.println("Virhe!");
        }  
    }

}
