package dao;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import domain.*;

public class FileLukuvinkkiDaoTest {
    @Rule
    public TemporaryFolder testiKansio = new TemporaryFolder();   

    File tiedosto;
    FileLukuvinkkiDao lukuvinkit;
    Vinkki vinkki;

    @Before
    public void setUp() throws Exception {
        vinkki = new Oletus("otsikko", "linkki", "tagi1,tagi2,tagi3", "null");
        try {
            tiedosto = File.createTempFile("tmp", null);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        lukuvinkit = new FileLukuvinkkiDao(tiedosto);
    }

    @After
    public void tearDown() {
        tiedosto.delete();
    }

    @Test
    public void vinkinLisaysOnnistuu() {
        lukuvinkit.lisaa(vinkki);
        List<Vinkki> vinkit = lukuvinkit.listaa();
        assertEquals(Tyyppi.OLETUS, vinkit.get(0).getTyyppi());
        Oletus testi = (Oletus)vinkit.get(0);
        assertEquals("otsikko", testi.getOtsikko());
        assertEquals("linkki", testi.getLinkki());
    }

    @Test
    public void vinkkienListausOnnistuu() {
        lukuvinkit.lisaa(vinkki);
        lukuvinkit.lisaa(new Oletus("toinen vinkki", "linkki2", "tagi1,tagi2,tagi3", "null"));
        lukuvinkit.lisaa(new Oletus("kolmas vinkki", "linkki3", "tagi1,tagi2,tagi3", "null"));
        List<Vinkki> vinkit = lukuvinkit.listaa();
        assertEquals(3, vinkit.size());
    }
    
    @Test
    public void vinkkienListausPalauttaaVinkitKaanteisessaJarjestyksessa() {
        lukuvinkit.lisaa(vinkki);
        lukuvinkit.lisaa(new Oletus("toinen vinkki", "linkki2", "tagi1,tagi2,tagi3", "null"));
        lukuvinkit.lisaa(new Oletus("kolmas vinkki", "linkki3", "tagi1,tagi2,tagi3", "null"));
        List<Vinkki> vinkit = lukuvinkit.listaa();
        assertEquals("kolmas vinkki", vinkit.get(0).getOtsikko());
    }

    @Test
    public void vinkinPoistoOnnistuu() {
        lukuvinkit.lisaa(vinkki);
        lukuvinkit.lisaa(new Oletus("toinen vinkki", "linkki2", "tagi1,tagi2,tagi3", "null"));
        lukuvinkit.lisaa(new Oletus("kolmas vinkki", "linkki3", "tagi1,tagi2,tagi3", "null"));
        lukuvinkit.poista(1);
        List<Vinkki> vinkit = lukuvinkit.listaa();
        assertEquals(2, vinkit.size());
    }

    @Test
    public void poistoPoistaaHalutunVinkin() {
        lukuvinkit.lisaa(vinkki);
        lukuvinkit.lisaa(new Oletus("toinen vinkki", "linkki2", "tagi1,tagi2,tagi3", "null"));
        lukuvinkit.lisaa(new Oletus("kolmas vinkki", "linkki3", "tagi1,tagi2,tagi3", "null"));
        lukuvinkit.poista(1);
        List<Vinkki> vinkit = lukuvinkit.listaa();
        Oletus testi = (Oletus)vinkit.get(0);
        assertEquals("toinen vinkki", testi.getOtsikko());
        assertEquals("linkki2", testi.getLinkki());
    }

    @Test
    public void listaaOtsikotListaaOtsikotKaanteisessaJarjestyksessa() {
        lukuvinkit.lisaa(vinkki);
        lukuvinkit.lisaa(new Oletus("toinen vinkki", "linkki2", "tagi1,tagi2,tagi3", "null"));
        lukuvinkit.lisaa(new Oletus("kolmas vinkki", "linkki3", "tagi1,tagi2,tagi3", "null"));
        List<String> otsikot = lukuvinkit.listaaOtsikot();
        assertEquals("otsikko", otsikot.get(2));
        assertEquals("toinen vinkki", otsikot.get(1));
        assertEquals("kolmas vinkki", otsikot.get(0));
    }

}
