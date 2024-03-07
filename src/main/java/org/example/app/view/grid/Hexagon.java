package org.example.app.view.grid;

import org.example.app.model.board.Cell;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.*;
import static java.lang.Math.PI;

public class Hexagon extends JPanel {
    final double[] offsetCoordinates;
    private final int radius;
    private Cell cell;
    protected Polygon hexagon;

    /**
     * Constructor for Hexagon
     * @param cell - Cell to be set
     * @param radius_offset_X_Y - radius, x and y offset
     */
    public Hexagon(Cell cell, int[] radius_offset_X_Y) {
        this.cell = cell;
        this.radius = radius_offset_X_Y[0];
        this.offsetCoordinates = createOffsetCoordinates(radius_offset_X_Y);
        this.hexagon = createHexagon();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.hexagon = createHexagon();
        g.setColor(this.cell.getColor());
        g.fillPolygon(hexagon);
        g.setColor(Color.BLACK);
        g.drawPolygon(hexagon);
    }

    /**
     * Checks if the hexagon contains the given x and y coordinates
     * @param x - x coordinate
     * @param y - y coordinate
     * @return boolean
     */
    @Override
    public boolean contains(int x, int y) {

        return hexagon.contains(x, y);
    }

    /**
     * Creates the offset coordinates for the hexagon
     * @param radius_offset_X_Y - radius, x and y offset
     * @return double array of offset coordinates
     */
    private double[] createOffsetCoordinates(int[] radius_offset_X_Y) {
        double[] offset = new double[2];
        int[] QRS = cell.getCoordinates().QRS;
        int radius = radius_offset_X_Y[0];
        int x = radius_offset_X_Y[1];
        int y = radius_offset_X_Y[2];

        offset[0] = x + radius * (3.0 / 2.0 * QRS[0]);
        offset[1] = y + radius * (sqrt(3) * QRS[1] + sqrt(3) / 2.0 * QRS[0]);

        return offset;
    }

    /**
     * Creates the hexagon
     * @return Polygon of the hexagon
     */
    private Polygon createHexagon() {

        final int vertexNumber = 6;
        int[] xPoints = new int[vertexNumber];
        int[] yPoints = new int[vertexNumber];
        // Flat Hexagon
        for (int i = 0; i < vertexNumber; i++) {
            xPoints[i] = (int) (offsetCoordinates[0] + radius * cos(i * 2 * PI / vertexNumber));
            yPoints[i] = (int) (offsetCoordinates[1] + radius * sin(i * 2 * PI / vertexNumber));
        }

        return new Polygon(xPoints, yPoints, vertexNumber);

    }

    /**
     * Sets the cell to the hexagon
     * @param cell - Cell to be set
     */
    public void setCell(Cell cell) {
        this.cell = cell;
    }

    /**
     * Gets the cell from the hexagon
     * @return Cell
     */
    public Cell getCell() {
        return cell;
    }

}
