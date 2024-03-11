package org.example.app.model.board;

import org.example.app.controller.players.AbstractPlayer;
import org.example.app.model.rules.AbstractRules;

import java.awt.*;
import java.util.*;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * The board class
 */
public class Board implements Cloneable {
    private List<Cell> board;
    private final AbstractRules RULES;
    public Board(AbstractRules rules) {
        this.RULES = rules;
        this.createBoard();
        this.connectCells();
    }

    /**
     * @param cell the cell to make the move
     * @param color the color to set the cell
     */
    public void makeMove(Cell cell, Color color) {
        for (Cell c : board) {
            for (Cell cNeighbour : c.connectedCells) {
                if(cNeighbour.equals(cell)) {
                    cNeighbour.setColor(color);
                }
            }
            if(c.equals(cell)) {
                c.setColor(color);
            }
        }
    }


    /**
     * @return the empty cells
     */
    public List<Cell> getEmptyCells() {
        return RULES.getEmptyCells(this);
    }


    /**
     * @return the game status
     */
    public boolean isGameOver() {
        return RULES.isGameOver(this);
    }

    /**
     * @return the board
     */
    public List<Cell> getBoard() {
        return board;
    }

    /**
     * creates a hexagonal board // TODO: make it generic with BoardUtils.java
     */
    private void createBoard() {
        this.board = new ArrayList<>();
        int boardSize = RULES.getBoardSize();
        for (int q = - boardSize; q <= boardSize; q++) {
            int r_max = max(-boardSize, - q - boardSize);
            int r_min = min( boardSize, - q + boardSize);
            for (int r = r_max; r <= r_min; r++) {
                Cell cell = new Cell(new int[]{q, r, - q - r});
                cell.setColor(RULES.getEmptyColor());
                this.board.add(cell);
            }
        }
    }

    /**
     * connects the cells in an hexagonal way // TODO: make it generic with BoardUtils.java
     */
    private void connectCells() {
        for (int i = 0; i < board.size(); i++) {
            Cell cell_A = board.get(i);
            for (int j = 0; j < board.size(); j++) {
                if(i != j){
                    Cell cell_B = board.get(j);
                    int difference_Q = Math.abs(cell_A.getQRS()[0] - cell_B.getQRS()[0]);
                    int difference_R = Math.abs(cell_A.getQRS()[1] - cell_B.getQRS()[1]);
                    int difference_S = Math.abs(cell_A.getQRS()[2] - cell_B.getQRS()[2]);
                    int total_difference = difference_Q + difference_R + difference_S;

                    if (total_difference == 2) { // they are neighbors
                        if (!cell_A.connectedCells.contains(cell_B)) cell_A.connectedCells.add(cell_B);
                        if (!cell_B.connectedCells.contains(cell_A)) cell_B.connectedCells.add(cell_A);
                    }
                }
            }
        }
    }

    /**
     * @param player the player to count groups size
     * @return list with the size of the groups
     */
    public List<Integer> getGroups(AbstractPlayer player) {

        Color color = player.getPlayerNumber() == 0 ? RULES.getFirstPlayerColor() : RULES.getSecondPlayerColor();
        this.update();
        List<Integer> clusterSizes = new ArrayList<>();

        for (Cell cell : board) {
            Color cellColor = cell.getColor();
            if (cellColor.equals(color) && !cell.isVisited()) {
                int groupSize = numberOfPiecesConnectedToCell(color, cell);
                clusterSizes.add(groupSize);
            }
        }
        this.update();
        return clusterSizes;
    }

    /**
     * @param color the color to count the groups
     * @param startingCell the starting cell
     * @return list with the size of the groups
     */
    private int numberOfPiecesConnectedToCell(Color color, Cell startingCell) {
        if (!startingCell.getColor().equals(color)) return 0;

        int count = 1;
        LinkedList<Cell> queue = new LinkedList<>();
        startingCell.setVisited(true);
        queue.add(startingCell);

        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            for (Cell neighbor : current.connectedCells) {
                if (!neighbor.isVisited() && neighbor.getColor().equals(color)) {
                    neighbor.setVisited(true);
                    queue.add(neighbor);
                    count++;
                }
            }
        }

        return count;
    }

    public List<Cell> getCellsByColor(Color color) {
        List<Cell> cellsByColor = new ArrayList<>();
        for (Cell cell : this.board) {
            if (cell.getColor().equals(color)) {
                cellsByColor.add(cell);
            }
        }
        return cellsByColor;
    }

    public AbstractPlayer updateCurrentPlayer(AbstractPlayer[] players, Board board) {
        return RULES.updateCurrentPlayer(players, board);
    }
    /**
     * updates the cells to non visited
     */
    public void update(){
        for (Cell cell : board) {
            cell.setVisited(false);
        }
    }

    @Override
    public Board clone() {
        try {
            Board clonedBoard = (Board) super.clone();
            clonedBoard.board = new ArrayList<>();
            for (Cell cell : this.board) {
                Cell cloned = cell.clone();
                for (Cell cellNeighbour : cell.connectedCells) {
                    cloned.connectedCells.add(cellNeighbour.clone());
                }
                clonedBoard.board.add(cloned);
            }

            //clonedBoard.reconnectCells();

            return clonedBoard;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }

    // Método auxiliar para reconectar las celdas clonadas basado en la estructura original
    private void reconnectCells() {
        for (int i = 0; i < this.board.size(); i++) {
            Cell cellA = this.board.get(i);
            for (Cell cellB : this.board) {
                if (!cellA.equals(cellB) && cellA.connectedCells.contains(cellB)) { // areNeighbors es un método hipotético que determina si dos celdas deben estar conectadas
                    cellA.connectedCells.add(cellB);
                }
            }
        }
    }

}
