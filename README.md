# TIC TAC TOE - Java Edition

### Requirements
This game requires that you have `java` and `maven` installed on your machine.

Please run `java -version` from the commandline to check if you have java.

If you have Java, your output should be similar to the one below.

![YouHaveJava](/images/YouHaveJava.png?raw=true)

If you don't get a different output, then follow the instructions at this link: [Getting Java 9](http://www.oracle.com/technetwork/java/javase/downloads/jdk9-downloads-3848520.html)

In order to run the test suites for this game you'll need to have Maven installed.

Please run `mvn --version` from the commandline to check if it is installed.

If you have mvn, your output should be similar to the one below.

![YouHaveMaven](/images/YouHaveMaven.png?raw=true)

If you get a different output, then there are a couple of ways for you to get up and running with maven.

I personally believe that the eaisest way to get started is through `homebrew`, using the command `brew install maven`.

[Installing brew](https://brew.sh/)

The other, less user friendly way is to follow the instructions at this link: [Installing Maven](https://maven.apache.org/download.cgi)


### Getting started

1. Run `git clone https://github.com/GeorgeSmith-Sweeper/JavaTTT.git` in your terminal.

2. Run `cd JavaTTT`.

3. Run `mvn package` at the root.

4. Run `java -cp target/JavaTicTacToe-1.0-SNAPSHOT.jar com.EighthLight.app.App` at the root to start the game.


### Playing the Game

The game will begin by displaying prompts for player symbols, game mode, and board size.

The current game modes available are human vs human, and human vs AI.

### Tests

There are 51 tests for this game, and currently they all pass.

The tests for this game can be run with the command `mvn test`, at the root.