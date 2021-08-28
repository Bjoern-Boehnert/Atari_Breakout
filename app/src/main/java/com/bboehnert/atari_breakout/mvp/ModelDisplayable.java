package com.bboehnert.atari_breakout.mvp;

import com.bboehnert.atari_breakout.entites.Ball;
import com.bboehnert.atari_breakout.entites.Brick;
import com.bboehnert.atari_breakout.entites.Paddle;

public interface ModelDisplayable {
    /**
     * Getter for the ball
     *
     * @return the ball
     */
    Ball getBall();

    /**
     * Getter for the bricks
     *
     * @return the bricks as an array
     */
    Brick[] getBricks();

    /**
     * Getter for the Paddle
     *
     * @return the paddle
     */
    Paddle getPaddle();

    /**
     * Getter for the total width of the game board
     *
     * @return the game board width
     */
    float getWidth();

    /**
     * Getter for the total height of the game board
     *
     * @return the game board height
     */
    float getHeight();

    /**
     * Getter for the score of the game
     *
     * @return a value about the game score
     */
    int getGameScore();
}
