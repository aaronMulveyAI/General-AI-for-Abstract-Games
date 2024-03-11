package org.example.app.agents.decision;

import org.example.app.controller.players.AbstractPlayer;
import org.example.app.model.GameModel;
import org.example.app.model.board.Cell;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class State {

    public AbstractPlayer[] players;

    public GameModel game;
    public Stats stats;
    public Action action;
    public State(GameModel game, AbstractPlayer[] players, Action action) {
        this.game = game;
        this.stats = new Stats();
        this.players = players;
        this.action = action;
    }

    public boolean isTerminal() {
        return game.isGameOver();
    }

    public List<State> getPosibleStates() {
        List<State> possibleStates = new ArrayList<>();
        List<Cell> emptyCells = game.getEmptyCells(); // Suponiendo que game.getEmptyCells() devuelve todas las celdas vacías donde se puede mover.
        AbstractPlayer currentPlayer = game.getCurrentPlayer(players); // Asume que `players` está disponible en `State`.

        for (Cell cell : emptyCells) {
            // Necesitas una forma de clonar o replicar el estado de GameModel para el nuevo estado.
            GameModel clonedGame = game.clone(); // Asegúrate de implementar un método de clonación en GameModel.
            clonedGame.makeMove(cell, currentPlayer.getColor());
            possibleStates.add(new State(clonedGame, players, new Action(cell, currentPlayer.getColor())));

        }

        return possibleStates;
    }

    public List<State> getRandomPosibleStates(int n) {
        List<State> possibleStates = new ArrayList<>();
        List<Cell> emptyCells = game.getEmptyCells(); // Suponiendo que game.getEmptyCells() devuelve todas las celdas vacías donde se puede mover.
        AbstractPlayer currentPlayer = game.getCurrentPlayer(players); // Asume que `players` está disponible en `State`.

        for (int i = 0; i < n; i++) {
            int cellIndex = (int) (Math.random() * emptyCells.size()); // TODO para que
            Cell cell = emptyCells.get(cellIndex);
            GameModel clonedGame = game.clone(); // Asegúrate de implementar un método de clonación en GameModel.
            clonedGame.makeMove(emptyCells.get(cellIndex), currentPlayer.getColor());
            possibleStates.add(new State(clonedGame, players, new Action(cell, currentPlayer.getColor())));
        }

        return possibleStates;
    }

    public double getReward() {
        double scoreM = game.getRULES().calculateScore(players[0], game.getBOARD());
        double score = game.getRULES().calculateScore(players[1], game.getBOARD());
        return scoreM > score ? 1 : 0;
    }
}
