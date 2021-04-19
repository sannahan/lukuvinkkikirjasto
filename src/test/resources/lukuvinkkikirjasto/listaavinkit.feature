Feature: Kayttaja voi listata lukuvinkkeja

Scenario: Kayttaja listaa lukemattomat lukuvinkit
    Given kayttaja kertoo haluavansa selata lukemattomia vinkkeja
    When annetaan lopetuskomento
    Then listauksesta loytyy vinkki "Testiotsikko" ja linkki "Testilinkki"

Scenario: Kayttaja listaa luetut vinkit
    Given kayttaja kertoo haluavansa lisata vinkin
    When otsikko "PianLuettu", URL "PianLinkattu" ja tagi "PianTägätty" annetaan
    And kayttaja kertoo haluavansa merkita vinkin luetuksi
    And kayttaja antaa id:n "2"
    And kayttaja kertoo haluavansa selata luettuja vinkkeja
    And annetaan lopetuskomento
    Then listauksesta loytyy vinkki "PianLuettu" ja linkki "PianLinkattu"
    And listauksesta ei loydy vinkkia "Testiotsikko" ja linkkia "Testilinkki"