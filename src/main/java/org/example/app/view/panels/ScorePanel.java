package org.example.app.view.panels;

import org.example.app.controller.players.AbstractPlayer;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    private final JLabel player1Score;
    private final JLabel player2Score;

    public ScorePanel() {
        setLayout(new GridLayout(1, 2)); // Usa GridLayout para distribuir los componentes
        player1Score = new JLabel("Jugador 1: 0");
        player2Score = new JLabel("Jugador 2: 0");

        add(player1Score);
        add(player2Score);

        setPreferredSize(new Dimension(200, 60)); // Ajusta esto según necesites
    }

    public void updateScore(double score1, double score2) {
        player1Score.setText(": " + score1);
        player2Score.setText(": " + score2);
    }
}
