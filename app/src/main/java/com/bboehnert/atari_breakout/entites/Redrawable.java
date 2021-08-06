package com.bboehnert.atari_breakout.entites;

public interface Redrawable {
    /**
     * Draw the UI game entities of the game board
     */
    void redraw();

    /**
     * Draw the game over screen
     *
     * @param message to display
     */
    void drawGameOver(String message);
}
