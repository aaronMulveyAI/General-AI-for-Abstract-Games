package org.example.app.model;

import org.example.app.model.board.*;
import org.example.app.model.rules.AbstractRules;
import org.example.app.view.GameView;
import org.example.app.controller.players.AbstractPlayer;


import java.awt.*;
import java.util.List;

/**
 * This class is the model of the game, it contains the board and the grid
 */
public class GameModel {

    /**
     * The game board
     */
    private final Board BOARD;

    /**
     * The game rules
     */
    private final AbstractRules RULES;

    /**
     * Constructor
     * @param rules the game board
     */
    public GameModel(AbstractRules rules) {
        this.BOARD = new Board(rules);
        this.RULES = rules;
    }


    /**
     * @return the current player
     */
    public AbstractPlayer getCurrentPlayer(AbstractPlayer[] players) {
        return RULES.updateCurrentPlayer(players, BOARD);
    }

    /**
     * @return a list of empty cells in the board
     */
    public List<Cell> getEmptyCells() {
        return this.BOARD.getEmptyCells();
    }

    /**
     * @param cell  the cell to make the move
     * @param color the color to set the cell
     */
    public void makeMove(Cell cell, Color color) {
        BOARD.makeMove(cell, color);
    }

    /**
     * @return the game status
     */
    public boolean isGameOver() {
        return BOARD.isGameOver();
    }

    public Board getBOARD() {
        return BOARD;
    }

    public AbstractRules getRULES() {
        return RULES;
    }
}
