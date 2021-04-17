package ui;

import java.util.List;

import domain.Sovellus;
import io.IO;

public class TextUI {
    private IO io;
    private Sovellus sovellus;

    public TextUI(IO io, Sovellus sovellus) {
        this.io = io;
        this.sovellus = sovellus;
    }

    public void suorita() {
        this.io.print("Valitse toiminto syöttämällä numero:");
        this.printInfo();

        while (true) {
            var komento = this.io.nextInt("Komento: ");

            if (komento == -1) {
                this.io.print("Hei hei!");
                break;
            }

            switch (komento) {
            case -2:
                this.io.error("Ei numero! Syötä numero komentovalikosta");
                printInfo();
                break;
            case 0:
                printInfo();
                break;
            case 1:
                selaaVinkkeja();
                break;
            case 2:
                lisaaVinkki();
                break;
            case 3:
                poistaVinkki();
                break;
            case 4:
                muokkaaVinkkia();
                break;
            case 5:
                merkitseVinkkiLuetuksi();
                break;
            case 6:
                selaaLuettujaVinkkeja();
                break;
            case 7:
                haeTagilla();
                break;    
            default:
                this.io.error("Komentoa ei löydy!");
                printInfo();
                break;
            }
        }
    }

    private void selaaVinkkeja() {
        for (String vinkki : sovellus.selaaVinkkeja()) {
            this.io.print(vinkki);
        }
    }

    private void selaaLuettujaVinkkeja() {
        for (String vinkki : sovellus.selaaLuettujaVinkkeja()) {
            this.io.print(vinkki);
        }
    }

    private void lisaaVinkki() {
        var otsikko = this.io.nextLine("Anna lukuvinkin otsikko: ");
        while (sovellus.tarkistaOtsikko(otsikko) || otsikko.isBlank()) {
            var error = (otsikko.isBlank()) ? "Otsikko ei voi olla tyhjä" : "Syöttämälläsi otsikolla löytyy jo vinkki. Syötä uniikki otsikko";
            this.io.error(error);
            otsikko = this.io.nextLine("Anna lukuvinkin otsikko: ");
        }
        var url = this.io.nextLine("Anna lukuvinkin URL: ");
        String tagit = this.io.nextLine("Lisää tägejä pilkulla erotettuna: ");
        sovellus.lisaaVinkki(otsikko, url, tagit);
    }

    private void poistaVinkki() {
        if (listaaOtsikotJosVinkkejaOnOlemassa()) {
            var poistettava = this.io.nextInt("Anna poistettavan vinkin id-numero:");
            if (sovellus.tarkistaId(poistettava)) {
                var vastaus = this.io.nextLine(
                        "Haluatko varmasti poistaa vinkin " + sovellus.getOtsikko(poistettava) + " (kyllä/ei)");
                if (vastaus.equals("kyllä")) {
                    sovellus.poistaVinkki(poistettava);
                    this.io.print("Vinkki poistettu");
                } else {
                    this.io.print("Vinkkiä ei poistettu");
                }
            } else {
                this.io.print("Virheellinen id-numero");
            }
        }
    }

    private void muokkaaVinkkia() {
        if (listaaOtsikotJosVinkkejaOnOlemassa()) {
            var muokattava = this.io.nextInt("Anna muokattavan vinkin id-numero:");
            if (sovellus.tarkistaId(muokattava)) {
                var vanhaVinkki = sovellus.poistaVinkki(muokattava);
                var otsikko = muokkaaOtsikkoa(vanhaVinkki.get("otsikko"));
                var url = muokkaaLinkkia(vanhaVinkki.get("linkki"));
                var tagit = muokkaaTageja(vanhaVinkki.get("tagit"));
                sovellus.lisaaVinkki(otsikko, url, tagit);
                this.io.print("Vinkki muokattu!");
            } else {
                this.io.print("Virheellinen id-numero");
            }
        }
    }

    private String muokkaaOtsikkoa(String otsikko) {
        this.io.print("Otsikko on nyt " + otsikko);
        var uusiOtsikko = this.io.nextLine("Anna uusi otsikko (tyhjä syöte säilyttää otsikon ennallaan): ");
        while (sovellus.tarkistaOtsikko(uusiOtsikko)) {
            if (uusiOtsikko.isBlank()) {
                break;
            }
            this.io.error("Syöttämälläsi otsikolla löytyy jo vinkki. Syötä uniikki otsikko");
            uusiOtsikko = this.io.nextLine("Anna uusi otsikko: ");
        }
        return (uusiOtsikko.isBlank()) ? otsikko : uusiOtsikko;
    }

    private String muokkaaLinkkia(String linkki) {
        this.io.print("Linkki on nyt " + linkki);
        var uusiLinkki = this.io.nextLine("Anna uusi URL (tyhjä syöte säilyttää linkin ennallaan): ");
        return (uusiLinkki.isBlank()) ? linkki : uusiLinkki;
    }

    private String muokkaaTageja(String tagit) {
        this.io.print("Vinkillä on seuraavat tagit " + tagit);
        var uudetTagit = this.io.nextLine("Anna uudet tagit pilkulla eroteltuna (tyhja syöte säilyttää tagit ennallaan):");
        return (uudetTagit.isBlank()) ? tagit : uudetTagit;
    }

    private void merkitseVinkkiLuetuksi() {
        if (listaaOtsikotJosVinkkejaOnOlemassa()) {
            var muokattava = this.io.nextInt("Anna luetuksi merkattavan vinkin id-numero:");
            if (sovellus.tarkistaId(muokattava)) {
                sovellus.merkitseLuetuksi(muokattava);
            } else {
                this.io.print("Virheellinen id-numero");
            }
        }
    }

    private boolean listaaOtsikotJosVinkkejaOnOlemassa() {
        List<String> otsikot = sovellus.listaaOtsikotIdlla();
        if (otsikot.isEmpty()) {
            this.io.print("Ei vinkkejä!");
            return false;
        }

        for (String otsikko : otsikot) {
            this.io.print(otsikko);
        }
        return true;
    }

    private void haeTagilla() {
        String haettavaTagi = this.io.nextLine("Syötä etsittävä tagi: ");

        List<String> vinkit = sovellus.etsiVinkkejaTagilla(haettavaTagi);

        if (vinkit.size() == 0) {
            this.io.print("Yhtään vinkkiä ei löytynyt tagilla \"" + haettavaTagi + "\".");
            return;
        }

        this.io.print("" + vinkit.size() + " vinkki(ä) löytyi tagilla \"" + haettavaTagi + "\".");
        for (String vinkki : vinkit) {
            this.io.print(vinkki);
        }
    }

    private void printInfo() {
        this.io.print(" 0: Info");
        this.io.print(" 1: Selaa vinkkejä");
        this.io.print(" 2: Lisää uusi vinkki");
        this.io.print(" 3: Poista vinkki");
        this.io.print(" 4: Muokkaa vinkkiä");
        this.io.print(" 5: Merkitse vinkki luetuksi");
        this.io.print(" 6: Selaa luettuja vinkkejä");
        this.io.print(" 7: Etsi tagilla");
        this.io.print("-1: Poistu");
    }
}
