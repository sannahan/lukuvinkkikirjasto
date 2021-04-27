package domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dao.AutotagDao;
import dao.LukuvinkkiDao;
import domain.suodatus.*;

import java.util.HashMap;
import java.util.Map;

public class Sovellus {

    private LukuvinkkiDao lukuvinkkiDao;
    private AutotagDao autotagDao;
    private LocalDate paivamaara;
    private DateTimeFormatter pvmMuotoilu;

    public Sovellus(LukuvinkkiDao lukuvinkkiDao, AutotagDao autotagDao) {
        this.lukuvinkkiDao = lukuvinkkiDao;
        this.pvmMuotoilu = DateTimeFormatter.ofPattern("d.M.yyyy");
        this.autotagDao = autotagDao;
    }

    public List<String> selaaVinkkeja() {
    	List<Vinkki> vinkit = lukuvinkkiDao.listaa();
        return vinkkiListaToString(suodataVinkkeja(new Kaikki(), vinkit));
    }

    public List<String> selaaLuettujaVinkkeja() {
        List<Vinkki> vinkit = lukuvinkkiDao.listaa();
        return vinkkiListaToString(suodataVinkkeja(new LuettuTaiLukematon(), vinkit));
    }

    public List<String> selaaLukemattomiaVinkkeja() {
        List<Vinkki> vinkit = lukuvinkkiDao.listaa();
        return vinkkiListaToString(suodataVinkkeja(new LuettuTaiLukematon(false), vinkit));
    }

    public void lisaaVinkki(String otsikko, String linkki, String tagit, String paivamaara) {
        tagit = autoTagaa(linkki, tagit);
        Vinkki vinkki = new Oletus(otsikko.replaceAll(";", ":"), linkki.replaceAll(";", ":"),
                tagit, paivamaara);
        lukuvinkkiDao.lisaa(vinkki);
    }

    private String autoTagaa(String linkki, String tagit) {
        Map<String, String> mappaukset = autotagDao.haeMappaukset();
        for (String sivusto : mappaukset.keySet()) {
            if (linkki.contains(sivusto) && !tagit.contains(mappaukset.get(sivusto))) {
                tagit += (tagit.isEmpty()) ? mappaukset.get(sivusto) : "," + mappaukset.get(sivusto);
            } 
        }
        return tagit;
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

    public List<String> etsiVinkkejaYhdellaTagilla(List<String> haettavatTagit) {
    	List<Vinkki> vinkit = lukuvinkkiDao.listaa();
    	List<Vinkki> loydetytVinkit = suodataVinkkeja(new SisaltaaTagin(haettavatTagit.get(0)), vinkit);
        return vinkkiListaToString(loydetytVinkit);
    }
    
    public List<String> etsiVinkkejaJaKonditiolla(List<String> haettavatTagit) {
    	List<Vinkki> vinkit = lukuvinkkiDao.listaa();
    	List<Vinkki> loydetytVinkit = suodataVinkkeja(new SisaltaaKaikkiAnnetutTagit(haettavatTagit), vinkit);
        return vinkkiListaToString(loydetytVinkit);
    }

    public List<String> etsiVinkkejaTaiKonditiolla(List<String> haettavatTagit) {
    	List<Vinkki> vinkit = lukuvinkkiDao.listaa();
    	List<Vinkki> loydetytVinkit = suodataVinkkeja(new SisaltaaJonkunAnnetuistaTageista(haettavatTagit), vinkit);
        return vinkkiListaToString(loydetytVinkit);
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
