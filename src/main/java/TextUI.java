import java.util.ArrayList;
import java.util.Scanner;
//import dao
//import Vinkki

public class TextUI implements IO {
	private Scanner scanner;

	public TextUI(Scanner scanner) {
		this.scanner = scanner;
	}

	@Override
	public void komento() {
		while (true) {
			System.out.println("Valitse toiminto syöttämällä numero:");
			System.out.println("1: Selaa vinkkejä");
			System.out.println("2: Lisää uusi vinkki");
			System.out.println("-1: Poistu");
			int komento = Integer.valueOf(scanner.nextLine());
			
			if (komento == 1) {
				selaaVinkkeja();
			}

			else if (komento == 2) {
				lisaaVinkki();
			}
			
			else if (komento == -1) {
				System.out.println("Hei hei!");
				break;
			}

			else {
				System.out.println("Ei sallittu komento!");
			}
		}
	}

	@Override
	public void selaaVinkkeja() {
//    	ArrayList<Vinkki> vinkit = vinkkien nouto listana daosta;
//    	for (Vinkki vinkki : vinkit) {
//    		vinkki.toString();
//    		System.out.println("********************");
//    	}
	}

	@Override
	public void lisaaVinkki() {
		System.out.print("Anna lukuvinkin otsikko: ");
		String otsikko = scanner.nextLine();
		System.out.print("Anna lukuvinkin URL: ");
		String URL = scanner.nextLine();
		//talleta vinkkiin otsikko ja URL
		System.out.println("Vinkki lisätty!");
	}
}
