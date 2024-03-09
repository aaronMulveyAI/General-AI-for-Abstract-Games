package org.example.app.model.rules;

import org.example.app.model.board.*;
import org.example.app.controller.players.AbstractPlayer;


import java.awt.*;
import java.util.List;

/**
 * The implementation of this class should be the rules of the game
 */
public abstract class AbstractRules {
    protected final RulesConstants RULES_CONSTANTS;
    public AbstractRules(RulesConstants rulesConstants) {
        this.RULES_CONSTANTS = rulesConstants;
    }

    /** The implementation of this method should return the board size
     * @param board the board to get the empty cells
     * @return the empty cells of the given board.
     */
    public abstract List<Cell> getEmptyCells(Board board);


    /** The implementation of this method should return the game status
     *  and this method has to be pass by reference the board
     * @return the game status
     */
    public abstract boolean isGameOver(Board board);

    /** The implementation of this method should return the next player
     *  and this method has to be pass by reference the players and the board
     * @return the game name
     */
    public abstract AbstractPlayer updateCurrentPlayer(AbstractPlayer[] players, Board board);

    /** The implementation of this method should return the score of the player
     * @param player the player to calculate the score
     * @param board the board to calculate the score of the player
     * @return the game name
     */
    public abstract double calculateScore(AbstractPlayer player, Board board);


    /** Public access to get the board size
     * @return the board size
     */
    public int getBoardSize() {
        return RULES_CONSTANTS.size();
    }

    /** Public access to get the empty color
     * @return the empty color
     */
    public Color getEmptyColor() {
        return RULES_CONSTANTS.emptyColor();
    }

    /** Public access to get the first player color
     * @return the first player color
     */
    public Color getFirstPlayerColor() {
        return RULES_CONSTANTS.colorP1();
    }

    /** public access to get the second player color
     * @return the second player color
     */
    public Color getSecondPlayerColor() {
        return RULES_CONSTANTS.colorP2();
    }
}
