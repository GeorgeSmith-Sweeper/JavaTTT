package com.EighthLight.app;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class Config implements IConfig{

    private IUserInterface ui;
    private ArrayList<IPlayer> players = new ArrayList();
    private ArrayList<String> symbols = new ArrayList<>();
    private Board board;

    public Config(IUserInterface ui) {
        this.ui = ui;
        setUpGame();
    }

    private void setUpGame() {
        setSymbols();
        setPlayers();
        setBoard();
    }

    private void setPlayers() {
        IPlayer playerOne = new Player(symbols.get(0), ui);
        IPlayer playerTwo = new Player(symbols.get(1), ui);
        IPlayer aiOne = new Ai(symbols.get(0));
        IPlayer aiTwo = new Ai(symbols.get(1));

        HashMap<String, ArrayList> playerCombos = new HashMap();
        playerCombos.put("1", new ArrayList<>(Arrays.asList(playerOne, playerTwo)));
        playerCombos.put("2", new ArrayList<>(Arrays.asList(playerOne, aiTwo)));
        playerCombos.put("3", new ArrayList<>(Arrays.asList(aiOne, playerTwo)));
        playerCombos.put("4", new ArrayList<>(Arrays.asList(aiOne, aiTwo)));

        ui.display(Constants.GAME_MODE_PROMPT);
        String userInput = ui.getInput();
        while (!(playerCombos.containsKey(userInput))) {
            ui.display(Constants.INVALID_GAME_MODE_MSG);
            userInput = ui.getInput();
        }
        players.addAll(playerCombos.get(userInput));
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
                board.createBoard();
                board.setWinningCombos();
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

    public Board getBoard() {
        return board;
    }
}
