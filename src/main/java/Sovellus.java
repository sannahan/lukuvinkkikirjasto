import java.util.ArrayList;
import java.util.Scanner;
//import dao
//import Vinkki

public class Sovellus {
	private IO ui;

	public Sovellus(IO ui) {
		this.ui = ui;
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
//    	ArrayList<Vinkki> vinkit = vinkkien nouto listana daosta;
//    	for (Vinkki vinkki : vinkit) {
//    		vinkki.toString();
//    		System.out.println("********************");
//    	}
	}

	
	public void lisaaVinkki() {
		// System.out.print("Anna lukuvinkin otsikko: ");
		String otsikko = this.ui.nextLine("Anna lukuvinkin otsikko: ");
		// System.out.print("Anna lukuvinkin URL: ");
		String URL = this.ui.nextLine("Anna lukuvinkin URL: ");
		//talleta vinkkiin otsikko ja URL
		// System.out.println("Vinkki lisätty!");
	}
}
