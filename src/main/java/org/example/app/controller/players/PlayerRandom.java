package org.example.app.controller.players;

import org.example.app.controller.GameController;
import org.example.app.model.GameModel;
import org.example.app.model.board.Cell;

import java.util.Random;

public class PlayerRandom extends AbstractPlayer{

    /**
     * @param gameController the game controller
     */
    public PlayerRandom(GameController gameController) {
        super(gameController);
    }

    /**
     * @return a random cell from the list of empty cells
     */
    @Override
    public Cell makeMove() {
        GameModel gameModel = gameController.getGameModel();
        if (gameModel.getEmptyCells().size() == 1) {
            return gameModel.getEmptyCells().get(0);
        }
        return gameModel.getEmptyCells().get(new Random().nextInt(gameModel.getEmptyCells().size() - 1));
    }
}
