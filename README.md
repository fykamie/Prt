Piramis Éptítő

Általános

A játék (maven project) célja hogy hamarabb rakja a játékos növekvő sorrendbe a piramisja kockáit, mint az ellenfele úgy hogy egymással cserélnek.
Játékos vs AI.

Általános információk

MVC mintát követi.
JavaFX a front-end.
Loggolt modell, dao package.

Adatbázis

JPA-val csatolt mySQL távoli szerver.
Piramisok kockáit tárolja (kockak) hogy melyik piramisban találhatóak (kihez tartoznak).

CheckStyle

parancs: mvn site
"checkstyle.xml"-n alapul.

Test:

parancs: mvn test
Néhány egyszerű JUnit teszt a modell és services osztályaira.

JAR állomány készítése:

parancs: mvn package

Futtatás:

parancs: mvn compile exec:java
A main osztály: MainApp.java

Jó szórakozást!
