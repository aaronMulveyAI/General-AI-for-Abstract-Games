package org.example.app.model.rules;

import org.example.app.controller.players.AbstractPlayer;
import org.example.app.model.board.Board;
import org.example.app.model.board.Cell;


import java.util.List;

public class Rules_Odd extends AbstractRules{
    public Rules_Odd(RulesConstants rulesConstants) {
        super(rulesConstants);
    }

    @Override
    public List<Cell> getEmptyCells(Board board) {
        return board.getCellsByColor(this.getEmptyColor());
    }


    @Override
    public boolean isGameOver(Board board) {
        return board.getEmptyCells().isEmpty();
    }

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

    @Override
    public double calculateScore(AbstractPlayer player, Board board) {
        int counter = 0;
        for (Integer groupSize : board.getGroups(player)){
            if (groupSize > 4){
                counter++;
            }
        }

        boolean odd = counter % 2 != 0;
        boolean isPlayer1 = player.getColor().equals(this.getFirstPlayerColor());

        if (odd){
            return isPlayer1 ? 1 : 0;
        }else {
            return isPlayer1 ? 0 : 1;
        }

    }
}
