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
        IPlayer playerOne;
        IPlayer playerTwo;
        HashMap playerCombos = new HashMap();

        playerCombos.put("1", new ArrayList<>(Arrays.asList(
                playerOne = new Player(symbols.get(0), ui),
                playerTwo = new Player(symbols.get(1), ui))
        ));

        playerCombos.put("2", new ArrayList<>(Arrays.asList(
                playerOne = new Player(symbols.get(0), ui),
                playerTwo = new Ai(symbols.get(1))
        )));

        playerCombos.put("3", new ArrayList<>(Arrays.asList(
                playerOne = new Ai(symbols.get(0)),
                playerTwo = new Player(symbols.get(1), ui))
        ));

        playerCombos.put("4", new ArrayList<>(Arrays.asList(
                playerOne = new Ai(symbols.get(0)),
                playerTwo = new Ai(symbols.get(1))
        )));

        String userInput = ui.getInput();
        while (!(playerCombos.containsKey(userInput))) {
            ui.display(Constants.INVALID_GAME_MODE_MSG);
            userInput = ui.getInput();
        }

        players.addAll((Collection<? extends IPlayer>) playerCombos.get(userInput));
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
