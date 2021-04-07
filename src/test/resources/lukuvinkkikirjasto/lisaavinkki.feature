Feature: Kayttaja voi lisata vinkin

    Scenario: kayttaja voi lisata vinkin ja sovellus lopettaa kaskysta
        Given kayttaja kertoo haluavansa lisata vinkin
        When  otsikko "loistovinkki" ja URL "www.hs.fi" annetaan
	And   annetaan lopetuskomento
        Then  sovellus suorittaa ja lopettaa

    Scenario: kayttaja voi lisata vinkin ja uusi komento
	Given kayttaja kertoo haluavansa lisata vinkin
	When  otsikko "loisto" ja URL "muu" annetaan
	And   annetaan lopetuskomento
	Then  sovellus vastaa "Komento:"
