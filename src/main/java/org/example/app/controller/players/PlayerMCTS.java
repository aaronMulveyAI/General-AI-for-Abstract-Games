package org.example.app.controller.players;

import org.example.app.agents.decision.Action;
import org.example.app.agents.decision.State;
import org.example.app.agents.mcts.MCTS;
import org.example.app.controller.GameController;
import org.example.app.model.board.Cell;

public class PlayerMCTS extends AbstractPlayer {
    public PlayerMCTS(GameController gameController) {
        super(gameController);
    }

    @Override
    public Cell makeMove() {
        State state = new State(gameController.getGameModel(), gameController.getPlayers(), null);
        MCTS mcts = new MCTS();
        Action action = mcts.search(state);
        return action.getCell().get(0);
    }
}
