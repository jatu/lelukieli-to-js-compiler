## TIEA241 harjoitustyö

#### *jokaisen työn tekemiseen osallistuneen opiskelijan nimi

Niko Autio

Jari Tuikka

#### *ryhmätyön tapauksessa lyhyt selostus siitä, kuka teki mitäkin

Pääasiallisesti parikoodasimme

#### *tieto ohjelman kääntämisessä ja käyttämisessä tarvittavista apuohjelmista ja kirjastoista (mukaan lukien mahdolliset järjestelmäriippuvuudet, esimerkiksi jos toimii vain Windowsissa)

Ohjelman tarvitsee Scala-tuen ja sbt:n joka on scalan buildaustyökalu.

#### *ohjeet ohjelman kääntämiseksi lähdekoodista

sbt compile

#### *ohjeet ohjelman käyttämiseksi (pelin tapauksessa läpipelausohje)

sbt "run --help"
sbt "run --file input.lk output.js"

#### *ohjelman toteutuksen pääpiirteiden esittely (kiinnittäen erityistä huomiota kurssin sisältöjen näkymiseen ohjelmassa)

Ohjelma osaa parsia kielimäärityksen perusteella AST-treen ja tarjoaa API:n AST-treeiden väliseen muutokseen sekä muuttaa AST-treen tekstiksi. Ohjelmaan on implementoitu meidän omasta kielestä transformointi Javascript-subsettiin. 
Sovelluksessa määritellään kaksi ohjelmointikieltä käyttäen kontekstittomia kielioppeja. Siinä käytetään myös apuna säännöllisiä kielioppeja kielen tägäykseen (esim. numeroiden tai stringien tunnistus). 