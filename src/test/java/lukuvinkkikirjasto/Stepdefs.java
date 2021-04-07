package lukuvinkkikirjasto;

import dao.FileLukuvinkkiDao;
import ui.StubIO;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.rules.TemporaryFolder;
import ui.Sovellus;

public class Stepdefs {
    Sovellus app;
    TemporaryFolder testiKansio;
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
    
    @Given("kayttaja kertoo haluavansa lisata vinkin")
    public void kayttajaKertooHaluavansaLisataVinkin() {
        io.lisaaSyote("2");     
    }
    
    @When("otsikko {string} ja URL {string} annetaan")
    public void kayttajaAntaaOtsikonJaUrlin(String otsikko, String url) {
        io.lisaaSyote(otsikko);
        io.lisaaSyote(url);
    }
    
    @When("annetaan lopetuskomento")
    public void kayttajaAntaaLopetuskomennon() {
        io.lisaaSyote("-1");        
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
}
