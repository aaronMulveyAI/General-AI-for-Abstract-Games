package org.example.app.model.rules;

import java.awt.*;

/**
 * Class that contains the rules constants
 * @param name the name of the game
 * @param size the size of the board
 * @param emptyColor the color of an empty cell
 * @param colorP1 the color of player 1
 * @param colorP2 the color of player 2
 */
public record RulesConstants(
        String name,
        int size,
        Color emptyColor,
        Color colorP1,
        Color colorP2) {}
