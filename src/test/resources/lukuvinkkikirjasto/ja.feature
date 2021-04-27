Feature: Kayttaja voi hakea vinkkeja jotka sisaltavat useamman tagin erottelamalla tagit haussa sanalla 'ja'

    Scenario: kayttaja voi hakea vinkkeja kahden tagin perusteella
        Given vinkki "Tapahtumia Oulussa", URL "https://www.ouka.fi/oulu/tapahtumat/" ja tagi "oulu, tapahtuma, paras" on listalla
        And   kayttaja kertoo haluavansa etsia vinkkeja tagilla
        When  kayttaja kirjoittaa "paras ja oulu"
        Then  listauksesta loytyy vinkki "Vinkki: Tapahtumia Oulussa" ja linkki "https://www.ouka.fi/oulu/tapahtumat/"

    Scenario: kayttaja voi hakea vinkkeja useamman tagin perusteella
        Given vinkki "Tapahtumia Oulussa", URL "https://www.ouka.fi/oulu/tapahtumat/" ja tagi "oulu, tapahtuma, paras" on listalla
        And   kayttaja kertoo haluavansa etsia vinkkeja tagilla
        When  kayttaja kirjoittaa "paras ja oulu ja tapahtuma"
        Then  listauksesta loytyy vinkki "Vinkki: Tapahtumia Oulussa" ja linkki "https://www.ouka.fi/oulu/tapahtumat/"

    Scenario: Kayttaja ei nae vaaraa vinkkia useamman tagin haussa
        Given vinkki "loisto", URL "muu.com" ja tagi "paras,jep,jee" on listalla
        And   vinkki "paras", URL "maa.com" ja tagi "paras,maa,jee" on listalla
        And   kayttaja kertoo haluavansa etsia vinkkeja tagilla
        When  kayttaja kirjoittaa "paras ja jep ja jee"
        Then  listauksesta loytyy vinkki "Vinkki: loisto" ja linkki "muu.com"
        And   listauksesta ei loydy vinkkia "paras" ja linkkia "maa.com"