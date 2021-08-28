package com.bboehnert.atari_breakout.mvp;

import com.bboehnert.atari_breakout.entites.Ball;
import com.bboehnert.atari_breakout.entites.Brick;
import com.bboehnert.atari_breakout.entites.GameBoard;
import com.bboehnert.atari_breakout.entites.Paddle;

/**
 * Representing the model of the MVP Pattern
 *
 */
public interface Model {

    /**
     * Restart the game and init the game entities
     */
    void restartGame();

    /**
     * Move the paddle to a new location on the game board
     *
     * @param x of the paddles new position
     * @param drawer for drawing the view
     */
    void movePaddle(float x, DrawListener drawer);

    /**
     * Do one game cycle of moving the ball and includes boundary checks
     *
     * @param audioListener for audio feedback
     * @param drawListener redraw frame for updating UI
     */
    void doGameAction(DrawListener drawListener,
                      AudioListener audioListener);

    /**
     * Initialize the ball, paddle and bricks in relative sizes to the width and height of
     * the game board
     */
    void initComponents(DrawListener drawer);

    /**
     * Getter for game started
     *
     * @return a value about the game start
     */
    boolean isGameStarted();

    /**
     * Getter for the game state for displaying the gameOver message
     *
     * @return a value about the game state
     */
    GameBoard.GameState getState();

    interface Drawer {
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

    /**
     * Representing the listener for game audio
     *
     */
    interface AudioListener {

        void playPaddleCollision();

        void playBrickCollision();
    }

    /**
     * Representing the listener for drawing the frame
     *
     */
    interface DrawListener {
        void redraw();
    }
}
