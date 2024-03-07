package org.example.app.view;

import org.example.app.controller.players.AbstractPlayer;
import org.example.app.model.GameModel;
import org.example.app.model.board.Cell;
import org.example.app.model.board.Coordinates;
import org.example.app.model.rules.AbstractRules;
import org.example.app.view.grid.Grid;
import org.example.app.view.panels.ScorePanel;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiConsumer;

public class GameView extends JFrame {

    private final Grid GRID;
    private final ScorePanel SCORE_PANEL;
    public GameView(GameModel gameModel) {
        setTitle("Hex Game");
        setSize(1500, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.GRID = new Grid(gameModel, new int[]{30, 700, 400});
        this.GRID.setBackground(Color.WHITE);
        SCORE_PANEL = new ScorePanel();
        add(SCORE_PANEL, BorderLayout.NORTH); // Añade el panel de puntajes en la parte superior
        add(GRID, BorderLayout.CENTER); // Añade el grid en el centro
        setVisible(true);

    }
    public void updateScores(double score1, double score2) {
        SCORE_PANEL.updateScore(score1, score2);
    }
    public void update() {

        GRID.repaint();
        SCORE_PANEL.repaint();
    }

    public void setCellClickListener(BiConsumer<Cell, Coordinates> cellClickListener) {
        GRID.setCellClickListener(cellClickListener);
    }


}
