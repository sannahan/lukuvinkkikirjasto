Feature: Kayttaja voi lisata vinkkeja

    Scenario: kayttaja voi lisata vinkin ja sovellus lopettaa kaskysta
        Given kayttaja kertoo haluavansa lisata vinkin
        When  otsikko "loistovinkki" ja URL "www.hs.fi" annetaan
        And   annetaan lopetuskomento
        Then  sovellus suorittaa ja lopettaa

    Scenario: kayttaja voi lisata vinkin ja uusi komento
        Given kayttaja kertoo haluavansa lisata vinkin
        When  otsikko "loisto" ja URL "muu.com" annetaan
        And   annetaan lopetuskomento
        Then  sovellus vastaa "Komento:"

    Scenario: kayttaja voi lisata vinkin ja vinkki loytyy
        Given kayttaja kertoo haluavansa lisata vinkin
        When  otsikko "paras" ja URL "miu.com" annetaan
        And   kayttaja kertoo haluavansa selata vinkkeja
        And   annetaan lopetuskomento
        Then  listauksesta loytyy vinkki "Vinkki: paras" ja linkki "miu.com"


