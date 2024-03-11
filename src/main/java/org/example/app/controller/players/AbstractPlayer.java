package org.example.app.controller.players;

import org.example.app.controller.GameController;
import org.example.app.model.board.Cell;
import org.example.app.model.rules.AbstractRules;

import java.awt.*;

public abstract class AbstractPlayer implements Cloneable {
    private double score;
    private Color color;
    private final int PLAYER_NUMBER;
    private static int numberOfPlayers = 0;
    protected GameController gameController;
    public AbstractPlayer(GameController gameController) {
        this.PLAYER_NUMBER = numberOfPlayers++;
        this.score = 0;
        this.gameController = gameController;
    }

    /**
     * @return the score
     */
    public abstract Cell makeMove();

    public void updatePlayer(){
        AbstractRules rules = gameController.getGameModel().getRULES();
        this.score = rules.calculateScore(this, gameController.getGameModel().getBOARD());
    }

    /**
     * @return the score
     */
    public double getScore() {
        return score;
    }


    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    public int getPlayerNumber() {
        return PLAYER_NUMBER;
    }

    @Override
    public AbstractPlayer clone() {
        try {
            AbstractPlayer cloned = (AbstractPlayer) super.clone();
            // For immutable and primitive fields, the default cloning is fine.
            // If there were mutable objects that needed deep cloning, you would clone them here.
            // Note: GameController, if mutable, might need special handling.
            return cloned;
        } catch (CloneNotSupportedException e) {
            // This shouldn't happen since we're Cloneable
            throw new AssertionError(e);
        }
    }
}
