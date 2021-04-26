Feature: Kayttaja suodattaa vinkkeja

    Scenario: kayttaja voi antaa komennon etsi vinkkeja tagilla
        Given kayttaja kertoo haluavansa etsia vinkkeja tagilla
        When  kayttaja kirjoittaa "paras"
        And   annetaan lopetuskomento
        Then  sovellus suorittaa ja lopettaa

    Scenario: kayttaja voi suodattaa naytettavia vinkkeja tagin perusteella
        Given vinkki "loisto", URL "muu.com" ja tagi "paras" on listalla
        And   kayttaja kertoo haluavansa etsia vinkkeja tagilla
        When  kayttaja kirjoittaa "paras"
        Then  listauksesta loytyy vinkki "Vinkki: loisto" ja linkki "muu.com"

    Scenario: Kayttaja nakee viestin, kun yhtaan vinkkia ei loydy
        Given kayttaja kertoo haluavansa etsia vinkkeja tagilla
        When  kayttaja kirjoittaa "eiloydy"
        Then  sovellus vastaa 'Yhtään vinkkiä ei löytynyt tagilla "eiloydy".'

    Scenario: Kayttaja ei nae vaaraa vinkkia
        Given vinkki "loisto", URL "muu.com" ja tagi "paras" on listalla
        And   kayttaja kertoo haluavansa etsia vinkkeja tagilla
        When  kayttaja kirjoittaa "parasparas"
        Then  listauksesta ei loydy vinkkia "loisto" ja linkkia "muu.com"

    Scenario: Kayttaja ei nae vaaraa vinkkia useamman tagin haussa
        Given vinkki "loisto", URL "muu.com" ja tagi "paras,jep,jee" on listalla
        And   vinkki "paras", URL "maa.com" ja tagi "paras,maa,jee" on listalla
        And   kayttaja kertoo haluavansa etsia vinkkeja tagilla
        When  kayttaja kirjoittaa "paras ja jep ja jee"
        Then  listauksesta loytyy vinkki "Vinkki: loisto" ja linkki "muu.com"
        And   listauksesta ei loydy vinkkia "paras" ja linkkia "maa.com"