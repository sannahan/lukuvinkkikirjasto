Feature: Kayttaja voi muokata vinkkia

    Scenario: Kayttaja muokkaa vinkin kaikki tiedot
        Given kayttaja kertoo haluavansa muokata vinkkia
        When kayttaja kirjoittaa "1"
        And otsikko "MuokattuO", URL "MuokattuL" ja tagi "MuokattuT" annetaan
        And annetaan lopetuskomento
        Then sovellus vastaa "Vinkki muokattu!"

    Scenario: Muokattava vinkki valitaan listalta numeron perusteella
        Given kayttaja kertoo haluavansa muokata vinkkia
        When kayttaja kirjoittaa "1"
        And otsikko "MuokattuO", URL "MuokattuL" ja tagi "MuokattuT" annetaan
        And kayttaja kertoo haluavansa selata vinkkeja
        And annetaan lopetuskomento
        Then listauksesta loytyy vinkki "MuokattuO" ja linkki "MuokattuL"
        And listauksesta ei loydy vinkkia "Testiotsikko" ja linkkia "Testilinkki"

    Scenario: Jos kenttä jätetään tyhjäksi, sitä ei muokata
        Given kayttaja kertoo haluavansa muokata vinkkia
        When kayttaja kirjoittaa "1"
        And otsikko "Muokattu0", URL "" ja tagi "MuokattuT" annetaan
        And kayttaja kertoo haluavansa selata vinkkeja
        And annetaan lopetuskomento
        Then listauksesta loytyy vinkki "Muokattu0" ja linkki "Testilinkki"

    Scenario: kayttaja voi keskeyttaa vinkin muokkauksen ja palata valikkoon
        Given kayttaja kertoo haluavansa muokata vinkkia
        When  kayttaja kirjoittaa "1"
        And   kayttaja kirjoittaa "Loisto vinkki!"
        And   kayttaja kirjoittaa "PERUUTA"
        Then  sovellus kysyy käyttäjältä uutta komentoa