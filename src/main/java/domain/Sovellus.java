package domain;

import java.util.ArrayList;
import java.util.List;
import dao.LukuvinkkiDao;

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

	public void poistaVinkki(int indeksi) {
		lukuvinkkiDao.poista(indeksi);
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
}
