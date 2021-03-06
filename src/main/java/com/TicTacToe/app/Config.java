package com.TicTacToe.app;

import com.TicTacToe.app.Difficulties.EasyDifficulty;
import com.TicTacToe.app.Difficulties.HardDifficulty;
import com.TicTacToe.app.Difficulties.MediumDifficulty;
import com.TicTacToe.app.Interfaces.*;
import com.TicTacToe.app.Ui.IUserInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Config implements IConfig {

    private IUserInterface ui;
    private ArrayList<IPlayer> players = new ArrayList<>();
    private ArrayList<String> symbols = new ArrayList<>();
    private HashMap<String, ArrayList> gameModes = new HashMap<>();
    private ArrayList<String> playerOrder = new ArrayList<>(Arrays.asList(Constants.HUMAN_GOES_FIRST, Constants.COMPUTER_GOES_FIRST));
    private ArrayList<String> prompts = new ArrayList<>(Arrays.asList(Constants.PLAYER_1_SYMBOL_PROMPT, Constants.PLAYER_2_SYMBOL_PROMPT));
    private HashMap<String, IStrategy> aiDifficulties = new HashMap<>();
    private IBoard board;

    private IStrategy difficulty;

    public Config(IUserInterface ui) {
        this.ui = ui;
        setUpGame();
    }

    private void setUpGame() {
        setBoard();
        setSymbols();
        createGameModes();
        createAiDifficultlyLevels();
        setGameMode();
    }

    private void createGameModes() {
        IPlayer player1 = new Player(symbols.get(0), ui);
        IPlayer player2 = new Player(symbols.get(1), ui);

        gameModes.put(Constants.HUMAN_VS_HUMAN, new ArrayList<>(Arrays.asList(player1, player2)));
        gameModes.put(Constants.HUMAN_VS_COMPUTER, new ArrayList<>(Arrays.asList(player1)));
    }

    private void createAiDifficultlyLevels() {
        aiDifficulties.put(Constants.EASY, new EasyDifficulty(symbols.get(1)));
        aiDifficulties.put(Constants.MEDIUM, new MediumDifficulty(symbols.get(1)));
        aiDifficulties.put(Constants.HARD, new HardDifficulty(symbols.get(1), symbols.get(0)));
    }

    private void setGameMode() {
        ui.display(Constants.GAME_MODE_PROMPT);
        String userInput = ui.getInput();

        while (!(gameModes.containsKey(userInput))) {
            ui.display(Constants.INVALID_GAME_MODE_MSG);
            userInput = ui.getInput();
        }

        if (userInput.equals(Constants.HUMAN_VS_COMPUTER)) {
            players.addAll(gameModes.get(userInput));
            setAiDifficulty();
            setPlayerOrder();
        }
        players.addAll(gameModes.get(userInput));
    }

    private void setPlayerOrder() {
        ui.display(Constants.PLAYER_ORDER_PROMPT);
        String userInput = ui.getInput();
        while (!(playerOrder.contains(userInput))) {
            ui.display(Constants.INVALID_CHOICE_PROMPT);
            userInput = ui.getInput();
        }
        if (userInput.equals(Constants.COMPUTER_GOES_FIRST)) {
            Collections.reverse(players);
        }
    }

    private void setAiDifficulty() {
        ui.display(Constants.AI_DIFFICULTY_PROMPT);
        String userInput = ui.getInput();
        while (!(aiDifficulties.containsKey(userInput))) {
            ui.display(Constants.INVALID_CHOICE_PROMPT);
            userInput = ui.getInput();
        }
        difficulty = aiDifficulties.get(userInput);
        IPlayer ai = new Ai(symbols.get(1), difficulty);
        players.add(ai);
    }

    private void setSymbols() {
        for (String prompt : prompts) {
            ui.display(prompt);
            String symbol = ui.getInput();
            while (symbols.contains(symbol)) {
                ui.display(Constants.DUPLICATE_SYMBOL_ERROR_PROMPT);
                symbol = ui.getInput();
            }
            symbols.add(symbol);
        }
    }

    private void setBoard() {
        ui.display(Constants.BOARD_SIZE_PROMPT);
        String boardSize = ui.getInput();
        boolean invalidBoardSize = true;

        while(invalidBoardSize) {
            try {
                int convertedBoardSize = Integer.parseInt(boardSize);
                board = new Board(convertedBoardSize);
                invalidBoardSize = false;
            } catch (NumberFormatException e) {
                ui.display(Constants.INVALID_BOARD_SIZE_MSG);
                boardSize = ui.getInput();
            }
        }
    }

    public ArrayList<IPlayer> getPlayers() {
        return players;
    }

    public ArrayList getSymbols() {
        return symbols;
    }

    public IBoard getBoard() {
        return board;
    }

    public IStrategy getDifficulty() {
        return difficulty;
    }
}
