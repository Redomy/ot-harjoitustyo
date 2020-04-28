# ot_harjoitustyo

## Miinaharava peli
Sovellus on klassinen miinaharava-peli, jossa pelaaja voi pelin loputtua 
tallentaa tuloksensa tietokantaan haluamallaan nimellä varustettuna.

## Dokumentaatio

## Komentorivitoiminnot
Kaikki komennot suoritetaan MineSweeper kansiosta.

### Testaus
Testit voi suorittaa komennolla:
mvn test

Testikattavuusraportti luodaan komennolla:
mvn jacoco:report

Kattavuusraportin voi katsoa selaimella avaamalla tiedoston target/site/jacoco/index.html

### Suoritettava jar
Suoritettava jar generoidaan komennolla:
mvn package

### Checkstyle
Checkstylen tarkistukset suoritetaan komennolla:
mvn jxr:jxr checkstyle:checkstyle

Jos haluat nähdä mahdolliset checkstyle virheet, avaa selaimella tiedosto: target/site/checkstyle.html
