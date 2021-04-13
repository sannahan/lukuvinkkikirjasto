package lukuvinkkikirjasto;

import dao.FileLukuvinkkiDao;
import domain.Sovellus;
import io.StubIO;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;

import static org.junit.Assert.assertEquals;
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
    List<String> syoterivit;
    
    @Before
    public void setup() throws Exception {
        testiKansio = new TemporaryFolder();
        try {
            tiedosto = File.createTempFile("tmp", null);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        lukuvinkit = new FileLukuvinkkiDao(tiedosto);
        syoterivit = new ArrayList<>();
        io = new StubIO(syoterivit); 
    }
    
    @After
    public void tearDown() {
        tiedosto.delete();
    }

    @Given("kayttaja kertoo haluavansa lisata vinkin")
    public void kayttajaKertooHaluavansaLisataVinkin() {
        io.lisaaSyote("2");     
    }
    
    @When("otsikko {string} ja URL {string} annetaan")
    public void kayttajaAntaaOtsikonJaUrlin(String otsikko, String url) {
        io.lisaaSyote(otsikko);
        io.lisaaSyote(url);
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
        app = new Sovellus(io, lukuvinkit);
        app.suorita();
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
        app = new Sovellus(io, lukuvinkit);
        app.suorita();        
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
        app = new Sovellus(io, lukuvinkit);
        app.suorita();
        List<String> tulosteet = io.getTulosteet();
        assertEquals("Komento: ", tulosteet.get(tulosteet.size() - 2));
    }
    
    @Then("listauksesta loytyy vinkki {string} ja linkki {string}") 
    public void listauksestaLoytyyVinkkiJaLinkki(String vinkki, String linkki) {
        app = new Sovellus(io, lukuvinkit);
        app.suorita();        
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
}
