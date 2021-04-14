package domain;

import dao.FileLukuvinkkiDao;
import dao.LukuvinkkiDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class SovellusTest {
    LukuvinkkiDao mockDao;
    Sovellus testiSovellus;

    @Before
    public void setUp() {
        mockDao = mock(FileLukuvinkkiDao.class);
        testiSovellus = new Sovellus(mockDao);
    }

    @Test
    public void selaaVinkkejaPalauttaaOikeanListan() {
        List<Vinkki> testiLista = Arrays.asList(
                new Oletus("Accessibility vodcast (React Finland)", "https://www.youtube.com/watch?v=jouctaXwpdU", "tagi1,tagi2,tagi3"),
                new Oletus("Axe-con", "https://axe-con.com/", "tagi1,tagi2,tagi3"),
                new Oletus("Base64", "https://www.youtube.com/watch?v=8qkxeZmKmOY&t=2s", "tagi1,tagi2,tagi3"),
                new Oletus("Human Reproduction Behavioral Research Video Library", "https://www.pornhub.com/", "tagi1,tagi2,tagi3"));
        Mockito.doReturn(testiLista).when(mockDao).listaa();
        var vinkit = testiSovellus.selaaVinkkeja();
        assertEquals(
                "Vinkki: Accessibility vodcast (React Finland)\nLinkki: https://www.youtube.com/watch?v=jouctaXwpdU\nTägit: #tagi1 #tagi2 #tagi3\n",
                vinkit.get(0));
        assertEquals("Vinkki: Axe-con\nLinkki: https://axe-con.com/\nTägit: #tagi1 #tagi2 #tagi3\n", vinkit.get(1));
        assertEquals("Vinkki: Base64\nLinkki: https://www.youtube.com/watch?v=8qkxeZmKmOY&t=2s\nTägit: #tagi1 #tagi2 #tagi3\n", vinkit.get(2));
        assertEquals("Vinkki: Human Reproduction Behavioral Research Video Library\nLinkki: https://www.pornhub.com/\nTägit: #tagi1 #tagi2 #tagi3\n",
                vinkit.get(3));
    }

    @Test
    public void lisaaVinkkiKutsuuDaoa() {
        testiSovellus.lisaaVinkki("Fullstack-kurssi", "https://fullstackopen.com/osa0", "tagi1,tagi2,tagi3");
        verify(mockDao, times(1)).lisaa(any());
        testiSovellus.lisaaVinkki("Computerphile", "https://www.youtube.com/channel/UC9-y-6csu5WGm29I7JiwpnA", "tagi1,tagi2,tagi3");
        testiSovellus.lisaaVinkki("Mimmit koodaa", "https://mimmitkoodaa.ohjelmistoebusiness.fi/", "tagi1,tagi2,tagi3");
        verify(mockDao, times(3)).lisaa(any());
    }

    @Test
    public void poistaVinkkiKutsuuDaoaHalutullaIndeksilla() {
        testiSovellus.poistaVinkki(1337);
        verify(mockDao, times(1)).poista(eq(1337));
        testiSovellus.poistaVinkki(420);
        testiSovellus.poistaVinkki(69);
        verify(mockDao, times(1)).poista(eq(420));
    }

    @Test
    public void olemassaOlevaOtsikkoTunnistetaan() {
        List<String> testiLista = Arrays.asList("Accessibility vodcast (React Finland)", "Axe-con", "Base64",
                "Human Reproduction Behavioral Research Video Library");
        Mockito.doReturn(testiLista).when(mockDao).listaaOtsikot();
        assertTrue(testiSovellus.tarkistaOtsikko("Base64"));
    }

    @Test
    public void otsikonTarkistusPalauttaaFalseJosOnUusiOtsikko() {
        List<String> testiLista = Arrays.asList("Accessibility vodcast (React Finland)", "Axe-con", "Base64",
                "Human Reproduction Behavioral Research Video Library");
        Mockito.doReturn(testiLista).when(mockDao).listaaOtsikot();
        assertFalse(testiSovellus.tarkistaOtsikko("Testejä kirjoittaessa tulee usein mietittyä, että lukeekohan näitä kukaan."));
    }

    @Test
    public void listaaOtsikotIdllaListaaOtsikotIdlla() {
        List<String> testiLista = Arrays.asList("Accessibility vodcast (React Finland)", "Axe-con", "Base64",
                "Human Reproduction Behavioral Research Video Library");
        Mockito.doReturn(testiLista).when(mockDao).listaaOtsikot();
        var otsikot = testiSovellus.listaaOtsikotIdlla();
        assertEquals("1: Accessibility vodcast (React Finland)", otsikot.get(0));
        assertEquals("2: Axe-con", otsikot.get(1));
        assertEquals("3: Base64", otsikot.get(2));
        assertEquals("4: Human Reproduction Behavioral Research Video Library", otsikot.get(3));
    }

}
