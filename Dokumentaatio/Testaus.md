# Testausdokumentti

Sovellusta on testattu JUnitin testeillä sekä järjestelmätestaus on suoritettu manuaalisesti.

## JUnit testit
### Sovelluslogiikka
Sovelluksen pakkauksen Minesweeper.logic toiminnallisuuksia ja luokkia on testattu testipakkauksen
testeillä MinesweeperTest ja TileTest. Ne testaavat, että sovelluksen alustaminen ja logiikka toimivat oikein.

Valitettavasti pakkauksen Minesweeper.database luokan DatabaseUserille ei ole testejä, koska en tiennyt järkevää ratkaisua
implementoida tietokannalle testejä.

### Testauskattavuus

## Järjestelmätestaus
Järjestelmätestaus on suoritettu manuaalisesti. 
Kaikki sovelluksen toiminnallisuudet on käyty läpi ja sovelluksen tietokantapuolta
on testattu eri tilanteissa, kuten jos tietokantaa ei ole vielä tehty verrattuna
siihen, että se olisi jo valmiiksi luotu.

## Rajoitukset
Sovelluksen tietokantapuolella ei ole hyviä testejä.
