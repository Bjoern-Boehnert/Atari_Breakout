package com.bboehnert.atari_breakout.entites;

public class GameBoard {

    private float width;
    private float height;
    private Ball ball;
    private Paddle paddle;
    private Brick[] bricks;
    private final Redrawable redrawable;
    private boolean isStarted = false;

    public GameBoard(Redrawable redrawable) {
        this.redrawable = redrawable;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void initComponents() {
        this.ball = initBall();
        this.bricks = initBricks();
        this.paddle = initPaddle();
    }

    private Ball initBall() {
        return new Ball(width / 2,
                height / 2,
                width / 32);
    }

    private Paddle initPaddle() {
        float paddleHeight = height / 16;
        return new Paddle(
                width / 3,
                height - paddleHeight,
                width / 3,
                paddleHeight);
    }

    private boolean isWin() {
        for (Brick brick : bricks) {
            if (brick != null) {
                return false;
            }
        }
        return true;
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
        return bricks[index].isIntersecting(ball);
    }

    private boolean checkPaddleCollision() {
        return paddle.isIntersecting(ball);
    }

    public void doGameActions() {
        if (ball.getX() < 0 || (ball.getX() + ball.getWidth()) > width) {
            ball.reflectX();

        } else if (ball.getY() < 0) {
            ball.reflectY();

        } else if (checkPaddleCollision()) {
            // Is reflected by paddle
            ball.reflectY();
            float reflectingPos = paddle.getReflectFactor(ball.getX() + ball.getWidth() / 2);
            ball.reflectByPaddle(reflectingPos);

        } else if (destroyBrick()) {
            ball.reflectY();

        } else if (ball.getY() + ball.getWidth() > height) {
            // Game Over
            isStarted = false;
            redrawable.drawGameOver("You lost!");
            return;

        } else if (isWin()) {
            isStarted = false;
            redrawable.drawGameOver("You won!");
            return;
        }
        moveBall();
    }

    private void moveBall() {
        ball.move();
        redrawable.redraw();
    }

    private Brick[] initBricks() {
        bricks = new Brick[24];

        float spacing = width / 256;
        int brickInRow = 8;
        int rowsCount = 3;

        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < bricks.length / rowsCount; j++) {
                bricks[(i * brickInRow) + j] = new Brick(
                        (j * width / brickInRow),
                        i * height / 16,
                        width / brickInRow - spacing,
                        height / 16 - spacing);
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
