package org.example.app.agents.decision;

import org.example.app.controller.players.AbstractPlayer;
import org.example.app.model.GameModel;
import org.example.app.model.board.Cell;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class State {

    AbstractPlayer[] players;
    GameModel game;
    public Stats stats;
    public Action action;
    public State(GameModel game, AbstractPlayer[] players, Action action) {
        this.game = game;
        this.stats = new Stats();
        this.players = players;
        this.action = action;
    }

    //TODO: Implementar la clase State
    public boolean isTerminal() {
        return game.isGameOver();
    }

    //TODO: Implementar la clase State
    public List<State> getPosibleStates() {
        List<State> possibleStates = new ArrayList<>();
        List<Cell> emptyCells = game.getEmptyCells(); // Suponiendo que game.getEmptyCells() devuelve todas las celdas vacías donde se puede mover.
        AbstractPlayer currentPlayer = game.getCurrentPlayer(players); // Asume que `players` está disponible en `State`.
        AbstractPlayer opponent = currentPlayer.equals(players[1])? players[0] : players[1];
        for (Cell cell : emptyCells) {
            // Necesitas una forma de clonar o replicar el estado de GameModel para el nuevo estado.
            GameModel clonedGame = game.clone(); // Asegúrate de implementar un método de clonación en GameModel.
            clonedGame.makeMove(cell, currentPlayer.getColor());
            possibleStates.add(new State(clonedGame, players, new Action(cell, currentPlayer.getColor())));

        }
        currentPlayer.updatePlayer();
        opponent.updatePlayer();

        return possibleStates;
    }


    //TODO: Implementar la clase State
    public double getReward() {

        return 0;
    }
}
