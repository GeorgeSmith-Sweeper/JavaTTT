package com.EighthLight.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class gameTests {
    private MockPlayer playerOne;
    private MockPlayer playerTwo;
    private String correctInput;
    private String incorrectInput;
    private ArrayList boardState;
    private ArrayList gameIsTiedValues;
    private ArrayList aPlayerWonValues;

    @BeforeEach
    private void setUp() {
        playerOne = new MockPlayer("X");
        playerTwo = new MockPlayer("O");
        correctInput = Constants.CORRECT_INPUT;
        incorrectInput = Constants.INCORRECT_INPUT;
        boardState = new ArrayList();
    }

    @Test
    void startGameTiedWithCorrectInput() {

        gameIsTiedValues = new ArrayList();
        gameIsTiedValues.add(true);
        aPlayerWonValues = new ArrayList();
        aPlayerWonValues.add(false);

        MockBoard board = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
        ArrayList userInputs = new ArrayList(Arrays.asList("1", correctInput));
        MockUi ui = new MockUi(userInputs);
        ArrayList<IPlayer> players = new ArrayList(Arrays.asList(playerOne, playerTwo));
        MockConfig config = new MockConfig(ui, players);
        Game game = new Game(ui, board, config);
        game.start();


        assertEquals(4, ui.getDisplayArgs().size());
        assertEquals(null, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals(Constants.TIE_GAME_MSG, ui.getDisplayArgs().get(2));
        assertEquals(null, ui.getDisplayArgs().get(3));

        assertEquals(2, ui.getPresentBoardArgs().size());
        assertEquals(boardState, ui.getPresentBoardArgs().get(0));
        assertEquals(boardState, ui.getPresentBoardArgs().get(1));

        assertEquals(board, playerOne.getMakeMoveArgs().get(0));

        assertEquals(3, board.getNumTimesGetSpacesCalled());
        assertEquals(playerOne, board.getHasPlayerWonArgs().get(0));
        assertEquals(boardState, board.getGameTieArgs().get(0));
    }

    @Test
    void startGameTiedWithIncorrectInput() {
        gameIsTiedValues = new ArrayList();
        gameIsTiedValues.add(true);
        aPlayerWonValues = new ArrayList();
        aPlayerWonValues.add(false);

        MockBoard board = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
        ArrayList userInputs = new ArrayList(Arrays.asList("1", incorrectInput, correctInput));
        MockUi ui = new MockUi(userInputs);
        ArrayList<IPlayer> players = new ArrayList(Arrays.asList(playerOne, playerTwo));
        MockConfig config = new MockConfig(ui, players);
        Game game = new Game(ui, board, config);
        game.start();

        assertEquals(4, ui.getDisplayArgs().size());
        assertEquals(null, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals(Constants.TIE_GAME_MSG, ui.getDisplayArgs().get(2));
        assertEquals(null, ui.getDisplayArgs().get(3));

        assertEquals(3, board.getNumTimesGetSpacesCalled());
        assertEquals(playerOne, board.getHasPlayerWonArgs().get(0));
        assertEquals(boardState, board.getGameTieArgs().get(0));
    }

    @Test
    void startGameWithWinningStateAndWithCorrectInput() {
        gameIsTiedValues = new ArrayList();
        gameIsTiedValues.add(false);
        aPlayerWonValues = new ArrayList();
        aPlayerWonValues.add(true);

        MockBoard board = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
        ArrayList userInputs = new ArrayList(Arrays.asList("1", correctInput));
        MockUi ui = new MockUi(userInputs);
        ArrayList<IPlayer> players = new ArrayList(Arrays.asList(playerOne, playerTwo));
        MockConfig config = new MockConfig(ui, players);
        Game game = new Game(ui, board, config);
        game.start();

        assertEquals(4, ui.getDisplayArgs().size());
        assertEquals(null, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals("X WINS!", ui.getDisplayArgs().get(2));
        assertEquals(null, ui.getDisplayArgs().get(3));

        assertEquals(2, ui.getPresentBoardArgs().size());
        assertEquals(boardState, ui.getPresentBoardArgs().get(0));
        assertEquals(boardState, ui.getPresentBoardArgs().get(1));

        assertEquals(3, board.getNumTimesGetSpacesCalled());
        assertEquals(playerOne, board.getHasPlayerWonArgs().get(0));
        assertEquals(boardState, board.getGameTieArgs().get(0));

        assertEquals(1, playerOne.getNumTimesGetSymbolCalled());
    }

    @Test
    void startGameWithWinningStateAndWithIncorrectInput() {
        gameIsTiedValues = new ArrayList();
        gameIsTiedValues.add(false);

        aPlayerWonValues = new ArrayList();
        aPlayerWonValues.add(true);

        MockBoard board = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
        ArrayList userInputs = new ArrayList(Arrays.asList("1", incorrectInput, correctInput));
        MockUi ui = new MockUi(userInputs);
        ArrayList<IPlayer> players = new ArrayList(Arrays.asList(playerOne, playerTwo));
        MockConfig config = new MockConfig(ui, players);
        Game game = new Game(ui, board, config);
        game.start();

        assertEquals(4, ui.getDisplayArgs().size());
        assertEquals(null, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals("X WINS!", ui.getDisplayArgs().get(2));
        assertEquals(null, ui.getDisplayArgs().get(3));

        assertEquals(2, ui.getPresentBoardArgs().size());
        assertEquals(boardState, ui.getPresentBoardArgs().get(0));
        assertEquals(boardState, ui.getPresentBoardArgs().get(0));

        assertEquals(3, board.getNumTimesGetSpacesCalled());
        assertEquals(playerOne, board.getHasPlayerWonArgs().get(0));
        assertEquals(boardState, board.getGameTieArgs().get(0));

        assertEquals(1, playerOne.getNumTimesGetSymbolCalled());
    }

    @Test
    void startGameWithAStateTwoAwayFromTiedAndIncorrectInput() {
        gameIsTiedValues = new ArrayList();
        gameIsTiedValues.add(false);
        gameIsTiedValues.add(true);

        aPlayerWonValues = new ArrayList();
        aPlayerWonValues.add(false);
        aPlayerWonValues.add(false);

        MockBoard board = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
        ArrayList userInputs = new ArrayList(Arrays.asList("1", incorrectInput, correctInput, correctInput));
        MockUi ui = new MockUi(userInputs);
        ArrayList<IPlayer> players = new ArrayList(Arrays.asList(playerOne, playerTwo));
        MockConfig config = new MockConfig(ui, players);
        Game game = new Game(ui, board, config);
        game.start();

        assertEquals(6, ui.getDisplayArgs().size());
        assertEquals(null, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals(null, ui.getDisplayArgs().get(2));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(3));
        assertEquals(Constants.TIE_GAME_MSG, ui.getDisplayArgs().get(4));
        assertEquals(null, ui.getDisplayArgs().get(5));

        assertEquals(3, ui.getPresentBoardArgs().size());
        assertEquals(boardState, ui.getPresentBoardArgs().get(0));
        assertEquals(boardState, ui.getPresentBoardArgs().get(0));
        assertEquals(boardState, ui.getPresentBoardArgs().get(0));

        assertEquals(5, board.getNumTimesGetSpacesCalled());
        assertEquals(playerOne, board.getHasPlayerWonArgs().get(0));
        assertEquals(boardState, board.getGameTieArgs().get(0));
    }

    @Test
    void startGameWithAStateTwoAwayFromTiedAndCorrectInput() {
        gameIsTiedValues = new ArrayList();
        gameIsTiedValues.add(false);
        gameIsTiedValues.add(true);

        aPlayerWonValues = new ArrayList();
        aPlayerWonValues.add(false);
        aPlayerWonValues.add(false);

        MockBoard board = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
        ArrayList userInputs = new ArrayList(Arrays.asList("1", correctInput, correctInput));
        MockUi ui = new MockUi(userInputs);
        ArrayList<IPlayer> players = new ArrayList(Arrays.asList(playerOne, playerTwo));
        MockConfig config = new MockConfig(ui, players);        Game game = new Game(ui, board, config);
        game.start();

        assertEquals(6, ui.getDisplayArgs().size());
        assertEquals(null, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals(null, ui.getDisplayArgs().get(2));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(3));
        assertEquals(Constants.TIE_GAME_MSG, ui.getDisplayArgs().get(4));
        assertEquals(null, ui.getDisplayArgs().get(5));

        assertEquals(3, ui.getPresentBoardArgs().size());
        assertEquals(boardState, ui.getPresentBoardArgs().get(0));
        assertEquals(boardState, ui.getPresentBoardArgs().get(0));
        assertEquals(boardState, ui.getPresentBoardArgs().get(0));

        assertEquals(5, board.getNumTimesGetSpacesCalled());
        assertEquals(playerOne, board.getHasPlayerWonArgs().get(0));
        assertEquals(boardState, board.getGameTieArgs().get(0));
    }

    @Test
    void startGameWithAStateTwoAwayFromWinAndIncorrectInput() {
        gameIsTiedValues = new ArrayList();
        gameIsTiedValues.add(false);
        gameIsTiedValues.add(false);

        aPlayerWonValues = new ArrayList();
        aPlayerWonValues.add(false);
        aPlayerWonValues.add(true);


        MockBoard board = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
        ArrayList userInputs = new ArrayList(Arrays.asList("1", incorrectInput, correctInput, correctInput));
        MockUi ui = new MockUi(userInputs);
        ArrayList<IPlayer> players = new ArrayList(Arrays.asList(playerOne, playerTwo));
        MockConfig config = new MockConfig(ui, players);
        Game game = new Game(ui, board, config);
        game.start();

        assertEquals(6, ui.getDisplayArgs().size());
        assertEquals(null, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals(null, ui.getDisplayArgs().get(2));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(3));
        assertEquals("O WINS!", ui.getDisplayArgs().get(4));
        assertEquals(null, ui.getDisplayArgs().get(5));

        assertEquals(3, ui.getPresentBoardArgs().size());
        assertEquals(boardState, ui.getPresentBoardArgs().get(0));

        assertEquals(5, board.getNumTimesGetSpacesCalled());
        assertEquals(playerOne, board.getHasPlayerWonArgs().get(0));
        assertEquals(boardState, board.getGameTieArgs().get(0));
    }

    @Test
    void startGameWithAStateTwoAwayFromWinAndCorrectInput() {
        gameIsTiedValues = new ArrayList();
        gameIsTiedValues.add(false);
        gameIsTiedValues.add(false);

        aPlayerWonValues = new ArrayList();
        aPlayerWonValues.add(false);
        aPlayerWonValues.add(true);

        MockBoard board = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
        ArrayList userInputs = new ArrayList(Arrays.asList("1", correctInput, correctInput));
        MockUi ui = new MockUi(userInputs);
        ArrayList<IPlayer> players = new ArrayList(Arrays.asList(playerOne, playerTwo));
        MockConfig config = new MockConfig(ui, players);
        Game game = new Game(ui, board, config);
        game.start();

        assertEquals(6, ui.getDisplayArgs().size());
        assertEquals(null, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals(null, ui.getDisplayArgs().get(2));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(3));
        assertEquals("O WINS!", ui.getDisplayArgs().get(4));
        assertEquals(null, ui.getDisplayArgs().get(5));

        assertEquals(3, ui.getPresentBoardArgs().size());
        assertEquals(boardState, ui.getPresentBoardArgs().get(0));
        assertEquals(boardState, ui.getPresentBoardArgs().get(0));
        assertEquals(boardState, ui.getPresentBoardArgs().get(0));

        assertEquals(5, board.getNumTimesGetSpacesCalled());
        assertEquals(playerOne, board.getHasPlayerWonArgs().get(0));
        assertEquals(boardState, board.getGameTieArgs().get(0));
    }
}