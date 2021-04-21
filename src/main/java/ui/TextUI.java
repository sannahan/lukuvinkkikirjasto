package ui;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import domain.Sovellus;
import io.IO;
import java.util.ArrayList;

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
            case 8:
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

    private void selaaLukemattomiaVinkkeja() {
        for (String vinkki : sovellus.selaaLukemattomiaVinkkeja()) {
            this.io.print(vinkki);
        }
    }

    private void lisaaVinkki() {
        this.io.print("\n*** Komennolla PERUUTA voit palata valikkoon ***\n");
        var otsikko = this.io.nextLine("Anna lukuvinkin otsikko: ");
        while (sovellus.tarkistaOtsikko(otsikko) || otsikko.isBlank()) {
            var error = (otsikko.isBlank()) ? "Otsikko ei voi olla tyhjä"
                    : "Syöttämälläsi otsikolla löytyy jo vinkki. Syötä uniikki otsikko";
            this.io.error(error);
            otsikko = this.io.nextLine("Anna lukuvinkin otsikko: ");
        }
        if (otsikko.trim().equals("PERUUTA")) return;
        var url = this.io.nextLine("Anna lukuvinkin URL: ");
        if (url.trim().equals("PERUUTA")) return;
        String tagit = this.io.nextLine("Lisää tägejä pilkulla erotettuna: ");
        if (tagit.trim().equals("PERUUTA")) return;
        sovellus.lisaaVinkki(otsikko, url, tagit, "null");
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
                this.io.print("\n*** Komennolla PERUUTA voit keskeyttää muokkauksen ja palata valikkoon ***\n");
                var vanhaVinkki = sovellus.poistaVinkki(muokattava);
                var otsikko = muokkaaOtsikkoa(vanhaVinkki.get("otsikko"));
                if (peruutaMuokkaus(otsikko, vanhaVinkki)) return;
                var url = muokkaaLinkkia(vanhaVinkki.get("linkki"));
                if (peruutaMuokkaus(url, vanhaVinkki)) return;
                var tagit = muokkaaTageja(vanhaVinkki.get("tagit"));
                if (peruutaMuokkaus(tagit, vanhaVinkki)) return;
                sovellus.lisaaVinkki(otsikko, url, tagit, vanhaVinkki.get("lukupvm"));
                this.io.print("Vinkki muokattu!");
            } else {
                this.io.print("Virheellinen id-numero");
            }
        }
    }

    private boolean peruutaMuokkaus(String komento, Map<String, String> vanhaVinkki) {
        if (komento.trim().equals("PERUUTA")) {
            sovellus.lisaaVinkki(vanhaVinkki.get("otsikko"), vanhaVinkki.get("linkki"), vanhaVinkki.get("tagit"),
                    vanhaVinkki.get("lukupvm"));
            return true;
        }
        return false;
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
        var uudetTagit = this.io
                .nextLine("Anna uudet tagit pilkulla eroteltuna (tyhja syöte säilyttää tagit ennallaan):");
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
        String syotetytTagit = this.io.nextLine("Syötä etsittävät tagit: ");
        boolean tagejaOnVainYksi = true;
        if (syotetytTagit.contains(" ja ") || syotetytTagit.contains(" tai ")) {
            tagejaOnVainYksi = false;
        }
        List<String> vinkit = new ArrayList<>();
        String tagitStr = "";
        //tähän tarkastus, ettei ole sekä ja, että tai
        if (syotetytTagit.contains(" ja ") && syotetytTagit.contains(" tai ")) {
            this.io.print("Ei onnistunut, käytä vain 'ja' tai 'tai' operandeja, älä molempia. Aloita alusta.");
        } else if (tagejaOnVainYksi) {
            List<String> haettavatTagit = new ArrayList<>();
            haettavatTagit.add(syotetytTagit);
            vinkit = sovellus.etsiVinkkejaTageilla(haettavatTagit, "");
        } else if (syotetytTagit.contains(" ja ")) {
            String[] tagit = syotetytTagit.split(" ja ");
            List<String> haettavatTagit = Arrays.asList(tagit);
            vinkit = sovellus.etsiVinkkejaTageilla(haettavatTagit, "ja");
            tagitStr += String.join(" ja ", tagit);
        } else if (syotetytTagit.contains(" tai ")) {
            String[] tagit = syotetytTagit.split(" tai ");
            List<String> haettavatTagit = Arrays.asList(tagit);
            vinkit = sovellus.etsiVinkkejaTageilla(haettavatTagit, "tai");
            tagitStr += String.join(" tai ", tagit);
        }
        //Molempien pitäisi palata tähän.
        if (vinkit.size() == 0) {
        	if (tagejaOnVainYksi) {
                    this.io.print("Yhtään vinkkiä ei löytynyt tagilla \"" + syotetytTagit + "\".");
        	} else {
        		
        		this.io.print("Yhtään vinkkiä ei löytynyt tageilla \"" + tagitStr + "\".");
        	}
            return;
        }

        if (tagejaOnVainYksi) {
        	this.io.print("" + vinkit.size() + " vinkki(ä) löytyi tagilla \"" + syotetytTagit + "\".");
        } else {
        	this.io.print("" + vinkit.size() + " vinkki(ä) löytyi tageilla \"" + tagitStr + "\".");
        }
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
        this.io.print(" 6: Selaa lukemattomia vinkkejä");
        this.io.print(" 7: Selaa luettuja vinkkejä");
        this.io.print(" 8: Etsi tagilla");
        this.io.print("-1: Poistu");
    }
}
