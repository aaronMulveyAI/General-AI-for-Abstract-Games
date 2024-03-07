package org.example.app.view.grid;

import org.example.app.model.GameModel;
import org.example.app.model.board.Cell;
import org.example.app.model.board.Coordinates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class Grid extends JPanel {
    private final GameModel GAME_MODEL;
    protected final int[] OFFSET_RADIUS_X_Y;
    private List<Hexagon> hexagonalGrid;

    /**
     * Constructor for the Grid
     * @param game - GameModel to be set
     * @param radius_offset_X_Y - radius, x and y offset
     */
    public Grid(GameModel game, int[] radius_offset_X_Y) {
        this.GAME_MODEL = game;
        this.OFFSET_RADIUS_X_Y = radius_offset_X_Y;
        hexagonalGrid = initHexagonalGrid(radius_offset_X_Y);
    }

    /**
     * Initializes the hexagonal grid
     * @param radius_offset_X_Y - radius, x and y offset
     * @return List of hexagons
     */
    private List<Hexagon> initHexagonalGrid(int[] radius_offset_X_Y) {
        this.hexagonalGrid = new ArrayList<>();
        for (int i = 0; i < GAME_MODEL.getBOARD().getBoard().size(); i++) {
            Cell cell = GAME_MODEL.getBOARD().getBoard().get(i);
            hexagonalGrid.add(new Hexagon(cell, radius_offset_X_Y));
        }
        return hexagonalGrid;
    }

    /**
     * Sets the cell click listener to the grid
     * @param cellClickListener - BiConsumer of Cell and Coordinates to be set
     */
    public void setCellClickListener(BiConsumer<Cell, Coordinates> cellClickListener) {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Cell cell = getCellFromPosition(e.getX(), e.getY());

                boolean nullClickListener = cellClickListener == null;
                assert cell != null;
                boolean notEmptyCell = cell.getColor() == GAME_MODEL.getEmptyCells().get(0).getColor();

                if (nullClickListener || notEmptyCell) {
                    assert cellClickListener != null;
                    cellClickListener.accept(cell, cell.getCoordinates());
                }
            }
        };


        addMouseListener(mouseAdapter);
    }

    /**
     * Paints the grid
     * @param g - Graphics to be painted
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.drawGrid(g);
    }

    /**
     * Draws the grid
     * @param g - Graphics to be drawn
     */
    private void drawGrid(Graphics g) {
        super.paintComponent(g);
        List<Cell> cells = GAME_MODEL.getBOARD().getBoard();
        for (Hexagon hexagon : hexagonalGrid) {
            hexagon.setCell(cells.get(hexagonalGrid.indexOf(hexagon)));
            hexagon.paintComponent(g);
        }
    }

    /**
     * Gets the cell from the position
     * @param x - x position
     * @param y - y position
     * @return Cell from the position
     */
    public Cell getCellFromPosition(int x, int y){
        for (Hexagon hexagon : hexagonalGrid) {
            if (hexagon.contains(x, y)) {
                return hexagon.getCell();
            }
        }
        return null;
    }

}
