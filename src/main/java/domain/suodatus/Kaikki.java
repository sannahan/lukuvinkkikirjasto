package domain.suodatus;

import domain.Vinkki;

public class Kaikki implements Ehto{
    @Override
    public boolean test(Vinkki vinkki) {
        return true;
    }
}
