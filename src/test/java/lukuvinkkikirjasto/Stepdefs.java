package lukuvinkkikirjasto;

import dao.AutotagDao;
import dao.FileLukuvinkkiDao;
import dao.HashMapAutotagDao;
import domain.*;
import domain.Sovellus;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ui.TextUI;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class Stepdefs {
    
    @Rule
    public TemporaryFolder testiKansio = new TemporaryFolder();
    
    Sovellus app;
    File tiedosto;
    StubIO io;
    FileLukuvinkkiDao lukuvinkit;
    AutotagDao autotagDao;
    List<String> syoterivit;
    TextUI ui;
    
    @Before
    public void setup() throws Exception {
        testiKansio = new TemporaryFolder();
        try {
            tiedosto = File.createTempFile("tmp", null);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        lukuvinkit = new FileLukuvinkkiDao(tiedosto);
        lukuvinkit.lisaa(new Oletus("Testiotsikko", "Testilinkki", "Testitägi", "null"));
        autotagDao = new HashMapAutotagDao();
        syoterivit = new ArrayList<>();
        io = new StubIO(syoterivit);
        app = new Sovellus(lukuvinkit, autotagDao);
        // TODO tälle pitää tehdä stub
        UrlDataService urlService = new UrlDataServiceStub();
        ui = new TextUI(io, app, urlService);
    }
    
    @After
    public void tearDown() {
        tiedosto.delete();
    }

    @Given("kayttaja kertoo haluavansa lisata vinkin")
    public void kayttajaKertooHaluavansaLisataVinkin() {
        io.lisaaSyote("2");     
    }
    
    @When("otsikko {string}, URL {string} ja tagi {string} annetaan")
    public void kayttajaAntaaOtsikonJaUrlin(String otsikko, String url, String tagi) {
        io.lisaaSyote(url);
        io.lisaaSyote(otsikko);
        io.lisaaSyote(tagi);
    }

    @When("kayttaja kertoo haluavansa selata vinkkeja")
    public void kayttajaHaluaaSelataVinkkeja() {
        io.lisaaSyote("1");
    }
       
    @When("annetaan lopetuskomento")
    public void kayttajaAntaaLopetuskomennon() {
        io.lisaaSyote("-1");        
    }
    
    @When("kayttaja antaa idn 220")
    public void kayttajaAntaaOlemattomanKomennon() {
        io.lisaaSyote("220");
    }

    @Then("sovellus suorittaa ja lopettaa")
    public void sovellusSuorittaaJaLopettaa() {
        ui.suorita();
        boolean sisaltaako = false;
        for (String s: io.getTulosteet()) {
            if (s.contains("hei")) {
                sisaltaako = true;
            }
        }
        
        assertTrue(sisaltaako);
    }
    
    @Then("sovellus vastaa {string}")
    public void sovellusVastaaOdotetulla(String expectedOutput) {
        io.lisaaSyote("-1");
        ui.suorita();        
        //assertTrue(io.getTulosteet().contains(expectedOutput));
        boolean sisaltaako = false;
        for (String s: io.getTulosteet()) {
            if (s.contains(expectedOutput)) {
                sisaltaako = true;
            }
        }
        
        assertTrue(sisaltaako);
    }
    
    @Then("sovellus kysyy käyttäjältä uutta komentoa")
    public void sovellusKysyyKomentoa() {
        io.lisaaSyote("-1");
        ui.suorita();
        List<String> tulosteet = io.getTulosteet();
        assertEquals("Komento: ", tulosteet.get(tulosteet.size() - 2));
    }
    
    @Then("listauksesta loytyy vinkki {string} ja linkki {string}") 
    public void listauksestaLoytyyVinkkiJaLinkki(String vinkki, String linkki) {
        io.lisaaSyote("-1");
        ui.suorita();  
        boolean sisVinkin = false;
        boolean sisLinkin = false;
        for (String s: io.getTulosteet()) {
            if (s.contains(vinkki)) {
                sisVinkin = true;
            }
            if (s.contains(linkki)) {
                sisLinkin = true;
            }
        }
        boolean molemmatLoytyy = false;
        if (sisVinkin && sisLinkin) {
            molemmatLoytyy = true;
        }
        assertTrue(molemmatLoytyy);        
    }
    
    @Given("kayttaja kertoo haluavansa muokata vinkkia") 
    public void kayttajaKertooHaluavansaMuokataVinkkia() {
        io.lisaaSyote("4");
    }
    
    @When("kayttaja kirjoittaa {string}")
    public void kayttajaKirjoittaa(String id) {
        io.lisaaSyote(id);
    }

    @When("kayttaja valitsee listalta vinkin")
    public void kayttajaValitseeListaltaVinkin() {
        io.lisaaSyote("1");
    }
    
    @Then("listauksesta ei loydy vinkkia {string} ja linkkia {string}")
    public void listauksestaEiLoydyVinkkiaJaLinkkia(String vinkki, String linkki) { 
        boolean sisVinkin = false;
        boolean sisLinkin = false;
        for (String s: io.getTulosteet()) {
            if (s.contains("Vinkki: " + vinkki)) {
                sisVinkin = true;
            }
            if (s.contains("Linkki: " + linkki)) {
                sisLinkin = true;
            }
        }
        boolean molemmatLoytyy = false;
        if (sisVinkin && sisLinkin) {
            molemmatLoytyy = true;
        }
        assertFalse(molemmatLoytyy); 
    }

    @Given("kayttaja kertoo haluavansa etsia vinkkeja tagilla")
    public void kayttajaKertooHaluavansaEtsiaVinkkejaTagilla() {
        io.lisaaSyote("8");
    }

    @Given("vinkki {string}, URL {string} ja tagi {string} on listalla")
    public void vinkkiURLJaTagiOnListalla(String otsikko, String url, String tagi) {
        io.lisaaSyote("2");
        io.lisaaSyote(url);
        io.lisaaSyote(otsikko);
        io.lisaaSyote(tagi);
    }

    /*@And("kayttaja kirjoittaa {string}")
    public void kayttajaAntaaSyotteen(String tagi) {
        io.lisaaSyote(tagi);
    }*/
    
    @Given("kayttaja kertoo haluavansa merkita vinkin luetuksi")
    public void kayttajaKertooHaluavansaMerkitaVinkinLuetuksi() {
        io.lisaaSyote("5");
    }
    
    @Then("kayttaja kertoo haluavansa selata luettuja vinkkeja") 
    public void kayttajaKertooHaluavansaSelataLuettujaVinkkeja() {
        io.lisaaSyote("7");
    }

    @Given("sovellukseen on lisatty vinkkeja")
    public void sovellukseenOnLisattyVinkkejaJoitaEiOleLuettu() {
        lukuvinkit.lisaa(new Oletus("Lehtijuttu", "www.hs.fi/juttu", "sanomalehti,juttu", "null"));
        lukuvinkit.lisaa(new Oletus("Videolinkki", "www.youtube.com/", "parhautta", "null"));
        io.lisaaSyote("5");
        io.lisaaSyote("2");
    }
    
    @When("kayttaja kertoo haluavansa selata vinkkeja joita ei ole luettu")
    public void kayttajaKertooHaluavansaSelataLukemattomiaVinkkeja() {
        io.lisaaSyote("6");
    }

    @Then("sovellus listaa lukemattomat vinkit") 
    public void lukemattomatLoytyvatListalta() {
        listauksestaLoytyyVinkkiJaLinkki("Testiotsikko", "Testilinkki");
        listauksestaLoytyyVinkkiJaLinkki("Videolinkki", "www.youtube.com/");
    }

    @Then("sovellus ei listaa luettuja vinkkeja") 
    public void luetutEivatLoydyListalta() {
        listauksestaEiLoydyVinkkiaJaLinkkia("Lehtijuttu", "www.hs.fi/juttu");
    }

    @Then("sovellus listaa luetut vinkit") 
    public void luetutLoytyvatListalta() {
        listauksestaLoytyyVinkkiJaLinkki("Lehtijuttu", "www.hs.fi/juttu");
    }

    @Then("sovellus ei listaa lukemattomia vinkkeja") 
    public void lukemattomatEivatLoydyListalta() {
        listauksestaEiLoydyVinkkiaJaLinkkia("Testiotsikko", "Testilinkki");
        listauksestaEiLoydyVinkkiaJaLinkkia("Videolinkki", "www.youtube.com/");
    }
    
    @Then("sovellus tallentaa oikean paivamaara")
    public void listauksestaLoytyyOikeaPaivamaara() {
        io.lisaaSyote("7");
        io.lisaaSyote("-1");
        ui.suorita();
        LocalDate paivamaara = LocalDate.now();
        DateTimeFormatter pvmMuotoilu = DateTimeFormatter.ofPattern("d.M.yyyy");
        String pvm = paivamaara.format(pvmMuotoilu);
        boolean sisPvm = false;
        for (String s: io.getTulosteet()) {
            if (s.contains(pvm)) {
                sisPvm = true;
            }
        }
        assertTrue(sisPvm);
    }
    
    @Then("viimeisin lisays tulostetaan ensimmaisena")
    public void viimeisinLisaysTulostetaanEnsimmaisena() {
        io.lisaaSyote("-1");
        ui.suorita();
        int uusimmanOtsikonIndeksi = -1;
        int toiseksiUusimmanOtsikonIndeksi = -2;
        List<String> tulosteet = io.getTulosteet();
        for (int i = 0; i < tulosteet.size(); i++) {
            if (tulosteet.get(i).contains("Uusin otsikko")) {
                uusimmanOtsikonIndeksi = i;
            }
            if (tulosteet.get(i).contains("Toiseksi uusin otsikko")) {
                toiseksiUusimmanOtsikonIndeksi = i;
            }
        }
        assertTrue(uusimmanOtsikonIndeksi < toiseksiUusimmanOtsikonIndeksi);
    }
    
    @Then("viimeisin muokkaus tulostetaan ensimmaisena")
    public void viimeisinMuokkausTulostetaanEnsimmaisena() {
        io.lisaaSyote("-1");
        ui.suorita();
        int uusimmanOtsikonIndeksi = -1;
        int toiseksiUusimmanOtsikonIndeksi = -2;
        List<String> tulosteet = io.getTulosteet();
        for (int i = 0; i < tulosteet.size(); i++) {
            if (tulosteet.get(i).contains("Muokattu otsikko")) {
                uusimmanOtsikonIndeksi = i;
            }
            if (tulosteet.get(i).contains("Muokkaamaton otsikko")) {
                toiseksiUusimmanOtsikonIndeksi = i;
            }
        }
        assertTrue(uusimmanOtsikonIndeksi < toiseksiUusimmanOtsikonIndeksi);
    }

    @Then("lisatyn vinkin tageista loytyy {string}") 
    public void tarkistaTagi(String tagi) {
        io.lisaaSyote("1");
        io.lisaaSyote("-1");
        ui.suorita();
        List<String> tulosteet = io.getTulosteet();
        assertTrue(tulosteet.get(tulosteet.size() - 4).contains(tagi));
    }

    @Then("lisatyn vinkin tagit ovat {string}") 
    public void tarkistaVinkinKaikkiTagit(String tagit) {
        io.lisaaSyote("1");
        io.lisaaSyote("-1");
        ui.suorita();
        List<String> tulosteet = io.getTulosteet();
        assertTrue(tulosteet.get(tulosteet.size() - 4).contains(tagit));
        assertFalse(tulosteet.get(tulosteet.size() - 4).contains(tagit + "#video"));
    }
}
