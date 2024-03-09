package org.example.app.agents.decision;

import org.example.app.model.board.Cell;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Action {

    List<Cell> cell;
    List<Color> color;
    public Action(Cell cell, Color color) {
        this.cell = new ArrayList<>(List.of(cell));
        this.color = new ArrayList<>(List.of(color));
    }

    public List<Cell> getCell() {
        return cell;
    }


}
