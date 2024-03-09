package org.example.app.model.rules;

import org.example.app.model.board.Board;
import org.example.app.model.board.Cell;
import org.example.app.controller.players.AbstractPlayer;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the rules for the game CatchUp
 */
public class Rules_CatchUp extends AbstractRules{

    public Rules_CatchUp(RulesConstants gameConstants) {
        super(gameConstants);
    }

    /**
     * Returns a list of empty cells on the board
     * @param board the game board
     * @return a list of empty cells
     */
    @Override
    public List<Cell> getEmptyCells(Board board) {
        List<Cell> emptyCells = new ArrayList<>();
        for (Cell cell : board.getBoard()) {
            if (cell.getColor() == this.getEmptyColor()) {
                emptyCells.add(cell);
            }
        }
        return emptyCells;
    }

    /**
     * Returns true if the game is over, false otherwise
     * @param board the game board
     * @return true if the game is over, false otherwise
     */
    @Override
    public boolean isGameOver(Board board) {
        return board.getEmptyCells().isEmpty();
    }

    /**
     * Returns the next player
     * @param players the players
     * @param board the game board
     * @return the next player
     */
    @Override
    public AbstractPlayer updateCurrentPlayer(AbstractPlayer[] players, Board board) {
        int playerIndex = (board.getEmptyCells().size() + 1) % players.length;
        return switch (playerIndex) {
            case 0 -> {
                players[0].setColor(this.getFirstPlayerColor());
                yield players[playerIndex];
            }
            case 1 -> {
                players[1].setColor(this.getSecondPlayerColor());
                yield players[playerIndex];
            }
            default -> players[playerIndex];
        };
    }

    /**
     * Returns the score of the player
     * @param player the player to calculate the score
     * @param board the game board
     * @return the score of the player
     */
    @Override
    public double calculateScore(AbstractPlayer player, Board board) {
        List<Integer> groupSizes = board.getGroups(player);
        return groupSizes.stream().max(Integer::compareTo).orElse(0);
    }

}
