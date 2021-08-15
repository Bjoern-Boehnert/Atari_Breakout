package com.bboehnert.atari_breakout.entites;

import com.bboehnert.atari_breakout.AudioListener;
import com.bboehnert.atari_breakout.DrawListener;

/**
 * Class that represents the game board with it's entities
 */
public class GameBoard {

    public enum GameAction {
        X_Reflection, Y_Reflection, GameWin, GameLose

    }

    private  float width, height;
    private DrawListener drawListener;
    private AudioListener audioListener;
    private Ball ball;
    private Paddle paddle;
    private Brick[] bricks;
    private boolean isStarted, isFinished = false;

    /**
     * Setter for the width
     *
     * @param width of the game board
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * Setter for the height
     *
     * @param height of the game board
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * Setter for the Drawer
     *
     * @param listener is the drawer
     */
    public void setDrawer(DrawListener listener) {
        this.drawListener = listener;
    }

    /**
     * Setter for the Audio
     *
     * @param listener is the audio player
     */
    public void setAudioPlayer(AudioListener listener) {
        this.audioListener = listener;
    }

    /**
     * Initialize the ball, paddle and bricks in relative sizes to the width and height of
     * the game board
     */
    public void initComponents() {

        // Init Ball
        this.ball = new Ball(width / 2,
                height / 2,
                width / 32);

        // Init Paddle
        float paddleHeight = height / 16;
        this.paddle = new Paddle(
                width / 3,
                height - paddleHeight,
                width / 3,
                paddleHeight);

        // Init Bricks
        float spacing = width / 256;
        int brickInRow = 8;
        int rowsCount = 3;

        bricks = new Brick[24];
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < bricks.length / rowsCount; j++) {
                bricks[(i * brickInRow) + j] = new Brick(
                        (j * width / brickInRow),
                        i * height / 16,
                        width / brickInRow - spacing,
                        height / 16 - spacing);
            }
        }
        drawListener.redraw();
    }

    /**
     * Checks whether the win condition is meet of destroying all bricks
     *
     * @return a value, whether the player has won the game
     */
    private boolean isWin() {
        for (Brick brick : bricks) {
            if (brick != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Initialize the ball, paddle and bricks
     */
    private boolean isDestroyBrick() {
        for (int i = 0; i < bricks.length; i++) {
            if (bricks[i] != null && bricks[i].isIntersecting(ball)) {
                bricks[i] = null;

                audioListener.playBrickCollision();
                return true;
            }
        }
        return false;
    }

    /**
     * Get the next game action of the game board
     *
     * @return a action to be executed
     */
    public GameAction getCurrentAction() {

        if (!isStarted) {
            return null;
        }
        GameAction action = null;

        if (isWin()) {
            action = GameAction.GameWin;

        } else if (ball.getY() + ball.getWidth() > height) {
            // Game Over
            action = GameAction.GameLose;

        } else if (ball.getX() < 0 || (ball.getX() + ball.getWidth()) > width) {
            action = GameAction.X_Reflection;

        } else if (ball.getY() < 0 || isDestroyBrick()) {
            action = GameAction.Y_Reflection;

        } else if (paddle.isIntersecting(ball)) {
            // Is reflected by paddle
            action = GameAction.Y_Reflection;
            float reflectingPos = paddle.getReflectFactor(ball.getX() + ball.getWidth() / 2);
            ball.reflectByPaddle(reflectingPos);

            // Play Audio
            audioListener.playPaddleCollision();
        }
        return action;
    }

    /**
     * Process the game action on the game board and move the ball
     *
     * @param action to process
     */
    public void processAction(GameAction action) {

        if (!isStarted) {
            return;
        }

        if (action != null) {
            if (action == GameAction.GameWin) {
                isStarted = false;
                isFinished = true;

            } else if (action == GameAction.GameLose) {
                isStarted = false;
                isFinished = true;

            } else if (action == GameAction.X_Reflection) {
                ball.reflectX();

            } else if (action == GameAction.Y_Reflection) {
                ball.reflectY();
            }
        }
        ball.move();
        drawListener.redraw();
    }

    /**
     * Move the paddle to a new location on the game board
     *
     * @param touchPos of the paddles new position
     */
    public void movePaddle(float touchPos) {

        // Position the paddle always centrally
        float xPos = touchPos - paddle.getWidth() / 2;

        if (xPos < 0) {
            // Exceeds left limit
            paddle.move(0);
        } else if (xPos + paddle.getWidth() > width) {
            // Exceeds Right limit
            paddle.move(width - paddle.getWidth());
        } else {
            paddle.move(xPos);
        }
        drawListener.redraw();
    }

    /**
     * Restart the game and init the game entities
     */
    public void restartGame() {
        isStarted = true;
        isFinished = false;
        initComponents();
    }

    /**
     * Getter for game started
     *
     * @return a value about the game start
     */
    public boolean isGameStarted() {
        return isStarted;
    }

    /**
     * Getter for the ball
     *
     * @return the ball
     */
    public Ball getBall() {
        return ball;
    }

    /**
     * Getter for the Paddle
     *
     * @return the paddle
     */
    public Paddle getPaddle() {
        return paddle;
    }

    /**
     * Getter for the bricks
     *
     * @return the bricks as an array
     */
    public Brick[] getBricks() {
        return bricks;
    }

    /**
     * Getter for the total width of the game board
     *
     * @return the game board width
     */
    public float getWidth() {
        return width;
    }

    /**
     * Getter for the total height of the game board
     *
     * @return the game board height
     */
    public float getHeight() {
        return height;
    }


}
