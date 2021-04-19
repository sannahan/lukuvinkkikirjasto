Feature: Kayttaja voi merkita vinkin luetuksi

Scenario: Tallennetaan paivamaara, jolloin merkattu luetuksi
    Given kayttaja kertoo haluavansa merkita vinkin luetuksi
    When kayttaja antaa id:n "1"
    And kayttaja kertoo haluavansa selata luettuja vinkkeja
    And annetaan lopetuskomento
    Then listauksesta loytyy oikea paivamaara

Scenario: Luetuksi merkattava vinkki valitaan listalta numeron perusteella
    Given kayttaja kertoo haluavansa merkita vinkin luetuksi
    When kayttaja antaa id:n "1"
    And kayttaja kertoo haluavansa selata luettuja vinkkeja
    And annetaan lopetuskomento
    Then listauksesta loytyy vinkki "Testiotsikko" ja linkki "Testilinkki"