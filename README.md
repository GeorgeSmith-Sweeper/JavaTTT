# TIC TAC TOE - Java Edition

### Requirements
This game requires that you have `java` and `maven` installed on your machine.

Please run `java --version` from the commandline to check if you have java.

If you have Java, your output should be similar to the one below.

![YouHaveJava](/images/YouHaveJava.png?raw=true)

If you don't get a different output, then follow the instructions at this link: [Getting Java 9](http://www.oracle.com/technetwork/java/javase/downloads/jdk9-downloads-3848520.html)

In order to run the test suites for this game you'll need to have Maven installed.

Please run `mvn --version` from the commandline to check if it is installed.

If you have mvn, your output should be similar to the one below.

![YouHaveMaven](/images/YouHaveMaven.png?raw=true)

If you don't get a different output, then follow the instructions at this link: [Installing Maven](https://maven.apache.org/download.cgi)

I personally believe that the eaisest way to get started is through `homebrew`, using the command `brew install maven`.

### Getting started

1. Run `git clone https://github.com/GeorgeSmith-Sweeper/JavaTTT.git` in your terminal

2. Run `java -jar JavaTicTacToe-1.0-SNAPSHOT.jar` at the root to start the game.

### Playing the Game

The game will begin by displaying a board, and a prompt to pick a spot.

![Game Start](/images/GameStart.png?raw=true)

Begin playing by selecting a spot 0-8.
Player 1 is 'X' Player 2 is 'O'.
Player 1 has the first move.

The game will end when the board is full.

### Tests

There are 14 tests for this game, and currently they all pass.

The tests for this game can be run with the command `mvn test`, at the root.
