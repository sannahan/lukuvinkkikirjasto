package io;

import java.util.ArrayList;
import java.util.List;

public class StubIO implements IO {
    private List<String> syotteet;
    private ArrayList<String> tulosteet;
    private int i;

    public StubIO(List<String> syotteet) {
        this.syotteet = syotteet;
        tulosteet = new ArrayList<>();
        i = 0;
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
        String palautettava = syotteet.get(i);
        i++;
        return palautettava;

    }

    @Override
    public int nextInt(String msg) {
        this.print(msg);
        // int palautettava = Integer.parseInt(syotteet.get(i));
        // i++;
        // return palautettava;
        
        return Integer.parseInt(syotteet.get(i++));
    }

    @Override
    public void error(String msg) {
        this.print("VIRHE: " + msg);
    }

    // testeja varten
    public ArrayList<String> getTulosteet() {
        return tulosteet;
    }

    public void lisaaSyote(String syote) {
        syotteet.add(syote);
    }
}
