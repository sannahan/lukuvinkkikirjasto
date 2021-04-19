package ui;

import java.util.List;

import domain.Sovellus;
import io.IO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

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
                selaaLukemattomiaVinkkeja();
                break;
            case 7:
                selaaLuettujaVinkkeja();
                break;
            default:
                this.io.error("Komentoa ei löydy!");
                printInfo();
                break;
            }
        }
    }

    private void selaaVinkkeja() {
        for (String vinkki : sovellus.selaaVinkkeja())
            this.io.print(vinkki);
    }
    
    private void selaaLuettujaVinkkeja() {
        for (String vinkki : sovellus.selaaLuettujaVinkkeja())
            this.io.print(vinkki);
    }

    private void selaaLukemattomiaVinkkeja() {
        for (String vinkki : sovellus.selaaLukemattomiaVinkkeja())
            this.io.print(vinkki);
    }
    
    private void lisaaVinkki() {
        var otsikko = this.io.nextLine("Anna lukuvinkin otsikko: ");
        while (sovellus.tarkistaOtsikko(otsikko)) {
            this.io.error("Syöttämälläsi otsikolla löytyy jo vinkki. Syötä uniikki otsikko");
            otsikko = this.io.nextLine("Anna lukuvinkin otsikko: ");
        }
        var URL = this.io.nextLine("Anna lukuvinkin URL: ");
        String tagit = this.io.nextLine("Lisää tägejä pilkulla erotettuna: ");
        sovellus.lisaaVinkki(otsikko, URL, tagit);
    }

    private void poistaVinkki() {
        if (listaaOtsikotJosVinkkejaOnOlemassa()) {
            var poistettava = this.io.nextInt("Anna poistettavan vinkin id-numero:");
            if (sovellus.tarkistaId(poistettava)) {
                var vastaus = this.io.nextLine("Haluatko varmasti poistaa vinkin " + sovellus.getOtsikko(poistettava) + " (kyllä/ei)");
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
                Map<String, String> vanhaVinkki = sovellus.poistaVinkki(muokattava);
                this.io.print("Otsikko on nyt " + vanhaVinkki.get("otsikko"));
                var otsikko = this.io.nextLine("Anna uusi otsikko (tyhjä syöte säilyttää otsikon ennallaan): ");
                if (otsikko.isEmpty()) {
                    otsikko = vanhaVinkki.get("otsikko");
                } else {
                    while (sovellus.tarkistaOtsikko(otsikko)) {
                        this.io.error("Syöttämälläsi otsikolla löytyy jo vinkki. Syötä uniikki otsikko");
                        otsikko = this.io.nextLine("Anna uusi otsikko: ");
                    }
                }
                this.io.print("Linkki on nyt " + vanhaVinkki.get("linkki"));
                var url = this.io.nextLine("Anna uusi URL (tyhjä syöte säilyttää linkin ennallaan): ");
                if (url.isEmpty()) {
                    url = vanhaVinkki.get("linkki");
                }
                this.io.print("Vinkillä on seuraavat tagit " + vanhaVinkki.get("tagit"));
                var tagit = this.io.nextLine("Anna uudet tagit (tyhja syöte säilyttää tagit ennallaan):");
                if (tagit.isEmpty()) {
                    tagit = vanhaVinkki.get("tagit");
                }
                sovellus.lisaaVinkki(otsikko, url, tagit);
            } else {
                this.io.print("Virheellinen id-numero"); 
            }
        }
    }

    private void merkitseVinkkiLuetuksi() {
        if (listaaOtsikotJosVinkkejaOnOlemassa()) {
            var muokattava = this.io.nextInt("Anna luetuksi merkattavan vinkin id-numero:");
            if (sovellus.tarkistaId(muokattava)) {
                Map<String, String> vanhaVinkki = sovellus.poistaVinkki(muokattava);
                LocalDate dateLD = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
                String date = dateLD.format(formatter);
                sovellus.lisaaLuettuVinkki(vanhaVinkki.get("otsikko"), vanhaVinkki.get("url"), vanhaVinkki.get("tagit"), date);
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

    private void printInfo() {
        this.io.print(" 0: Info");
        this.io.print(" 1: Selaa vinkkejä");
        this.io.print(" 2: Lisää uusi vinkki");
        this.io.print(" 3: Poista vinkki");
        this.io.print(" 4: Muokkaa vinkkiä");
        this.io.print(" 5: Merkitse vinkki luetuksi");
        this.io.print(" 6: Selaa lukemattomia vinkkejä");
        this.io.print(" 7: Selaa luettuja vinkkejä");
        this.io.print("-1: Poistu");
    }
}