package dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.*;

public class FileLukuvinkkiDao implements LukuvinkkiDao {
    private File tiedosto;
    private List<String> lukuvinkit;

    public FileLukuvinkkiDao(File tiedosto) throws Exception {
        lukuvinkit = new ArrayList<>();
        this.tiedosto = tiedosto;
        lueTiedosto();
    }

    private void lueTiedosto() throws IOException {
        try {
            Scanner lukija = new Scanner(tiedosto);
            while (lukija.hasNextLine()) {
                String lukuvinkki = lukija.nextLine();
                lukuvinkit.add(lukuvinkki);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(tiedosto);
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
                lukuvinkit.add("1;" + oletusVinkki.getOtsikko() + ";" + oletusVinkki.getLinkki() + ";" + oletusVinkki.getTagit() + ";" + oletusVinkki.getLuettu() + ";" + oletusVinkki.getluettuPvm()); // Laitoin oletusvinkin tunnisteeksi nyt 1, mutta tätä voi toki muuttaa.
                break;
        }

        tallenna();

    }

    private void tallenna() {
        try (FileWriter writer = new FileWriter(tiedosto)) {
            for (String lukuvinkki : lukuvinkit) {
                writer.write(lukuvinkki + "\n");
            }
        } catch (Exception e) {
            System.out.println("Virhe!");
        }
    }

    public List<Vinkki> listaa() {
        ArrayList<Vinkki> vinkit = new ArrayList<>();
        for (String vinkki : lukuvinkit) {
            String[] osat = vinkki.split(";");
            switch (osat[0]) {
                // case "2", "3", "4", ...
                default: // eli "1"
                vinkit.add(new Oletus(osat[1], osat[2], osat[3]));
                break;
            }
        }
        return vinkit;
    }
    
    public List<String> listaaOtsikot() {
        List<Vinkki> vinkit = listaa();
        ArrayList<String> otsikot = new ArrayList<>();
        for (Vinkki vinkki: vinkit) {
            otsikot.add(vinkki.getOtsikko());
        }
        return otsikot;
    }

    public Vinkki poista(int tunnus) {
        // tässä oletetaan, että tunnukset numeroidaan listauksessa ykkösestä alkaen
        String poistettu = lukuvinkit.remove(tunnus-1);
        tallenna();
        return teeRivistaVinkki(poistettu);
    }
    
    // TODO: toisteista koodia lisaa()-metodin kanssa
    private Vinkki teeRivistaVinkki(String rivi) {
        String[] osat = rivi.split(";");
        switch (osat[0]) {
            // case "2", "3", "4", ...
            default: // eli "1"
                Vinkki vinkki = new Oletus(osat[1], osat[2], osat[3]);
                return vinkki;
        }
    }

    public int getMaara() {
        return lukuvinkit.size();
    }
}