package domain.Suodatus;

import java.util.Arrays;
import domain.Vinkki;

public class SisaltaaTagin implements Ehto{
    private String tagi;

    public SisaltaaTagin(String tagi) {
        this.tagi = tagi;
    }

    @java.lang.Override
    public boolean test(Vinkki vinkki) {
        String[] tagit = vinkki.getTagit().split(",");

        if (Arrays.asList(tagit).contains(this.tagi)) {
            return true;
        }

        return false;
    }
}
