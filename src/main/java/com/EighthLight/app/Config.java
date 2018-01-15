package com.EighthLight.app;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Config implements IConfig{

    private IUserInterface ui;
    private ArrayList<IPlayer> players = new ArrayList();
    private ArrayList<String> symbols = new ArrayList<>();
    private HashMap<String, ArrayList> gameMode = new HashMap<>();
    private HashMap<String, Object> playerOrder = new HashMap<>();
    private HashMap<String, String> aiDifficulties = new HashMap<>();
    private IBoard board;
    private String difficulty;

    public Config(IUserInterface ui) {
        this.ui = ui;
        setUpGame();
    }

    private void setUpGame() {
        setSymbols();
        createGameModes();
        createPlayerOrderOptions();
        createAiDifficultlyLevels();
        setGameMode();
        setBoard();
    }

    private void createGameModes() {
        IPlayer playerOne = new Player(symbols.get(0), ui);
        IPlayer playerTwo = new Player(symbols.get(1), ui);
        IPlayer ai = new Ai(symbols.get(1));

        gameMode.put("1", new ArrayList<>(Arrays.asList(playerOne, playerTwo)));
        gameMode.put("2", new ArrayList<>(Arrays.asList(playerOne, ai)));
    }

    private void createPlayerOrderOptions() {
        playerOrder.put("1", null);
        playerOrder.put("2", null);
    }

    private void createAiDifficultlyLevels() {
        aiDifficulties.put(Constants.EASY, "Easy");
        aiDifficulties.put(Constants.MEDIUM, "Medium");
        aiDifficulties.put(Constants.HARD, "Hard");
    }

    private void setGameMode() {
        ui.display(Constants.GAME_MODE_PROMPT);
        String userInput = ui.getInput();
        while (!(gameMode.containsKey(userInput))) {
            ui.display(Constants.INVALID_GAME_MODE_MSG);
            userInput = ui.getInput();
        }
        players.addAll(gameMode.get(userInput));
        if (userInput.equals(Constants.HUMAN_VS_COMPUTER)) {
            setPlayerOrder();
            setAiDifficulty();
        }
    }

    private void setPlayerOrder() {
        ui.display(Constants.PLAYER_ORDER_PROMPT);
        String userInput = ui.getInput();
        while (!(playerOrder.containsKey(userInput))) {
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
    }

    private void setSymbols() {
        ArrayList<String> prompts = new ArrayList(Arrays.asList("Pick a symbol for player 1", "Pick a symbol for player 2"));
        for (String prompt : prompts) {
            ui.display(prompt);
            String symbol = ui.getInput();
            while (symbols.contains(symbol)) {
                ui.display("Symbol already selected, please pick a different symbol.");
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

    public String getDifficulty() {
        return difficulty;
    }
}
