package domain.suodatus;

import domain.Vinkki;

public class LuettuTaiLukematon implements Ehto {
    private boolean luettu;

    public LuettuTaiLukematon() {
        this.luettu = true;
    }

    public LuettuTaiLukematon(boolean luettu) {
        this.luettu = luettu;
    }

    @Override
    public boolean test(Vinkki vinkki) {
        return vinkki.getLuettu() == this.luettu;
    }
}
