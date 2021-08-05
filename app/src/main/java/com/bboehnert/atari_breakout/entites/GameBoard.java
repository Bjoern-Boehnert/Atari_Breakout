package com.bboehnert.atari_breakout.entites;

public class GameBoard {

    private int width, height;
    private Ball ball;
    private Paddle paddle;
    private Brick[] bricks;
    private final int ballColor, brickColor, paddleColor;
    private Redrawable redrawable;
    private boolean isStarted = false;


    public GameBoard(int width, int height, int ballColor, int brickColor, int paddleColor, Redrawable redrawable) {
        this.width = width;
        this.height = height;
        this.ballColor = ballColor;
        this.brickColor = brickColor;
        this.paddleColor = paddleColor;
        this.redrawable = redrawable;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void initComponents() {
        this.ball = initBall(ballColor);
        this.bricks = initBricks(brickColor);
        this.paddle = initPaddle(paddleColor);
    }

    private Ball initBall(int color) {
        return new Ball(width / 2,
                height / 2,
                width / 32,
                color);
    }

    private Paddle initPaddle(int color) {
        int paddleHeight = height / 16;
        return new Paddle(
                width / 3,
                height - paddleHeight,
                width / 3,
                paddleHeight,
                color);
    }

    private boolean isWin() {
        boolean isWin = true;
        for (int i = 0; i < bricks.length; i++) {
            if (bricks[i] == null) {
                continue;
            } else {
                isWin = false;
            }
        }
        return isWin;
    }

    private boolean destroyBrick() {

        for (int i = 0; i < bricks.length; i++) {
            if (bricks[i] != null && checkBrickCollision(i)) {
                bricks[i] = null;
                return true;
            }
        }
        return false;
    }

    private boolean checkBrickCollision(int index) {
        return bricks[index].getRectangle().intersect(ball.getRectangle());
    }

    public void checkBoundaries() {

        if (ball.getX() < 0 || (ball.getX() + ball.getWidth()) > width) {
            ball.reflectX();

        } else if (ball.getY() < 0) {
            ball.reflectY();

        } else if (ball.getRectangle().intersect(paddle.getRectangle())) {
            // Is reflected by paddle
            ball.reflectY();
            float reflectingPos = paddle.getReflectFactor(ball.getX() + ball.getWidth() / 2);
            ball.reflectByPaddle(reflectingPos);

        } else if (destroyBrick()) {
            ball.reflectY();

        } else if (ball.getY() + ball.getWidth() > height) {
            // Game Over
            redrawable.drawGameOver("You lost!");
            return;

        } else if (isWin()) {
            redrawable.drawGameOver("You won!");
            return;
        }
        moveBall();
    }

    private void moveBall() {
        ball.move();
        redrawable.redraw();
    }

    private Brick[] initBricks(int color) {
        bricks = new Brick[24];

        int spacing = width / 256;
        int brickInRow = 8;
        int rowsCount = 3;

        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < bricks.length / rowsCount; j++) {
                bricks[(i * brickInRow) + j] = new Brick(
                        (j * width / brickInRow),
                        i * height / 16,
                        width / brickInRow - spacing,
                        height / 16 - spacing,
                        color);
            }
        }
        return bricks;
    }

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
        redrawable.redraw();
    }

    public boolean isGameStarted() {
        return isStarted;
    }

    public void restartGame() {
        isStarted = true;
        initComponents();
    }

    public Ball getBall() {
        return ball;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public Brick[] getBricks() {
        return bricks;
    }
}
