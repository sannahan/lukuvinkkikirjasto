Feature: Kayttaja voi hakea vinkkeja jonkin tageista erottelamalla tagit haussa sanalla 'tai'

    Scenario: kayttaja voi hakea vinkkeja jotka sisaltavat jommankumman kahdesta tagista
        Given vinkki "Tapahtumia Oulussa", URL "https://www.ouka.fi/oulu/tapahtumat/" ja tagi "oulu, tapahtuma, paras" on listalla
        And   kayttaja kertoo haluavansa etsia vinkkeja tagilla
        When  kayttaja kirjoittaa "oulu tai olut"
        Then  listauksesta loytyy vinkki "Vinkki: Tapahtumia Oulussa" ja linkki "https://www.ouka.fi/oulu/tapahtumat/"

    Scenario: kayttaja voi hakea vinkkeja jotka sisaltavat jonkin useammasta tagista
        Given vinkki "Tapahtumia Oulussa", URL "https://www.ouka.fi/oulu/tapahtumat/" ja tagi "oulu, tapahtuma, paras" on listalla
        And   kayttaja kertoo haluavansa etsia vinkkeja tagilla
        When  kayttaja kirjoittaa "oulu tai olut tai jääkarhu"
        Then  listauksesta loytyy vinkki "Vinkki: Tapahtumia Oulussa" ja linkki "https://www.ouka.fi/oulu/tapahtumat/"