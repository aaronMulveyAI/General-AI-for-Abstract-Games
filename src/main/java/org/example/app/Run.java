package org.example.app;

import org.example.app.controller.GameController;
import org.example.app.controller.players.AbstractPlayer;
import org.example.app.controller.players.PlayerHuman;
import org.example.app.controller.players.PlayerMCTS;
import org.example.app.controller.players.PlayerRandom;
import org.example.app.model.GameModel;
import org.example.app.model.rules.RulesConstants;
import org.example.app.model.rules.Rules_CatchUp;
import org.example.app.model.rules.Rules_Odd;
import org.example.app.model.rules.Rules_Omega;
import org.example.app.view.GameView;

import java.awt.*;

public class Run {

    public static void main(String[] args) {
        String gameName = "CatchUp";
        int boardSize = 3;
        Color player1Color = Color.BLUE;
        Color player2Color = Color.RED;
        Color emptyColor = Color.WHITE;
        RulesConstants rulesConstants = new RulesConstants(gameName, boardSize, emptyColor, player1Color, player2Color);
        Rules_CatchUp catchUp = new Rules_CatchUp(rulesConstants);
        Rules_Omega omega = new Rules_Omega(rulesConstants);
        Rules_Odd odd = new Rules_Odd(rulesConstants);
        GameModel gameModel = new GameModel(omega);
        GameView gameView = new GameView(gameModel);
        AbstractPlayer[] players = new AbstractPlayer[2];
        GameController gameController = new GameController(gameModel, gameView);
        players[0] = new PlayerRandom(gameController);
        players[1] = new PlayerMCTS(gameController);
        gameController.setPlayers(players);
        gameController.startGame();
    }
}
