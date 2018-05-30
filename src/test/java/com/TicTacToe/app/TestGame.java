package com.TicTacToe.app;

import com.TicTacToe.app.Interfaces.IPlayer;
import com.TicTacToe.app.Mocks.MockBoard;
import com.TicTacToe.app.Mocks.MockConfig;
import com.TicTacToe.app.Mocks.MockPlayer;
import com.TicTacToe.app.Mocks.MockUi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class gameTests {
    private String player1Symbol = "X";
    private String player2Symbol = "O";
    private MockPlayer player1 = new MockPlayer(player1Symbol);
    private MockPlayer player2 = new MockPlayer(player2Symbol);
    private ArrayList<IPlayer> players = new ArrayList<>(Arrays.asList(player1, player2));
    private ArrayList boardState;
    private ArrayList gameIsTiedValues = new ArrayList();
    private ArrayList aPlayerWonValues = new ArrayList();
    private ArrayList userInputs = new ArrayList();

    @BeforeEach
    public void setUp() {
        boardState = new ArrayList();
    }

    @AfterEach
    public void resetValues() {
        gameIsTiedValues.clear();
        aPlayerWonValues.clear();
    }

    @Test
    void gameIsCreatedWithABoardAndPlayers() {

        MockBoard board = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
        MockUi ui = new MockUi(userInputs);
        MockConfig config = new MockConfig(ui, players, board);
        new Game(ui, config);

        assertEquals(1, config.getNumTimesGetBoardCalled());
        assertEquals(1, config.getNumTimesGetPlayersCalled());
        Assertions.assertEquals(player1, config.getPlayers().get(0));
        Assertions.assertEquals(player2, config.getPlayers().get(1));
        Assertions.assertEquals(player1, config.getCurrentPlayer());
    }

    @Test
    void gameCallsAndPromptsAreCorrectWhenStateIsOneMoveFromTied() {
        gameIsTiedValues.add(true);
        aPlayerWonValues.add(false);

        MockBoard board = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
        MockUi ui = new MockUi(userInputs);
        MockConfig config = new MockConfig(ui, players, board);
        Game game = new Game(ui, config);
        game.start();

        assertEquals(4, ui.getDisplayArgs().size());
        assertEquals(Constants.DISPLAYED_STRING, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals(Constants.TIE_GAME_MSG, ui.getDisplayArgs().get(2));
        assertEquals(Constants.DISPLAYED_STRING, ui.getDisplayArgs().get(3));

        assertEquals(2, ui.getPresentBoardArgs().size());

        assertEquals(board, player1.getMakeMoveArgs().get(0));

        assertEquals(4, board.getNumTimesGetSpacesCalled());
        assertTrue(board.getHasPlayerWonArgs().get(0) instanceof ArrayList);
        assertEquals(player1Symbol, board.getHasPlayerWonArgs().get(1));
        assertEquals(boardState, board.getGameTieArgs().get(0));
    }

    @Test
    void gameCallsAndPromptsAreCorrectWhenStateIsOneMoveFromAWin() {
        gameIsTiedValues.add(false);
        aPlayerWonValues.add(true);

        MockBoard board = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
        MockUi ui = new MockUi(userInputs);
        MockConfig config = new MockConfig(ui, players, board);
        Game game = new Game(ui, config);
        game.start();

        assertEquals(4, ui.getDisplayArgs().size());
        assertEquals(Constants.DISPLAYED_STRING, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals(player1Symbol + " WINS!", ui.getDisplayArgs().get(2));
        assertEquals(Constants.DISPLAYED_STRING, ui.getDisplayArgs().get(3));

        assertEquals(4, board.getNumTimesGetSpacesCalled());
        assertTrue(board.getHasPlayerWonArgs().get(0) instanceof ArrayList);
        assertEquals(player1Symbol, board.getHasPlayerWonArgs().get(1));
        assertEquals(boardState, board.getGameTieArgs().get(0));
    }

    @Test
    void gameSwitchesPlayersWhenThereAreTwoMoveLeft() {
        gameIsTiedValues.add(false);
        gameIsTiedValues.add(false);
        aPlayerWonValues.add(false);
        aPlayerWonValues.add(true);

        MockBoard board = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
        MockUi ui = new MockUi(userInputs);
        MockConfig config = new MockConfig(ui, players, board);
        Game game = new Game(ui, config);
        game.start();

        assertEquals(3, ui.getPresentBoardArgs().size());
        assertEquals(6, ui.getDisplayArgs().size());
        assertEquals(Constants.DISPLAYED_STRING, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals(Constants.DISPLAYED_STRING, ui.getDisplayArgs().get(2));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(3));
        assertEquals(player2Symbol + " WINS!", ui.getDisplayArgs().get(4));
        assertEquals(Constants.DISPLAYED_STRING, ui.getDisplayArgs().get(5));

        assertEquals(7, board.getNumTimesGetSpacesCalled());
        assertTrue(board.getHasPlayerWonArgs().get(0) instanceof ArrayList);
        assertEquals(player1Symbol, board.getHasPlayerWonArgs().get(1));
        assertTrue(board.getHasPlayerWonArgs().get(2) instanceof ArrayList);
        assertEquals(player2Symbol, board.getHasPlayerWonArgs().get(3));
        assertEquals(boardState, board.getGameTieArgs().get(0));
    }

    @Test
    void gameCallsAndPromptsAreCorrectWhenStateIsTwoMovesFromTied() {
        gameIsTiedValues.add(false);
        gameIsTiedValues.add(true);
        aPlayerWonValues.add(false);
        aPlayerWonValues.add(false);

        MockBoard board = new MockBoard(boardState, aPlayerWonValues, gameIsTiedValues);
        MockUi ui = new MockUi(userInputs);
        MockConfig config = new MockConfig(ui, players, board);
        Game game = new Game(ui, config);
        game.start();

        assertEquals(3, ui.getPresentBoardArgs().size());
        assertEquals(6, ui.getDisplayArgs().size());
        assertEquals(Constants.DISPLAYED_STRING, ui.getDisplayArgs().get(0));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(1));
        assertEquals(Constants.DISPLAYED_STRING, ui.getDisplayArgs().get(2));
        assertEquals(Constants.PICK_A_SPOT_MSG, ui.getDisplayArgs().get(3));
        assertEquals(Constants.TIE_GAME_MSG, ui.getDisplayArgs().get(4));
        assertEquals(Constants.DISPLAYED_STRING, ui.getDisplayArgs().get(5));

        assertEquals(7, board.getNumTimesGetSpacesCalled());
        assertTrue(board.getHasPlayerWonArgs().get(0) instanceof ArrayList);
        assertEquals(player1Symbol, board.getHasPlayerWonArgs().get(1));
        assertTrue(board.getHasPlayerWonArgs().get(2) instanceof ArrayList);
        assertEquals(player2Symbol, board.getHasPlayerWonArgs().get(3));
        assertEquals(boardState, board.getGameTieArgs().get(0));
    }
}