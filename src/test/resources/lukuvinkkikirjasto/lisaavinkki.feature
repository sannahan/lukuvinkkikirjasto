Feature: Kayttaja voi lisata vinkkeja

    Scenario: kayttaja voi lisata vinkin ja sovellus lopettaa kaskysta
        Given kayttaja kertoo haluavansa lisata vinkin
        When  otsikko "loistovinkki", URL "www.hs.fi" ja tagi "sanomalehti" annetaan
        And   annetaan lopetuskomento
        Then  sovellus suorittaa ja lopettaa

    Scenario: kayttaja voi lisata vinkin ja uusi komento
        Given kayttaja kertoo haluavansa lisata vinkin
        When  otsikko "loisto", URL "muu.com" ja tagi "paras" annetaan
        And   annetaan lopetuskomento
        Then  sovellus vastaa "Komento:"

    Scenario: kayttaja voi lisata vinkin ja vinkki loytyy
        Given kayttaja kertoo haluavansa lisata vinkin
        When  otsikko "paras", URL "miu.com" ja tagi "loisto" annetaan
        And   kayttaja kertoo haluavansa selata vinkkeja
        And   annetaan lopetuskomento
        Then  listauksesta loytyy vinkki "Vinkki: paras" ja linkki "miu.com"

    Scenario: kayttaja voi lisata vinkin ja vinkki nakyy oikein kaytettaessa ehdotettua otsikkoa
        Given kayttaja kertoo haluavansa lisata vinkin
        When  otsikko "", URL "http://www.youtube.com" ja tagi "yt" annetaan
        And   kayttaja kertoo haluavansa selata vinkkeja
        And   annetaan lopetuskomento
        Then  listauksesta loytyy vinkki "Vinkki: YouTube" ja linkki "http://www.youtube.com"

    Scenario: kayttaja voi lisata vinkin ja uusi komento JOKA EI TOIMI
        Given kayttaja kertoo haluavansa lisata vinkin
        When  otsikko "loisto", URL "muu.com" ja tagi "paras" annetaan
        Then  sovellus kysyy käyttäjältä uutta komentoa

    Scenario: kayttaja voi keskeyttaa vinkin lisayksen ja palata valikkoon
        Given kayttaja kertoo haluavansa lisata vinkin
        When  kayttaja kirjoittaa "PERUUTA" 
        Then  sovellus kysyy käyttäjältä uutta komentoa

