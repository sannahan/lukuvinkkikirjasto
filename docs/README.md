# Lukuvinkkikirjasto

- **[Product Backlog](https://docs.google.com/spreadsheets/d/1X-Dka5l4AGH5qX0RWS0PIBxflUSbwlH-rtMBb4RWIM4/edit#gid=1)**
- **[Sprint 1 backlog](https://docs.google.com/spreadsheets/d/1X-Dka5l4AGH5qX0RWS0PIBxflUSbwlH-rtMBb4RWIM4/edit#gid=7)**
- **[Sprint 2 backlog](https://docs.google.com/spreadsheets/d/1X-Dka5l4AGH5qX0RWS0PIBxflUSbwlH-rtMBb4RWIM4/edit#gid=799619105)**
- **[Sprint 3 backlog](https://docs.google.com/spreadsheets/d/1X-Dka5l4AGH5qX0RWS0PIBxflUSbwlH-rtMBb4RWIM4/edit#gid=47336913)**
<TODO info="lisätään sprint backlogille linkki">

## Release 2

- Release 2 löytyy [täältä](https://github.com/sannahan/lukuvinkkikirjasto/releases/tag/v2.0.1)
- Sovellus olettaa, että suoritushakemistossa on tiedosto `demo.txt` ja tiedosto `config.properties`
- Suorita jar-tiedosto komennolla `java -jar lukuvinkkikirjasto.jar`

## Käyttöohjeet
- Suorita pääohjelma komennolla `gradle -q --console plain run` (käytä komennosta muotoa `./gradlew`, jos `gradle` ei toimi) projektin juurihakemistossa
- Generoi jar-tiedosto komennolla `gradle shadowJar` ja suorita jar-tiedosto komennolla `java -jar build/libs/lukuvinkkikirjasto.jar` projektin juurihakemistossa

## Definition of done
- Koodi on selkeää ja helposti luettavaa: koodi on katselmoitu toisen tiimiläisen johdosta ja koodi tarkistetaan checkstyle actionin avulla
- Kattavahkot testit, jotka menee läpi: rivikattavuus vähintään 80%
- Userstoryn hyväksymiskriteerit toteutuu

![GitHub Actions](https://github.com/sannahan/lukuvinkkikirjasto/workflows/Java%20CI%20with%20Gradle/badge.svg)
[![codecov](https://codecov.io/gh/sannahan/lukuvinkkikirjasto/branch/main/graph/badge.svg?token=D5RRH7MIFT)](https://codecov.io/gh/sannahan/lukuvinkkikirjasto)
[![Checkstyle](https://github.com/sannahan/lukuvinkkikirjasto/actions/workflows/checkstyle.yml/badge.svg)](https://github.com/sannahan/lukuvinkkikirjasto/actions/workflows/checkstyle.yml)

