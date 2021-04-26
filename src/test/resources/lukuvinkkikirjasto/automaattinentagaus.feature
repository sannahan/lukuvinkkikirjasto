Feature: Sovellus lisaa tagin automaattisesti linkin perusteella

    Scenario: Sovellus tagaa automaatisesti videolinkin
        Given kayttaja kertoo haluavansa lisata vinkin
        When  kayttaja kirjoittaa "https://www.youtube.com/watch?v=i3NRe3KulqU"
        And   kayttaja kirjoittaa ""
        And   kayttaja kirjoittaa "smb, wr, 4:54"
        Then  lisatyn vinkin tageista loytyy "video"

    Scenario: Sovellus ei lisaa tagia, jos kayttaja on jo antanut vinkille saman tagin
        Given kayttaja kertoo haluavansa lisata vinkin
        When  kayttaja kirjoittaa "https://www.youtube.com/watch?v=i3NRe3KulqU"
        And   kayttaja kirjoittaa ""
        And   kayttaja kirjoittaa "smb, video, 4:54"
        Then  lisatyn vinkin tagit ovat "#smb #video #4:54"