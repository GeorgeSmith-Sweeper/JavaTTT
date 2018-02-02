# TIC TAC TOE - Java Edition


### Dependencies
This game requires that you have `java` and `maven` installed on your machine.

Please run `java -version` from the command line to check if you have java.

If you have Java, your output should be similar to the one below.

![YouHaveJava](/images/YouHaveJava.png?raw=true)

If you don't get a different output, then follow the instructions at this link: [Getting Java 9](http://www.oracle.com/technetwork/java/javase/downloads/jdk9-downloads-3848520.html)

In order to run the test suites for this game, and create a game package you'll need to have Maven installed.

Please run `mvn --version` from the command line to check if it is installed.

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

![Board Size Prompt](/images/boardSizePrompt.png?raw=true)

Next you can select symbols for the player 1, and player 2.

![Player 1 Symbol](/images/player1Symbol.png?raw=true)

![Player 2 Symbol](/images/player2Symbol.png?raw=true)

Next is a game mode prompt

![Game Mode Prompt](/images/gameModePrompt.png?raw=true)

If you've chosen to play against the Ai you will be given the option of three difficulty levels.

![Difficulty Level Prompt](/images/difficultylevelPrompt.png?raw=true)

Finally you will be asked if you would like to have the first move.

![Who Move First](/images/whoMovesFirstPrompt.png?raw=true)

### Tests

There are 61 tests for this game! The tests can executed my running `mvn test` at the root.

### Points of interest

The computer is unbeatable when played on a standard 3x3 board (seriously it's kind of a jerk),
however when played on boards of 4x4 or greater, it gets bogged down in move computations, and fails
to make a move in an acceptable time. The next version of Tic-Tac-Toe will add Alpha-Beta pruning to my negaMax function,
in order to prune number of branches that the computer must search for the 'best move'. 
This will allow the computer to handle larger board sizes while remaining unbeatable.

Posts by crafters at 8th-Light who also did negamax: 

[Joys of minimax and negamax](https://medium.com/@indykidd/joys-of-minimax-and-negamax-ee5e456977e2)

[Ruby tic tac toe negamax with alpha beta pruning](https://medium.com/@pelensky/ruby-tic-tac-toe-negamax-with-alpha-beta-pruning-c1126172fb5a)




