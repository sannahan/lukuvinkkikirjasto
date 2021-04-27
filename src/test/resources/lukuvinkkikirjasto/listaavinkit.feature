Feature: Kayttaja voi listata luetut ja lukemattomat vinkit

    Scenario: Kayttaja voi listata lukemattomat vinkit
        Given sovellukseen on lisatty vinkkeja
        When  kayttaja kertoo haluavansa selata vinkkeja joita ei ole luettu
        Then  sovellus listaa lukemattomat vinkit
        And   sovellus ei listaa luettuja vinkkeja

    Scenario: Kayttaja voi listata luetut vinkit
        Given sovellukseen on lisatty vinkkeja
        When  kayttaja kertoo haluavansa selata luettuja vinkkeja
        Then  sovellus listaa luetut vinkit
        And   sovellus ei listaa lukemattomia vinkkeja