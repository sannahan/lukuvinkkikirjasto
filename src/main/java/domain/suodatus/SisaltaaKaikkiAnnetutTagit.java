package domain.suodatus;

import domain.Vinkki;
import java.util.Arrays;
import java.util.List;

public class SisaltaaKaikkiAnnetutTagit implements Ehto {
    private List<String> tagit;
    
    public SisaltaaKaikkiAnnetutTagit(List<String> tagit) {
        this.tagit = tagit;
    }
    
    @Override
    public boolean test(Vinkki vinkki) {
        boolean sisaltaako = true;
        
        String[] vinkinTagit = vinkki.getTagit().split(",");
        
        for (String tagi: tagit) {
            if (!Arrays.asList(vinkinTagit).contains(tagi)) {
                sisaltaako = false;            
            }
        }
        
        return sisaltaako;
    }
    
}
