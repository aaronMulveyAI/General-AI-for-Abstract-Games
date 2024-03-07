package org.example.app.controller.players;

import org.example.app.controller.GameController;
import org.example.app.model.GameModel;
import org.example.app.model.board.Cell;
import org.example.app.model.rules.AbstractRules;

import java.awt.*;

public abstract class AbstractPlayer {
    private double score;
    private Color color;
    protected GameController gameController;
    public AbstractPlayer(GameController gameController) {
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

}
