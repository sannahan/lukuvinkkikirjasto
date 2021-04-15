Feature: Kayttaja voi muokata vinkkia

Scenario: Kayttaja muokkaa vinkin kaikki tiedot 
	Given kayttaja kertoo haluavansa muokata vinkkia
	When kayttaja antaa id:n "1"
	And otsikko "MuokattuO", URL "MuokattuL" ja tagi "MuokattuT" annetaan
        And annetaan lopetuskomento
	Then sovellus vastaa "Vinkki muokattu!"
