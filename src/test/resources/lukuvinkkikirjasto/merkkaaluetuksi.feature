Feature: Kayttaja voi merkita vinkin luetuksi

    Scenario: Kun kayttaja merkitsee vinkin luetuksi, tallentuu oikea paivamaara
        Given kayttaja kertoo haluavansa merkita vinkin luetuksi
        When  kayttaja valitsee listalta vinkin
        Then  sovellus tallentaa oikean paivamaara

    Scenario: Luetuksi merkattava vinkki valitaan listalta numeron perusteella
        Given sovellukseen on lisatty vinkkeja
        And   kayttaja kertoo haluavansa merkita vinkin luetuksi
        When  kayttaja kirjoittaa "1"
        And   kayttaja kertoo haluavansa selata luettuja vinkkeja
        Then  sovellus tallentaa oikean paivamaara