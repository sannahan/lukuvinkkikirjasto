import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

public class TextUI implements IO{
    private Scanner scanner;

    public TextUI(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public String nextLine(String msg) {
        if (msg.length() > 0) {
            this.print(msg);
        }

        return scanner.nextLine();
    }

    @Override
    public void error(String msg) {
        this.print("ERR: " + msg);
    }
}
