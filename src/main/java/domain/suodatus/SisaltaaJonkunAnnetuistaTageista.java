
package domain.suodatus;

import domain.Vinkki;
import java.util.Arrays;
import java.util.List;


public class SisaltaaJonkunAnnetuistaTageista implements Ehto {

    private List<String> tagit;
    
    public SisaltaaJonkunAnnetuistaTageista(List<String> tagit) {
        this.tagit = tagit;
    }
    
    @Override
    public boolean test(Vinkki vinkki) {
        boolean sisaltaako = false;
        
        String[] vinkinTagit = vinkki.getTagit().split(",");
        
        for (String tagi: tagit) {
            if (Arrays.asList(vinkinTagit).contains(tagi)) {
                sisaltaako = true;            
            }
        }
        
        return sisaltaako;
    }
    
}
