package ui;


import java.util.ArrayList;
import java.util.List;


public class StubIO implements IO {
    private List<String> syotteet;
    private ArrayList<String> tulosteet;
    private int i;
    
    public StubIO (List<String> syotteet) {
        this.syotteet = syotteet;
        tulosteet = new ArrayList<>();
    }
    
    @Override
    public void print(String msg) {
        tulosteet.add(msg);
    }

    @Override
    public String nextLine(String msg) {
        if (msg.length() > 0) {
            this.print(msg);
        }
        return tulosteet.get(i++);
     }

    @Override
    public int nextInt(String msg) {
        if (msg.length() > 0) {
            this.print(msg);
        }
        try {
            return Integer.parseInt(tulosteet.get(i++));
        } catch(NumberFormatException e) {
            return -2;
        }
    }

    @Override
    public void error(String msg) {
        this.print("VIRHE: " + msg);
    }
    
}
