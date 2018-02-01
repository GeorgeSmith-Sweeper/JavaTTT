package com.EighthLight.app;

import com.EighthLight.app.Interfaces.IPlayer;
import com.EighthLight.app.Mocks.MockBoard;
import com.EighthLight.app.Mocks.MockConfig;
import com.EighthLight.app.Mocks.MockPlayer;
import com.EighthLight.app.Mocks.MockUi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class gameTests {
    private MockPlayer player1 = new MockPlayer("X");
    private MockPlayer player2 = new MockPlayer("O");
    private ArrayList<IPlayer> players = new ArrayList<>(Arrays.asList(player1, player2));
    private String correctInput;
    private String incorrectInput;
    private ArrayList boardState;
    private ArrayList gameIsTiedValues;
    private ArrayList aPlayerWonValues;

    @BeforeEach
    public void setUp() {
        correctInput = Constants.CORRECT_INPUT;
        incorrectInput = Constants.INCORRECT_INPUT;
        boardState = new ArrayList();
    }

    @Test
    void gameIsCreatedWithABoardAndPlayers() {

        MockBoard board = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
        ArrayList userInputs = new ArrayList<>(Arrays.asList("1", correctInput));
        MockUi ui = new MockUi(userInputs);
        MockConfig config = new MockConfig(ui, players, board);
        new Game(ui, config);

        assertEquals(1, config.getNumTimesGetBoardCalled());
        assertEquals(1, config.getNumTimesGetPlayersCalled());
        assertEquals(player1, config.getPlayers().get(0));
        assertEquals(player2, config.getPlayers().get(1));
        assertEquals(player1, config.getCurrentPlayer());
    }

    @Test
    void startGameTiedWithCorrectInput() {

        gameIsTiedValues = new ArrayList();
        gameIsTiedValues.add(true);
        aPlayerWonValues = new ArrayList();
        aPlayerWonValues.add(false);

        MockBoard board = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
        ArrayList userInputs = new ArrayList<>(Arrays.asList("1", correctInput));
        MockUi ui = new MockUi(userInputs);
        MockConfig config = new MockConfig(ui, players, board);
        Game game = new Game(ui, config);
        game.start();


        assertEquals(4, ui.getDisplayArgs().size());
        assertEquals(null, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals(Constants.TIE_GAME_MSG, ui.getDisplayArgs().get(2));
        assertEquals(null, ui.getDisplayArgs().get(3));

        assertEquals(2, ui.getPresentBoardArgs().size());

        assertEquals(board, player1.getMakeMoveArgs().get(0));

        assertEquals(4, board.getNumTimesGetSpacesCalled());
        assertEquals(player1.getSymbol(), board.getHasPlayerWonArgs().get(0));
        assertEquals(boardState, board.getGameTieArgs().get(0));
    }

    @Test
    void startGameTiedWithIncorrectInput() {
        gameIsTiedValues = new ArrayList();
        gameIsTiedValues.add(true);
        aPlayerWonValues = new ArrayList();
        aPlayerWonValues.add(false);

        MockBoard board = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
        ArrayList userInputs = new ArrayList<>(Arrays.asList("1", incorrectInput, correctInput));
        MockUi ui = new MockUi(userInputs);
        MockConfig config = new MockConfig(ui, players, board);
        Game game = new Game(ui, config);
        game.start();

        assertEquals(4, ui.getDisplayArgs().size());
        assertEquals(null, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals(Constants.TIE_GAME_MSG, ui.getDisplayArgs().get(2));
        assertEquals(null, ui.getDisplayArgs().get(3));

        assertEquals(4, board.getNumTimesGetSpacesCalled());
        assertEquals(player1.getSymbol(), board.getHasPlayerWonArgs().get(0));
        assertEquals(boardState, board.getGameTieArgs().get(0));
    }

    @Test
    void startGameWithWinningStateAndWithCorrectInput() {
        gameIsTiedValues = new ArrayList();
        gameIsTiedValues.add(false);
        aPlayerWonValues = new ArrayList();
        aPlayerWonValues.add(true);

        MockBoard board = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
        ArrayList userInputs = new ArrayList<>(Arrays.asList("1", correctInput));
        MockUi ui = new MockUi(userInputs);
        MockConfig config = new MockConfig(ui, players, board);
        Game game = new Game(ui, config);
        game.start();

        assertEquals(4, ui.getDisplayArgs().size());
        assertEquals(null, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals("X WINS!", ui.getDisplayArgs().get(2));
        assertEquals(null, ui.getDisplayArgs().get(3));

        assertEquals(2, ui.getPresentBoardArgs().size());
        assertEquals(4, board.getNumTimesGetSpacesCalled());
        assertEquals(player1.getSymbol(), board.getHasPlayerWonArgs().get(0));
        assertEquals(boardState, board.getGameTieArgs().get(0));

        assertEquals(3, player1.getNumTimesGetSymbolCalled());
    }

    @Test
    void startGameWithWinningStateAndWithIncorrectInput() {
        gameIsTiedValues = new ArrayList();
        gameIsTiedValues.add(false);

        aPlayerWonValues = new ArrayList();
        aPlayerWonValues.add(true);

        MockBoard board = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
        ArrayList userInputs = new ArrayList<>(Arrays.asList("1", incorrectInput, correctInput));
        MockUi ui = new MockUi(userInputs);
        MockConfig config = new MockConfig(ui, players, board);
        Game game = new Game(ui, config);
        game.start();

        assertEquals(4, ui.getDisplayArgs().size());
        assertEquals(null, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals("X WINS!", ui.getDisplayArgs().get(2));
        assertEquals(null, ui.getDisplayArgs().get(3));

        assertEquals(2, ui.getPresentBoardArgs().size());

        assertEquals(4, board.getNumTimesGetSpacesCalled());
        assertEquals(player1.getSymbol(), board.getHasPlayerWonArgs().get(0));
        assertEquals(boardState, board.getGameTieArgs().get(0));

        assertEquals(3, player1.getNumTimesGetSymbolCalled());
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
        ArrayList userInputs = new ArrayList<>(Arrays.asList("1", incorrectInput, correctInput, correctInput));
        MockUi ui = new MockUi(userInputs);
        MockConfig config = new MockConfig(ui, players, board);
        Game game = new Game(ui, config);
        game.start();

        assertEquals(6, ui.getDisplayArgs().size());
        assertEquals(null, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals(null, ui.getDisplayArgs().get(2));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(3));
        assertEquals(Constants.TIE_GAME_MSG, ui.getDisplayArgs().get(4));
        assertEquals(null, ui.getDisplayArgs().get(5));

        assertEquals(3, ui.getPresentBoardArgs().size());
        assertEquals(7, board.getNumTimesGetSpacesCalled());
        assertEquals(player1.getSymbol(), board.getHasPlayerWonArgs().get(0));
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
        ArrayList userInputs = new ArrayList<>(Arrays.asList("1", correctInput, correctInput));
        MockUi ui = new MockUi(userInputs);
        MockConfig config = new MockConfig(ui, players, board);
        Game game = new Game(ui, config);
        game.start();

        assertEquals(6, ui.getDisplayArgs().size());
        assertEquals(null, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals(null, ui.getDisplayArgs().get(2));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(3));
        assertEquals(Constants.TIE_GAME_MSG, ui.getDisplayArgs().get(4));
        assertEquals(null, ui.getDisplayArgs().get(5));

        assertEquals(3, ui.getPresentBoardArgs().size());
        assertEquals(7, board.getNumTimesGetSpacesCalled());
        assertEquals(player1.getSymbol(), board.getHasPlayerWonArgs().get(0));
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
        ArrayList userInputs = new ArrayList<>(Arrays.asList("1", incorrectInput, correctInput, correctInput));
        MockUi ui = new MockUi(userInputs);
        MockConfig config = new MockConfig(ui, players, board);
        Game game = new Game(ui, config);
        game.start();

        assertEquals(6, ui.getDisplayArgs().size());
        assertEquals(null, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals(null, ui.getDisplayArgs().get(2));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(3));
        assertEquals("O WINS!", ui.getDisplayArgs().get(4));
        assertEquals(null, ui.getDisplayArgs().get(5));

        assertEquals(3, ui.getPresentBoardArgs().size());
        assertEquals(7, board.getNumTimesGetSpacesCalled());
        assertEquals(player1.getSymbol(), board.getHasPlayerWonArgs().get(0));
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
        ArrayList userInputs = new ArrayList<>(Arrays.asList("1", correctInput, correctInput));
        MockUi ui = new MockUi(userInputs);
        MockConfig config = new MockConfig(ui, players, board);
        Game game = new Game(ui, config);
        game.start();

        assertEquals(6, ui.getDisplayArgs().size());
        assertEquals(null, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals(null, ui.getDisplayArgs().get(2));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(3));
        assertEquals("O WINS!", ui.getDisplayArgs().get(4));
        assertEquals(null, ui.getDisplayArgs().get(5));

        assertEquals(3, ui.getPresentBoardArgs().size());
        assertEquals(7, board.getNumTimesGetSpacesCalled());
        assertEquals(player1.getSymbol(), board.getHasPlayerWonArgs().get(0));
        assertEquals(boardState, board.getGameTieArgs().get(0));
    }
}