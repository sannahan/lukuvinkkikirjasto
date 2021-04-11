Feature: Applikaatio antaa oikeat virheviestit
 
    Scenario: Kayttaja antaa olemattoman komentoidn
        Given kayttaja antaa idn 220
        When  annetaan lopetuskomento
        Then  sovellus vastaa "VIRHE: Komentoa ei löydy!"
