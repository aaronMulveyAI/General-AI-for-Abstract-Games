package org.example.app.controller;


import org.example.app.controller.players.PlayerRandom;
import org.example.app.model.GameModel;
import org.example.app.model.board.Cell;
import org.example.app.model.rules.RulesConstants;
import org.example.app.model.rules.Rules_CatchUp;
import org.example.app.view.GameView;
import org.example.app.controller.players.AbstractPlayer;

import java.awt.*;


public class GameController {
    GameView gameView;
    GameModel gameModel;
    AbstractPlayer[] players;
    public GameController(GameModel gameModel, GameView gameView) {
        this.gameModel = gameModel;
        this.gameView = gameView;

    }
    public void setPlayers(AbstractPlayer[] players) {
        this.players = players;
    }

    public void startGame() {
        while (!gameModel.isGameOver()) {
            AbstractPlayer currentPlayer = gameModel.getCurrentPlayer(players);
            AbstractPlayer otherPlayer = currentPlayer.equals(players[0]) ? players[1] : players[0];
            Cell cell = currentPlayer.makeMove();
            gameModel.makeMove(cell, currentPlayer.getColor());
            currentPlayer.updatePlayer();
            gameView.updateScores(currentPlayer.getScore(), otherPlayer.getScore());
            gameView.update();
        }
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public GameView getGameView() {
        return gameView;
    }
}
