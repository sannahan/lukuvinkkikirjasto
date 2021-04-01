import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IO textUI = new TextUI(new Scanner(System.in));

        textUI.print("Hello Lukuvinkkikirjasto!");

        String komento = textUI.nextLine("Komento :");
        textUI.error("Komentoa \"" + komento + "\" ei löydy!");
    }
}

