package org.example.app.model.rules;

import org.example.app.controller.players.AbstractPlayer;
import org.example.app.model.board.Board;
import org.example.app.model.board.Cell;


import java.awt.*;
import java.util.List;

public class Rules_Omega extends AbstractRules{

    public Rules_Omega(RulesConstants rulesConstants) {
        super(rulesConstants);
    }

    @Override
    public List<Cell> getEmptyCells(Board board) {
        return board.getCellsByColor(this.getEmptyColor());
    }

    @Override
    public boolean isGameOver(Board board) {
        return this.getEmptyCells(board).size() < 4;
    }

    @Override
    public AbstractPlayer updateCurrentPlayer(AbstractPlayer[] players, Board board) {
        // Obtener el tamaño total del tablero.
        int size = board.getBoard().size();
        // Calcular las casillas llenas restando las vacías del total.
        int filledCells = size - board.getEmptyCells().size();
        // players.length * 2 porque cada jugador pone dos colores en su turno.
        int totalSteps = players.length * 2;
        // Calculamos el índice del paso basado en las casillas llenas para mantener el orden de juego.
        int stepIndex = filledCells % totalSteps;

        // Calculamos el índice del jugador: es 0 para los dos primeros pasos, 1 para los siguientes dos, etc.
        int playerIndex = stepIndex / 2;
        // Los colores se alternan cada paso, por lo que usamos stepIndex para determinar el color actual.
        Color[] colors = {
                this.getFirstPlayerColor(), // j1 color1
                this.getSecondPlayerColor(), // j1 color2
                this.getFirstPlayerColor(), // j2 color1
                this.getSecondPlayerColor()}; // j2 color2

        // Asignamos el color al jugador actual basado en el índice calculado.
        players[playerIndex].setColor(colors[stepIndex]);
        // Devolvemos el jugador actual con su color asignado.
        return players[playerIndex];
    }

    @Override
    public double calculateScore(AbstractPlayer player, Board board) {
        List<Integer> groupSizes = board.getGroups(player);
        int score = 1;
        for (int i = 0; i < groupSizes.size(); i++) {
            score *= groupSizes.get(i);
        }
        //return  groupSizes.stream().reduce(1, (a, b) -> a * b);
        return score;
    }
}
