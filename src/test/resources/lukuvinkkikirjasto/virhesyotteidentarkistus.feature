Feature: Applikaatio antaa oikeat virheviestit
 
    Scenario: Kayttaja antaa olemattoman komentoidn
        Given kayttaja kirjoittaa "1337"
        Then  sovellus vastaa "VIRHE: Komentoa ei löydy!"
