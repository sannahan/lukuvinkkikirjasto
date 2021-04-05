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

			if (komento == 0) {
				this.printInfo();
			}
			
			else if (komento == 1) {
				selaaVinkkeja();
			}

			else if (komento == 2) {
				lisaaVinkki();
			}
			
			else if (komento == -1) {
				this.ui.print("Hei hei!");
				break;
			}

			else {
				this.ui.error("Komentoa ei löydy!");
			}
		}
	}
	
	public void printInfo() {
		this.ui.print(" 0: Info");
		this.ui.print(" 1: Selaa vinkkejä");
		this.ui.print(" 2: Lisää uusi vinkki");
		this.ui.print("-1: Poistu");
	}

	
	public void selaaVinkkeja() {
    	List<Vinkki> vinkit = lukuvinkkiDao.listaa();
    	for (Vinkki vinkki : vinkit) {
    		System.out.println(vinkki.toString());
    	}
	}

	
	public void lisaaVinkki() {
		// System.out.print("Anna lukuvinkin otsikko: ");
		String otsikko = this.ui.nextLine("Anna lukuvinkin otsikko: ");
		// System.out.print("Anna lukuvinkin URL: ");
		String URL = this.ui.nextLine("Anna lukuvinkin URL: ");
		Vinkki vinkki = new Oletus(otsikko, URL);
		lukuvinkkiDao.lisaa(vinkki);
		// System.out.println("Vinkki lisätty!");
	}
}
