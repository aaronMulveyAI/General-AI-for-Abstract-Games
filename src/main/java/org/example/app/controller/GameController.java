package org.example.app.controller;


import org.example.app.model.GameModel;
import org.example.app.model.board.Cell;
import org.example.app.view.GameView;
import org.example.app.controller.players.AbstractPlayer;

import java.awt.*;


public class GameController {
    private final GameView GAME_VIEW;
    private final GameModel GAME_MODEL;
    private AbstractPlayer[] players;
    public GameController(GameModel gameModel, GameView gameView) {
        this.GAME_MODEL = gameModel;
        this.GAME_VIEW = gameView;

    }
    public void setPlayers(AbstractPlayer[] players) {
        this.players = players;
    }

    /**
     * Start the game
     */
    public void startGame() {

        AbstractPlayer currentPlayer = GAME_MODEL.getCurrentPlayer(players);
        AbstractPlayer otherPlayer = currentPlayer.equals(players[0]) ? players[1] : players[0];

        while (!GAME_MODEL.isGameOver()) {
            Cell cell = currentPlayer.makeMove();
            GAME_MODEL.makeMove(cell, currentPlayer.getColor());
            currentPlayer.updatePlayer();
            otherPlayer.updatePlayer();
            GAME_VIEW.updateScores(currentPlayer.getScore(), otherPlayer.getScore());
            GAME_VIEW.update();
            currentPlayer = GAME_MODEL.getCurrentPlayer(players);
            otherPlayer = currentPlayer.equals(players[0]) ? players[1] : players[0];
        }
    }

    /**
     * @return the game model
     */
    public GameModel getGameModel() {
        return GAME_MODEL;
    }

    /**
     * @return the game view
     */
    public GameView getGameView() {
        return GAME_VIEW;
    }

    public AbstractPlayer[] getPlayers() {
        return players;
    }
}
