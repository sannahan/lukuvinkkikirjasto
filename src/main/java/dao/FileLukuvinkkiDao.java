package dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.Oletus;
import domain.Vinkki;

public class FileLukuvinkkiDao implements LukuvinkkiDao {
    private String tiedosto;
    private List<String> lukuvinkit;

    public FileLukuvinkkiDao(String tiedosto) throws Exception {
        lukuvinkit = new ArrayList<>();
        this.tiedosto = tiedosto;
        lueTiedosto();
    }

    private void lueTiedosto() throws IOException {
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
    public void lisaa(Vinkki vinkki) {
        switch (vinkki.getTyyppi()) {
            // case KIRJA:
            // case YOUTUBE:
            // case KADUNMIES:
            default:
                Oletus oletusVinkki = (Oletus) vinkki;
                lukuvinkit.add(oletusVinkki.getOtsikko() + ";" + oletusVinkki.getLinkki());
                break;
        }

        tallenna();

    }

    private void tallenna() {
        try (FileWriter writer = new FileWriter(new File(tiedosto))) {
            for (String lukuvinkki : lukuvinkit) {
                writer.write(lukuvinkki + "\n");
            }
        } catch (Exception e) {
            System.out.println("Virhe!");
        }
    }

}