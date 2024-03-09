package org.example.app.model;

import org.example.app.model.board.*;
import org.example.app.model.rules.AbstractRules;
import org.example.app.controller.players.AbstractPlayer;


import java.awt.*;
import java.util.List;

/**
 * This class is the model of the game, it contains the board and the grid
 */
public class GameModel implements Cloneable {


    /**
     * The game board
     */
    private Board BOARD;

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
        return BOARD.updateCurrentPlayer(players, BOARD);
    }

    /**
     * @return a list of empty cells in the board
     */
    public List<Cell> getEmptyCells() {
        return BOARD.getEmptyCells();
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

    @Override
    public GameModel clone() {
        try {
            GameModel cloned = (GameModel) super.clone();
            // Realizar copias profundas de los objetos mutables para asegurar la clonación completa.
            cloned.BOARD = this.BOARD.clone(); // Asume que Board también es Cloneable y tiene un método clone() adecuado.
            // Si tienes otros objetos mutables como parte del estado del juego, asegúrate de clonarlos aquí.
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // No debería ocurrir, maneja adecuadamente según tu caso.
        }
    }

}
