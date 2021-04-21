package domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import dao.LukuvinkkiDao;
import domain.suodatus.Ehto;
import domain.suodatus.Kaikki;
import domain.suodatus.SisaltaaTagin;

import java.util.HashMap;
import java.util.Map;

public class Sovellus {

    private LukuvinkkiDao lukuvinkkiDao;
    private LocalDate paivamaara;
    private DateTimeFormatter pvmMuotoilu;

    public Sovellus(LukuvinkkiDao lukuvinkkiDao) {
        this.lukuvinkkiDao = lukuvinkkiDao;
        this.pvmMuotoilu = DateTimeFormatter.ofPattern("yyyy MM dd");
    }

    public List<String> selaaVinkkeja() {
    	List<Vinkki> vinkit = lukuvinkkiDao.listaa();
        return vinkkiListaToString(suodataVinkkeja(new Kaikki(), vinkit));
    }

    public List<String> selaaLuettujaVinkkeja() {
        List<Vinkki> vinkit = lukuvinkkiDao.listaa();
        List<String> lista = new ArrayList<>();
        // Tämän voisi hoitaa Ehto -rajapinnan toteuttavalla LuettuTaiLukematon -metodilla, suodataVinkkeja -metodin läpi.
        // Tällöin lukupäivämäärä jäisi tulostamatta.
        // Ehto suodatin = new Luettu(); // Oletuksena hyväksyy luetut

        for (Vinkki vinkki : vinkit) {
            if (vinkki.getLuettu()) { // suodatin.test(vinkki)
                lista.add(vinkki.toString() + "Luettu: " + vinkki.getluettuPvm() + "\n");
            }
        }
        return lista;
    }

    public List<String> selaaLukemattomiaVinkkeja() {
        List<Vinkki> vinkit = lukuvinkkiDao.listaa();
        List<String> lista = new ArrayList<>();

        for (Vinkki vinkki : vinkit) {
            if (!vinkki.getLuettu()) { // suodatin.test(vinkki)
                lista.add(vinkki.toString());
            }
        }
        return lista;
    }

    public void lisaaVinkki(String otsikko, String linkki, String tagit, String paivamaara) {
        Vinkki vinkki = new Oletus(otsikko.trim().replaceAll(";", ":"), linkki.trim().replaceAll(";", ":"),
                tagit.replaceAll("\\s|;", ""), paivamaara);
        lukuvinkkiDao.lisaa(vinkki);
    }

    public void merkitseLuetuksi(int indeksi) {
        paivamaara = LocalDate.now();
        Vinkki vinkki = lukuvinkkiDao.poista(indeksi);
        vinkki.merkitseLuetuksi(paivamaara.format(pvmMuotoilu));
        lukuvinkkiDao.lisaa(vinkki);
    }

    public Map<String, String> poistaVinkki(int indeksi) {
        Map<String, String> vinkinTiedot = new HashMap<>();
        Vinkki vinkki = lukuvinkkiDao.poista(indeksi);
        if (vinkki != null) {
            vinkinTiedot.put("otsikko", vinkki.getOtsikko());
            vinkinTiedot.put("linkki", vinkki.getLinkki());
            vinkinTiedot.put("tagit", vinkki.getTagit());
            vinkinTiedot.put("lukupvm", vinkki.getluettuPvm());
        }
        return vinkinTiedot;
    }

    public boolean tarkistaOtsikko(String otsikko) {
        return lukuvinkkiDao.listaaOtsikot().contains(otsikko);
    }

    public List<String> listaaOtsikotIdlla() {
        List<String> otsikot = lukuvinkkiDao.listaaOtsikot();
        List<String> lista = new ArrayList<>();
        for (int i = 1; i <= otsikot.size(); i++) {
            lista.add(i + ": " + otsikot.get(i - 1));
        }
        return lista;
    }
    
    public List<String> vinkkiListaToString(List<Vinkki> vinkit) {
    	List<String> lista = new ArrayList<>();
    	for (Vinkki vinkki : vinkit) {
    		lista.add(vinkki.toString());
    	}
    	return lista;
    }

    public List<String> etsiVinkkejaTageilla(List<String> haettavatTagit) {
    	List<Vinkki> vinkit = lukuvinkkiDao.listaa();
    	List<Vinkki> loydetytVinkit = suodataVinkkeja(new SisaltaaTagin(haettavatTagit.get(0)), vinkit);
    	/*Ajattelin, että kun lisätään tai konditionaali, niin tämän seuraavan if:n sisällä voi olla 
    	joku boolean ja == true -tyylinen ratkaisu, siksi rakensin sen näin, että ensiksi tehdään yhden tagin
    	ja sen pohjalta sitten tehdään ja / tai hakuja. Jos haluat tehdä jotenkin ihan toisin, niin siitä vaan
    	muuttamaan!*/
    	if (haettavatTagit.size() > 1) {
    		loydetytVinkit = etsiVinkkejaJaKonditiolla(loydetytVinkit, haettavatTagit);
    	}
        return vinkkiListaToString(loydetytVinkit);
    }
    
    public List<Vinkki> etsiVinkkejaJaKonditiolla(List<Vinkki> vinkit, List<String> haettavatTagit) {
    	List<Vinkki> loydetytVinkit = vinkit;
		for (int i = 1; i < haettavatTagit.size(); i++) {
	    	loydetytVinkit = suodataVinkkeja(new SisaltaaTagin(haettavatTagit.get(i)), loydetytVinkit);
		}
    	return loydetytVinkit;
    }

    public List<Vinkki> suodataVinkkeja(Ehto suodatin, List<Vinkki> vinkit) {
        List<Vinkki> lista = new ArrayList<>();
        for (Vinkki vinkki : vinkit) {
            if (suodatin.test(vinkki)) {
                lista.add(vinkki);
            }
        }
        return lista;
    }

    public boolean tarkistaId(int id) {
        int vinkkiMaara = lukuvinkkiDao.getMaara();
        if (id > vinkkiMaara || id < 1) {
            return false;
        }
        return true;
    }

    public String getOtsikko(int id) {
        return lukuvinkkiDao.listaa().get(id - 1).getOtsikko();
    }
}
