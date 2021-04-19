package domain;

public class Oletus implements Vinkki {
	private Tyyppi tyyppi;
	private String otsikko;
	private String linkki;
	private String tagit;
	private boolean luettu;
	private String luettuPvm;

	/*public Oletus(String otsikko, String linkki, String tagit) {
		this.tyyppi = Tyyppi.OLETUS;
		this.otsikko = otsikko;
		this.linkki = linkki;
		this.tagit = (tagit.isEmpty()) ? "lukuvinkki" : tagit; 
		this.luettu = false;
		this.luettuPvm = null;
	}*/

	public Oletus(String otsikko, String linkki, String tagit, String date) {
		this.tyyppi = Tyyppi.OLETUS;
		this.otsikko = otsikko;
		this.linkki = linkki;
		this.tagit = (tagit.isEmpty()) ? "lukuvinkki" : tagit;
		this.luettu = (date.equals("null")) ? false : true;
		this.luettuPvm = date;
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

	public String getluettuPvm() {
		return luettuPvm;
	}

	public void merkitseLuetuksi(String pvm) {
		luettu = true;
		luettuPvm = pvm;
	}

	public void setLukemattomaksi() {
		luettu = false;
		luettuPvm = null;
	}

	@Override
	public String toString() {
		// String vinkki = "Vinkki: " + this.otsikko + "\n" + "Linkki: " + this.linkki + "\n" + "Tägit: " + tagitToString() + "\n";
		// vinkki = (luettu) ? vinkki + "Luettu: " + luettuPvm +"\n": vinkki;
		return "Vinkki: " + this.otsikko + "\n" + "Linkki: " + this.linkki + "\n" + "Tägit: " + tagitToString() + "\n";
	}

	/*public String luetutToString() {
		return "Vinkki: " + this.otsikko + "\n" + "Linkki: " + this.linkki + "\n" + "Tägit: " + tagitToString() + "\n"
				+ "Milloin luettu: " + this.getluettuPvm() + "\n";

	}*/

	public String tagitToString() {
		String tagitStr = "#";
		String[] tagit = this.tagit.split(",");
		/* for (int i = 0; i < tagit.length; i++) {
			String tagi = tagit[i];
			if (i != tagit.length - 1) {
				tagitStr += "#" + tagi + " ";
			} else {
				tagitStr += "#" + tagi;
			}
		} */
    tagitStr += String.join(" #", tagit);
		return tagitStr;
	}
}
