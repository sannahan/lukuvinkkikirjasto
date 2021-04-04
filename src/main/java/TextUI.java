import java.util.Scanner;

public class TextUI implements IO{
    private Scanner scanner;

    public TextUI(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    // TODO pitäisikö olla "print" toisella parametrilla?
    private void ask(String msg) {
        System.out.print(msg);
    }

    @Override
    public String nextLine(String msg) {
        if (msg.length() > 0) {
            this.print(msg);
        }

        return scanner.nextLine();
    }

    @Override
    public int nextInt(String msg) {
        if (msg.length() > 0) {
            this.print(msg);
        }

        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public void error(String msg) {
        this.print("VIRHE: " + msg);
    }
}
