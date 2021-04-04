import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IO textUI = new TextUI(new Scanner(System.in));

        Sovellus app = new Sovellus(textUI);
        app.suorita();
    }
}

