package domain;

public interface Vinkki {
    Tyyppi getTyyppi();
    String getOtsikko();
    String getLinkki();
    String getTagit();
    boolean getLuettu();
    String getluettuPvm();
    String luetutToString();
}
