package com.bboehnert.atari_breakout.mvp;

/**
 * Representing the View of the MVP Pattern
 */
public interface View {

    /**
     * update view component for redrawing new model data
     * @param model
     */
    void updateViewComponent(ModelDisplayable model);

    /**
     * play paddle sound
     */
    void playPaddleCollision();

    /**
     * play brick sound
     */
    void playBrickCollision();

    /**
     * Getter for custom undecided message
     *
     * @return the message
     */
    String getUndecidedMessage();

    /**
     * Getter for custom winner message
     *
     * @return the message
     */
    String getWinMessage();

    /**
     * Getter for custom losing message
     *
     * @return the message
     */
    String getLostMessage();

    void drawGameOverScreen(String message);
}
