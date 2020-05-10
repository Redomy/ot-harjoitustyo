# Rakenne:

tällä hetkellä sovelluksen pakkausrakenteessa on kaksi pakkausta Minesweeper.ui ja Minesweeper.logic.
Tarkoituksena on lisätä vielä yksi pakkaus tietokantoja ja pysyväistalletusta hoitavalle koodille.

# Käyttöliittymä:

Sovellukseni käyttöliittymässä on kolme eri näkymää:
- Miinaharavan peli näkymä
- Päävalikon näkymä
- Parhaitten pisteiden näkymä

Näkymät on toteutettu Scene olioina ja ne liitetään sovelluksen Stage olioon.
Käyttöliittymä on toteutettu luokassa Minesweeper.ui.MinesweeperUI.
On huomioitavaa myös, että sovelluksen tietokantoja hoitava koodi on myös käyttöliittymän pakkauksessa
mutta se on tarkoitus sijoittaa omaan pakkaukseen.

# Sovelluslogiikka:

Sovelluslogiikkaan kuuluu luokat Minesweeper ja Tile.
Minesweeper luokka hoitaa pelin alustan teknisen luomisen, solujen luomisen ja tarjoaa soluille
myös tärkeän metodin niiden ympäristön selvittämiseksi.
Tile luokka taas kuvaa pelin soluja ja se myös määrittää mitä tapahtuu sen ympäristön soluille tietyssä tilanteessa.

# Pysyväistalletus:

Luokka DatabaseUser hoitaa tietokantaan tallentamisen ja lukemisen.
Sovellus käyttää sqlite tietokantaa, johon tallennetaan nimiä ja pisteitä.
