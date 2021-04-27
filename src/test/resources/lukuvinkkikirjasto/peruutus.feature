Feature: Kayttaja voi keskeyttää vinkin lisayksen tai muokkauksen, ja palata takaisin valikkoon

    Scenario: kayttaja voi keskeyttaa vinkin lisayksen ja palata valikkoon
        Given kayttaja kertoo haluavansa lisata vinkin
        When  kayttaja kirjoittaa "PERUUTA" 
        Then  sovellus kysyy käyttäjältä uutta komentoa

    Scenario: kayttaja voi keskeyttaa vinkin lisayksen ja palata valikkoon annettuaan linkin
        Given kayttaja kertoo haluavansa lisata vinkin
        When  kayttaja kirjoittaa "www.helsinki.fi"
        And   kayttaja kirjoittaa "PERUUTA" 
        Then  sovellus kysyy käyttäjältä uutta komentoa

    Scenario: kayttaja voi keskeyttaa vinkin lisayksen ja palata valikkoon tageja kysyttaessa
        Given kayttaja kertoo haluavansa lisata vinkin
        When  kayttaja kirjoittaa "https://www.turku.fi/turun-kaupunginkirjasto"
        And   kayttaja kirjoittaa "Suomen Turun kaupunginkirjasto"
        And   kayttaja kirjoittaa "PERUUTA" 
        Then  sovellus kysyy käyttäjältä uutta komentoa

    Scenario: kayttaja voi keskeyttaa vinkin muokkauksen ja palata valikkoon
        Given kayttaja kertoo haluavansa muokata vinkkia
        When  kayttaja valitsee listalta vinkin
        And   kayttaja kirjoittaa "PERUUTA"
        Then  sovellus kysyy käyttäjältä uutta komentoa

    Scenario: kayttaja voi keskeyttaa vinkin muokkauksen ja palata valikkoon linkkia kysyttaessa
        Given kayttaja kertoo haluavansa muokata vinkkia
        When  kayttaja valitsee listalta vinkin
        And   kayttaja kirjoittaa "Loisto vinkki!"
        And   kayttaja kirjoittaa "PERUUTA"
        Then  sovellus kysyy käyttäjältä uutta komentoa

    Scenario: kayttaja voi keskeyttaa vinkin muokkauksen ja palata valikkoon tageja kysyttaessa
        Given kayttaja kertoo haluavansa muokata vinkkia
        When  kayttaja valitsee listalta vinkin
        And   kayttaja kirjoittaa "Loisto vinkki!"
        And   kayttaja kirjoittaa "https://www.pori.fi/pori-tieto"
        And   kayttaja kirjoittaa "PERUUTA"
        Then  sovellus kysyy käyttäjältä uutta komentoa