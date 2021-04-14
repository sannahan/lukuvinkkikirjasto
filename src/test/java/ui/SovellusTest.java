package ui;

import dao.*;
import domain.Sovellus;
import io.IO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SovellusTest {
    IO tui;
    File tiedosto;
    LukuvinkkiDao vDao;

    @Before
    public void setUp() {
        System.out.println("Alustetaan rajapinnat");
        tui = mock(IO.class);
        // vDao = mock(FileLukuvinkkiDao.class);

        try {
            tiedosto = File.createTempFile("tmp", null);
            vDao = new FileLukuvinkkiDao(tiedosto);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        tiedosto.delete();
    }
/*
    @Test
    public void KutsutaanKaksikertaaNextIntMetodian() {
        // Listaus ja poistuminen
        when(tui.nextInt(anyString()))  // "Komento: "
                .thenReturn(1)
                .thenReturn(-1);

        Sovellus app = new Sovellus(tui, vDao);
        app.suorita();

        verify(tui, times(2)).nextInt(anyString());
        // verify(tui, atLeast(0)).someMethod("was called any number of times");
    }

    @Test
    public void KutsutaanNextLineMetodiaVinkkiaLisatessa() {
        when(tui.nextInt(anyString()))
                .thenReturn(2)
                .thenReturn(-1);

        when(tui.nextLine(anyString()))
                .thenReturn("aa")
                .thenReturn("bb");

        Sovellus app = new Sovellus(tui, vDao);
        app.suorita();

        verify(tui, times(2)).nextLine(anyString());
        // verify(tui, atLeast(0)).someMethod("was called any number of times");
    }

    @Test
    public void VinkkiListataanKunSeOnLisatty() {

        // Listaus ja poistuminen
        when(tui.nextInt(anyString()))  // "Komento: "
                .thenReturn(2)
                .thenReturn(1)
                .thenReturn(-1);

        when(tui.nextLine(anyString()))
                .thenReturn("aa")
                .thenReturn("bb");

        Sovellus app = new Sovellus(tui, vDao);
        app.suorita();

        verify(tui, times(1)).print("Vinkki: aa\nLinkki: bb\n");
    }

    @Test
    public void VinkkiaEiVoiLisataKahdesti() {
        when(tui.nextInt(anyString()))  // "Komento: "
                .thenReturn(2)
                .thenReturn(2)
                .thenReturn(-1);

        when(tui.nextLine(anyString()))
                .thenReturn("aa")
                .thenReturn("bb")
                .thenReturn("aa")
                .thenReturn("cc");

        Sovellus app = new Sovellus(tui, vDao);
        app.suorita();

        verify(tui, times(1)).error(anyString());
    }

    @Test
    public void VinkkiaEiListataPoistonJalkeen() {
        when(tui.nextInt(anyString()))
                .thenReturn(2)
                .thenReturn(1)
                .thenReturn(1) // Vinkki listataan vain kaksi kertaa
                .thenReturn(3)
                .thenReturn(1)
                .thenReturn(1) // tässä kohtaa ei kuulu listata
                .thenReturn(-1);

        when(tui.nextLine(anyString()))
                .thenReturn("aa")
                .thenReturn("bb")
                .thenReturn("kyllä");

        Sovellus app = new Sovellus(tui, vDao);
        app.suorita();

        verify(tui, times(2)).print("Vinkki: aa\nLinkki: bb\n");
    }

*/

}
