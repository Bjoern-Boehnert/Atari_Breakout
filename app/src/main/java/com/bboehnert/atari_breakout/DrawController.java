package com.bboehnert.atari_breakout;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.bboehnert.atari_breakout.entites.Ball;
import com.bboehnert.atari_breakout.entites.GameBoard;
import com.bboehnert.atari_breakout.entites.Paddle;

/**
 * Controller for handling all draw events for the game
 */
class DrawController {

    private GameBoard board;
    private final Paint ballPaint;
    private final Paint brickPaint;
    private final Paint paddlePaint;
    private final Paint backgroundPaint;

    /**
     * Constructor
     *
     * @param colors of the game entities
     */
    public DrawController(TypedArray colors) {
        // Parsing of the Attributes in gameBoard_Attributes.xml
        int backgroundColor = colors.getInt(R.styleable.GameBoardView_backgroundColor, Color.GRAY);
        int ballColor = colors.getColor(R.styleable.GameBoardView_ballColor, Color.BLUE);
        int brickColor = colors.getInt(R.styleable.GameBoardView_brickColor, Color.RED);
        int paddleColor = colors.getInt(R.styleable.GameBoardView_paddleColor, Color.GREEN);
        colors.recycle();

        ballPaint = new Paint();
        ballPaint.setColor(ballColor);

        brickPaint = new Paint();
        brickPaint.setColor(brickColor);

        paddlePaint = new Paint();
        paddlePaint.setColor(paddleColor);

        backgroundPaint = new Paint();
        backgroundPaint.setColor(backgroundColor);
    }

    /**
     * Draw the paddle, bricks and ball on the canvas
     *
     * @param canvas to draw the game objects
     */
    public void drawGameObjects(Canvas canvas) {
        canvas.drawPaint(backgroundPaint);

        Ball ball = board.getBall();
        canvas.drawRect(ball.getX(),
                ball.getY(),
                ball.getX() + ball.getWidth(),
                ball.getY() + ball.getHeight(),
                ballPaint);

        Paddle paddle = board.getPaddle();
        canvas.drawRect(paddle.getX(),
                paddle.getY(),
                paddle.getX() + paddle.getWidth(),
                paddle.getY() + paddle.getHeight(),
                paddlePaint);

        for (int i = 0; i < this.board.getBricks().length; i++) {
            if (this.board.getBricks()[i] != null) {
                canvas.drawRect(board.getBricks()[i].getX(),
                        board.getBricks()[i].getY(),
                        board.getBricks()[i].getX() + board.getBricks()[i].getWidth(),
                        board.getBricks()[i].getY() + board.getBricks()[i].getHeight(),
                        brickPaint);
            }
        }
    }

    /**
     * Draw the Game Over Screen with the desired message
     *
     * @param canvas  to draw the game over
     * @param message is the text on finished game
     */
    public void drawGameOverScreen(Canvas canvas, String message) {
        canvas.drawPaint(backgroundPaint);

        Paint paint = new Paint();
        paint.setTextSize(board.getWidth() / 8);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.BLUE);
        canvas.drawText(
                message,
                board.getWidth() / 2,
                board.getHeight() / 2,
                paint);
    }

    /**
     * Setter for the board
     *
     * @param board is the game board
     */
    public void setBoard(GameBoard board) {
        this.board = board;
    }

    /**
     * Getter for the board
     *
     * @return board is the game board
     */
    public GameBoard getBoard() {
        return board;
    }
}
