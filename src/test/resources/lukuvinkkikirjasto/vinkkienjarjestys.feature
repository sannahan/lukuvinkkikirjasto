Feature: Vinkit naytetaan kayttajalle viimeiseksi lisatty/muokattu ensin

Scenario: Kayttaja lisaa vinkin, ja se naytetaan listatessa ensimmaisena
    Given kayttaja kertoo haluavansa lisata vinkin
    When otsikko "Toiseksi uusin otsikko", URL "Toiseksi uusin linkki" ja tagi "Tagi" annetaan
    And kayttaja kertoo haluavansa lisata vinkin
    And otsikko "Uusin otsikko", URL "Uusin linkki" ja tagi "Tagi" annetaan
    And kayttaja kertoo haluavansa selata vinkkeja
    And annetaan lopetuskomento
    Then viimeisin lisays tulostetaan ensimmaisena

Scenario: Kayttaja muokkaa vinkkia, ja se naytetaan listatessa ensimmaisena
    Given kayttaja kertoo haluavansa lisata vinkin
    When otsikko "Muokkaamaton otsikko", URL "Muokkaamaton linkki" ja tagi "Tagi" annetaan
    And kayttaja kertoo haluavansa muokata vinkkia
    And kayttaja antaa id:n "2"
    And otsikko "Muokattu otsikko", URL "Muokattu linkki" ja tagi "" annetaan
    And annetaan lopetuskomento
    Then viimeisin muokkaus tulostetaan ensimmaisena
