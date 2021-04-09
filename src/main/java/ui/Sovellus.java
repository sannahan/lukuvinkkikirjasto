package ui;

import ui.IO;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//import dao
//import Vinkki

import dao.LukuvinkkiDao;
import domain.*;

public class Sovellus {
	private IO ui;
	private LukuvinkkiDao lukuvinkkiDao;

	public Sovellus(IO ui, LukuvinkkiDao lukuvinkkiDao) {
		this.ui = ui;
		this.lukuvinkkiDao = lukuvinkkiDao;
	}

	public void suorita() {
		this.ui.print("Valitse toiminto syöttämällä numero:");
		this.printInfo();

		while (true) {
			int komento = this.ui.nextInt("Komento: ");

			if (komento == -2) {
				this.ui.error("Ei numero! Syötä numero komentovalikosta");
				printInfo();
			}

			else if (komento == 0) {
				this.printInfo();
			}

			else if (komento == 1) {
				selaaVinkkeja();
			}

			else if (komento == 2) {
				lisaaVinkki();
			}

			else if (komento == 3) {
				poistaVinkki();
			}

			else if (komento == -1) {
				this.ui.print("Hei hei!");
				break;
			}

			else {
				this.ui.error("Komentoa ei löydy!");
				printInfo();
			}
		}
	}

	public void printInfo() {
		this.ui.print(" 0: Info");
		this.ui.print(" 1: Selaa vinkkejä");
		this.ui.print(" 2: Lisää uusi vinkki");
		this.ui.print(" 3: Poista vinkki");
		this.ui.print("-1: Poistu");
	}

	public void selaaVinkkeja() {
		List<Vinkki> vinkit = lukuvinkkiDao.listaa();
		for (Vinkki vinkki : vinkit) {
			this.ui.print(vinkki.toString());
		}
	}

	public void lisaaVinkki() {
		// System.out.print("Anna lukuvinkin otsikko: ");
		String otsikko = this.ui.nextLine("Anna lukuvinkin otsikko: ");
		// System.out.print("Anna lukuvinkin URL: ");
		if (lukuvinkkiDao.listaaOtsikot().contains(otsikko)) {
			this.ui.error("Syöttämälläsi otsikolla löytyy jo vinkki. Syötä uniikki otsikko");
			otsikko = this.ui.nextLine("Anna lukuvinkin otsikko: ");
		}
		String URL = this.ui.nextLine("Anna lukuvinkin URL: ");
		Vinkki vinkki = new Oletus(otsikko, URL);
		lukuvinkkiDao.lisaa(vinkki);
		// System.out.println("Vinkki lisätty!");
	}

	public void poistaVinkki() {
		List<String> otsikot = tulostaOtsikotIdlla();
		if (otsikot.isEmpty()) {
			System.out.println("Ei poistettavia vinkkejä!");
		} else {
			int poistettava = this.ui.nextInt("Anna poistettavan vinkin id-numero:");
			String vastaus = this.ui
					.nextLine("Haluatko varmasti poistaa vinkin " + otsikot.get(poistettava - 1) + " (kyllä/ei)");
			if (vastaus.equals("kyllä")) {
				lukuvinkkiDao.poista(poistettava);
				System.out.println("Vinkki poistettu");
			} else {
				System.out.println("Vinkkiä ei poistettu");
			}
		}
	}

	public List<String> tulostaOtsikotIdlla() {
		List<String> otsikot = lukuvinkkiDao.listaaOtsikot();
		for (int i = 0; i < otsikot.size(); i++) {
			int index = i + 1;
			System.out.println(index + ": " + otsikot.get(i));
		}
		return otsikot;
	}
}
