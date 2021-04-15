package domain;

import java.util.ArrayList;
import java.util.List;
import dao.LukuvinkkiDao;
import java.util.HashMap;
import java.util.Map;

public class Sovellus {
	private LukuvinkkiDao lukuvinkkiDao;

	public Sovellus(LukuvinkkiDao lukuvinkkiDao) {
		this.lukuvinkkiDao = lukuvinkkiDao;
	}

	public List<String> selaaVinkkeja() {
		List<Vinkki> vinkit = lukuvinkkiDao.listaa();
		List<String> lista = new ArrayList<>();
		for (Vinkki vinkki : vinkit) {
			lista.add(vinkki.toString());
		}
		return lista;
	}

	public void lisaaVinkki(String otsikko, String URL, String tagit) {
		Vinkki vinkki = new Oletus(otsikko, URL, tagit);
		lukuvinkkiDao.lisaa(vinkki);
	}

	public Map<String, String> poistaVinkki(int indeksi) {
                Map<String, String> vinkinTiedot = new HashMap<>();
		Vinkki vinkki = lukuvinkkiDao.poista(indeksi);
                if (vinkki != null) {
                    vinkinTiedot.put("otsikko", vinkki.getOtsikko());
                    vinkinTiedot.put("linkki", vinkki.getLinkki());
                    vinkinTiedot.put("tagit", vinkki.getTagit());
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