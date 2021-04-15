package domain;

import java.time.LocalDate;
import java.util.List;


public class Oletus implements Vinkki {
	private Tyyppi tyyppi;
	private String otsikko;
	private String linkki;
	private String tagit;
	private boolean luettu;
	private LocalDate luettuPvm;

	public Oletus(String otsikko, String linkki, String tagit) {
		this.tyyppi = Tyyppi.OLETUS;
		this.otsikko = otsikko;
		this.linkki = linkki;
		this.tagit = tagit;
		this.luettu = false;
	}

	public Tyyppi getTyyppi() {
		return this.tyyppi;
	}

	public String getOtsikko() {
		return this.otsikko;
	}

	public String getLinkki() {
		return this.linkki;
	}

	public String getTagit() {
		return tagit;
	}
	
	public boolean getLuettu() {
		return luettu;
	}
	
	public LocalDate getluettuPvm() {
		return luettuPvm;
	}
	
	public void setLuetuksi(LocalDate pvm) {
		luettu = true;
		luettuPvm = pvm;
	}
	
	public void setLukemattomaksi() {
		luettu = false;
		luettuPvm = null;
	}
	
    @Override
    public String toString() {
        return "Vinkki: " + this.otsikko + "\n" + "Linkki: " + this.linkki + "\n" + "Tägit: " + tagitToString() + "\n" ;  
    }
    
    public String tagitToString() {
    	String tagitStr = "";
    	String[] tagit = this.tagit.split(",");
    	for (int i = 0; i < tagit.length; i++) {
    		String tagi = tagit[i];
    		if (i != tagit.length-1) {
    			tagitStr += "#" + tagi + " ";
    		} else {
    			tagitStr += "#" + tagi;
    		}
    	}
    	return tagitStr;
    }

}
