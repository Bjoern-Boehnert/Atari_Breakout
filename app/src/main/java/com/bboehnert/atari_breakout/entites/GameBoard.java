package com.bboehnert.atari_breakout.entites;

import com.bboehnert.atari_breakout.Contract;

/**
 * Class that represents the game board with it's entities
 */
public class GameBoard implements Contract.Model, Contract.Model.Drawer {

    public enum GameState {
        Won, Lost, Undecided
    }

    private final float width, height;
    private Ball ball;
    private Paddle paddle;
    private Brick[] bricks;
    private boolean isStarted = false;
    private int gameScore;
    private GameState state = GameState.Undecided;

    public GameBoard(float width,
                     float height) {

        this.width = width;
        this.height = height;
    }

    /**
     * Initialize the ball, paddle and bricks
     */
    private boolean isDestroyBrick() {
        for (int i = 0; i < bricks.length; i++) {
            if (bricks[i] != null && bricks[i].isIntersecting(ball)) {
                bricks[i] = null;
                return true;
            }
        }
        return false;
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
     * Initialize the ball, paddle and bricks in relative sizes to the width and height of
     * the game board
     */
    @Override
    public void initComponents(Contract.Model.DrawListener drawListener) {

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
        int brickInRow = 6;
        int rowsCount = 4;

        float spacing = width / 256;
        float margin = width / 3;
        float brickWidth = width / brickInRow - margin / brickInRow;
        float brickHeight = height / 8 - margin / rowsCount;


        bricks = new Brick[rowsCount * brickInRow];
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < bricks.length / rowsCount; j++) {
                bricks[(i * brickInRow) + j] = new Brick(
                        j * brickWidth + margin / 2,
                        i * brickHeight + margin,
                        brickWidth - spacing,
                        brickHeight - spacing);
            }
        }
        drawListener.redraw();
    }

    @Override
    public void doGameAction(Contract.Model.DrawListener drawListener,
                             Contract.Model.AudioListener audioListener) {

        if (!isStarted) {
            return;
        }

        if (isWin()) {
            isStarted = false;
            state = GameState.Won;

        } else if (ball.getY() + ball.getWidth() > height) {
            // Game Over
            isStarted = false;
            state = GameState.Lost;

        } else if (ball.getX() < 0 || (ball.getX() + ball.getWidth()) > width) {
            ball.reflectX();

        } else if (ball.getY() < 0) {
            ball.reflectY();

        } else if (isDestroyBrick()) {
            ball.reflectY();
            gameScore++;
            audioListener.playBrickCollision();

        } else if (paddle.isIntersecting(ball)) {
            // Is reflected by paddle
            ball.reflectY();
            float reflectingPos = paddle.getReflectFactor(ball.getX() + ball.getWidth() / 2);
            ball.reflectByPaddle(reflectingPos);

            // Play Audio
            audioListener.playPaddleCollision();
        }
        ball.move();
        drawListener.redraw();
    }

    /**
     * Move the paddle to a new location on the game board
     *
     * @param touchPos of the paddles new position
     */
    @Override
    public void movePaddle(float touchPos, Contract.Model.DrawListener drawListener) {

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
    @Override
    public void restartGame() {
        state = GameState.Undecided;
        isStarted = true;
        gameScore = 0;
    }

    /**
     * Getter for the ball
     *
     * @return the ball
     */
    @Override
    public Ball getBall() {
        return ball;
    }

    /**
     * Getter for the Paddle
     *
     * @return the paddle
     */
    @Override
    public Paddle getPaddle() {
        return paddle;
    }

    /**
     * Getter for the bricks
     *
     * @return the bricks as an array
     */
    @Override
    public Brick[] getBricks() {
        return bricks;
    }

    /**
     * Getter for game started
     *
     * @return a value about the game start
     */
    @Override
    public boolean isGameStarted() {
        return isStarted;
    }

    /**
     * Getter for the total width of the game board
     *
     * @return the game board width
     */
    @Override
    public float getWidth() {
        return width;
    }

    /**
     * Getter for the total height of the game board
     *
     * @return the game board height
     */
    @Override
    public float getHeight() {
        return height;
    }

    /**
     * Getter for the score of the game
     *
     * @return a value about the game score
     */
    @Override
    public int getGameScore() {
        return gameScore;
    }

    @Override
    public GameState getState() {
        return state;
    }
}
