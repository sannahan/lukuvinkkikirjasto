Feature: Kayttaja voi hakea vinkkeja joilla on jokin haluttu tagi erottelamalla tagit haussa sanalla 'tai'

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

    Scenario: kahdella tagilla haettaessa molemmilla loytyvat tulokset tulostetaan
        Given vinkki "Tapahtumia Oulussa", URL "https://www.ouka.fi/oulu/tapahtumat/" ja tagi "oulu, tapahtuma, paras" on listalla
        And   vinkki "Imatran kuulutukset", URL "https://www.imatra.fi/kuulutukset" ja tagi "imatra, tapahtuma, heikompi" on listalla
        And   kayttaja kertoo haluavansa etsia vinkkeja tagilla
        When  kayttaja kirjoittaa "oulu tai imatra"
        Then  listauksesta loytyy vinkki "Vinkki: Tapahtumia Oulussa" ja linkki "https://www.ouka.fi/oulu/tapahtumat/"
        And   listauksesta loytyy vinkki "Vinkki: Imatran kuulutukset" ja linkki "https://www.imatra.fi/kuulutukset"