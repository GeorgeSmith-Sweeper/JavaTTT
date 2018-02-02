# TIC TAC TOE - Java Edition

### Dependencies
This game requires that you have `java` and `maven` installed on your machine.

Please run `java -version` from the commandline to check if you have java.

If you have Java, your output should be similar to the one below.

![YouHaveJava](/images/YouHaveJava.png?raw=true)

If you don't get a different output, then follow the instructions at this link: [Getting Java 9](http://www.oracle.com/technetwork/java/javase/downloads/jdk9-downloads-3848520.html)

In order to run the test suites for this game, and create a game package you'll need to have Maven installed.

Please run `mvn --version` from the commandline to check if it is installed.

If you have Maven, your output should be similar to the one below.

![YouHaveMaven](/images/YouHaveMaven.png?raw=true)

If you get a different output, then the easiest way to get started is with the package manager Homebrew. 
To check if you have Homebrew, please run `brew --version`. 

If you don't, installation is very easy, just follow the instructions at this link: [Installing brew](https://brew.sh/).

Once you have Homebrew, you can install maven using the command `brew install maven`.


### Getting started

1. Run `git clone https://github.com/GeorgeSmith-Sweeper/JavaTTT.git` in your terminal.

2. Run `cd JavaTTT`.

3. Run `mvn package` at the root.

4. Run `java -jar target/JavaTicTacToe-1.0-SNAPSHOT.jar` at the root to start the game.


### Playing the Game

The game will begin by displaying a prompt for a board size.

// add image

Next you can select symbols for the player 1, and player 2.

// add image

Next is a game mode prompt

// add image

If you've chosen to play against the Ai you will be giveen the option of three difficulty levels.

// add image

Finally you will be asked if you would like to have the first move.

// add image


### Tests

There are 65 tests for this game. The tests can be run with the command `mvn test`, at the root.