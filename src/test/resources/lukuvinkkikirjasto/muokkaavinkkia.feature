Feature: Kayttaja voi muokata vinkkia

    Scenario: Kayttaja voi muokata vinkin kaikki tiedot
        Given kayttaja kertoo haluavansa muokata vinkkia
        And   kayttaja valitsee listalta vinkin
        When  otsikko "Muokattu Otsikko", URL "www.muokattu.info" ja tagi "MuokattuT" annetaan
        Then  sovellus vastaa "Vinkki muokattu!"

    Scenario: Muokattava vinkki valitaan listalta numeron perusteella
        Given kayttaja kertoo haluavansa muokata vinkkia
        When  kayttaja kirjoittaa "1"
        And   otsikko "MuokattuO", URL "MuokattuL" ja tagi "MuokattuT" annetaan
        And   kayttaja kertoo haluavansa selata vinkkeja
        Then  listauksesta loytyy vinkki "MuokattuO" ja linkki "MuokattuL"
        And   listauksesta ei loydy vinkkia "Testiotsikko" ja linkkia "Testilinkki"

    Scenario: Jos kenttä jätetään tyhjäksi, sitä ei muokata
        Given kayttaja kertoo haluavansa muokata vinkkia
        When  kayttaja valitsee listalta vinkin
        And   otsikko "Muokattu0", URL "" ja tagi "MuokattuT" annetaan
        And   kayttaja kertoo haluavansa selata vinkkeja
        Then  listauksesta loytyy vinkki "Muokattu0" ja linkki "Testilinkki"
