package org.example.app.model.rules;

import org.example.app.controller.players.AbstractPlayer;
import org.example.app.model.board.Board;
import org.example.app.model.board.Cell;

import java.awt.*;
import java.util.List;

public class Rules_Omega extends AbstractRules{

    public Rules_Omega(RulesConstants rulesConstants) {
        super(rulesConstants);
    }

    @Override
    public List<Cell> getEmptyCells(Board board) {
        return board.getCellsByColor(this.getEmptyColor());
    }

    @Override
    public boolean isGameOver(Board board) {
        return this.getEmptyCells(board).size() < 4;
    }

    @Override
    public AbstractPlayer updateCurrentPlayer(AbstractPlayer[] players, Board board) {
        int colorIndex =  (board.getEmptyCells().size() + 1) % (players.length * 2);
        int playerIndex = (board.getEmptyCells().size() + 1) % (players.length * 2) / 2;
        Color[] colors = {
                this.getFirstPlayerColor(),
                this.getSecondPlayerColor(),
                this.getFirstPlayerColor(),
                this.getSecondPlayerColor()};

        return switch (playerIndex) {
            case 0 -> {
                players[0].setColor(colors[colorIndex]);
                yield players[playerIndex];
            }
            case 1 -> {
                players[1].setColor(colors[colorIndex]);
                yield players[playerIndex];
            }
            default -> players[playerIndex];
        };
    }

    @Override
    public double calculateScore(AbstractPlayer player, Board board) {
        List<Integer> groupSizes = board.getGroups(player);
        return  groupSizes.stream().reduce(1, (a, b) -> a * b);
    }
}
