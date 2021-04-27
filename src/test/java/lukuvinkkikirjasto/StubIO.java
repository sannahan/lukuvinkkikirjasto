package lukuvinkkikirjasto;

import io.IO;
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
        this.print(msg);
        return syotteet.get(i++);

    }

    @Override
    public int nextInt(String msg) {
        this.print(msg);        
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
    
	@Override
	public String trimTags(String tags) {
		String[] tagsList = tags.split(","); 
		String trimmed = "" + tagsList[0].replaceAll("#", "").trim().toLowerCase();
		for (int i = 1; i < tagsList.length; i++) {
			trimmed += "," + tagsList[i].replaceAll("#", "").trim().toLowerCase();
		}
		return trimmed;
	}
}
