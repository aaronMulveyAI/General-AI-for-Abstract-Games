package org.example.app.model.board;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Cell class is a class that represents a single cell in the board.
 * It has a unique identifier (QRS) and a color.
 * The board class will set the color and connect the cells.
 */
public class Cell implements Cloneable{

    private Coordinates coordinates;
    private final int[] QRS;             /* Unique identifier for the cell (The coordinate system) */
    private Color color;                 /* Board class will set the color */
    public List<Cell> connectedCells;           /* Board class will connect the cells */
    private boolean visited;           /* For the rules to know if the cell was visited */

    /** Constructor for the Cell class
     * @param QRS the QRS of the cell.
     */
    public Cell(int[] QRS) {
        this.coordinates = new Coordinates(QRS);
        this.QRS = QRS;
        this.connectedCells = new ArrayList<>();
    }

    /** Access from many classes link for GUI
     * @return the color of the cell.
     */
    public Color getColor() {
        return color;
    }

    /** Access from many classes link for GUI
     * @return the QRS of the cell.
     */
    public int[] getQRS() {
        return QRS;
    }

    /** Access from Board class only
     * @param color the color to set.
     */
    void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the visited status of the cell.
     */
    public boolean isVisited() {
        return this.visited;
    }

    /**
     * @param visited the visited status to set.
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public Cell clone() {
        try {
            Cell clonedCell = (Cell) super.clone();
            // Dado que Color es inmutable en Java, no es necesario clonarlo.
            // Sin embargo, la lista de celdas conectadas sí necesita ser clonada, pero solo sus referencias, ya que la estructura de conexión se maneja externamente.
            clonedCell.connectedCells = new ArrayList<>(this.connectedCells);
            // Las coordenadas también deben ser clonadas si son mutables.
            clonedCell.coordinates = new Coordinates(this.QRS.clone()); // Asumiendo que Coordinates tiene un constructor adecuado.
            // El campo 'visited' es un primitivo, por lo que se copia directamente.
            return clonedCell;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cell cannot be cloned", e);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return QRS[0] == ((Cell)obj).QRS[0] && QRS[1] == ((Cell)obj).QRS[1] && QRS[2] == ((Cell)obj).QRS[2];
    }
}
