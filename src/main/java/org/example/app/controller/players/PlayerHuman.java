package org.example.app.controller.players;

import org.example.app.controller.GameController;
import org.example.app.model.board.Cell;
import org.example.app.view.GameView;

public class PlayerHuman extends AbstractPlayer{
    private final Object lock = new Object();
    private volatile Cell selectedCell;

    /**
     * @param controller the game controller
     */
    public PlayerHuman(GameController controller) {
        super(controller);
        GameView gameView = this.gameController.getGameView();
        gameView.setCellClickListener((cell, coordinates) -> {
            synchronized (lock) {
                selectedCell = cell;
                lock.notifyAll();
            }
        });
    }

    /**
     * @return the cell selected by the human player
     */
    @Override
    public Cell makeMove() {
        synchronized (lock) {
            while (selectedCell == null) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            Cell cell = selectedCell;
            selectedCell = null;
            return cell;
        }
    }
}
