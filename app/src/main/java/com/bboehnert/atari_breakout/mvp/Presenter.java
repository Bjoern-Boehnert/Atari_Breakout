package com.bboehnert.atari_breakout.mvp;

/**
 * Representing the Presenter of the MVP Pattern. It shall cover user interactions effecting
 * the game
 */
public interface Presenter {

    /**
     * Action on restart Button press
     */
    void restartButtonPressed();

    /**
     * Action on touching the paddle
     *
     * @param x is the location on the x-axis
     */
    void touchedPaddle(float x);

    /**
     * Action on getting the game action
     *
     */
    void doGameActions();
}
