package domain;

import java.time.LocalDate;
import java.util.List;

public interface Vinkki {
    Tyyppi getTyyppi();
    String getOtsikko();
    String getLinkki();
    String getTagit();
    boolean getLuettu();
    String getluettuPvm();
    String luetutToString();
}
