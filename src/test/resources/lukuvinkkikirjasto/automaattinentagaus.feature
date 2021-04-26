Feature: Sovellus lisaa tagin automaattisesti linkin perusteella

    Scenario: Sovellus tagaa automaatisesti videolinkin
        Given kayttaja kertoo haluavansa lisata vinkin
        When  otsikko "", URL "https://www.youtube.com/watch?v=i3NRe3KulqU" ja tagi "smb, wr, 4:54" annetaan
        Then  lisatyn vinkin tageista loytyy "#video"

    Scenario: Sovellus tagaa automaatisesti julkaisulinkin
        Given kayttaja kertoo haluavansa lisata vinkin
        When  otsikko "AI education: open-access educational resources on AI", URL "https://dl.acm.org/doi/10.1145/3054837.3054841" ja tagi "" annetaan
        Then  lisatyn vinkin tageista loytyy "#julkaisu"

    Scenario: Sovellus tagaa automaatisesti artikkelilinkin
        Given kayttaja kertoo haluavansa lisata vinkin
        When  otsikko "Auttaako musiikki parantamaan sairauksia?", URL "https://www.hs.fi/tiede/art-2000007939075.html" ja tagi "sanomalehti" annetaan
        Then  lisatyn vinkin tageista loytyy "#artikkeli"

    Scenario: Sovellus ei lisaa tagia, jos kayttaja on jo antanut vinkille saman tagin
        Given kayttaja kertoo haluavansa lisata vinkin
        When  kayttaja kirjoittaa "https://www.youtube.com/watch?v=i3NRe3KulqU"
        And   kayttaja kirjoittaa ""
        And   kayttaja kirjoittaa "smb, video, 4:54"
        Then  lisatyn vinkin tagit ovat "#smb #video #4:54"