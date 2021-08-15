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
public final class DrawController {

    private static GameBoard board;
    private static Paint ballPaint;
    private static Paint brickPaint;
    private static Paint paddlePaint;
    private static Paint backgroundPaint;

    /**
     * Setter for colors
     *
     * @param colors of the game entities
     */
    public static void setColors(TypedArray colors) {
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
    public static void drawGameObjects(Canvas canvas) {
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

        for (int i = 0; i < board.getBricks().length; i++) {
            if (board.getBricks()[i] != null) {
                canvas.drawRect(board.getBricks()[i].getX(),
                        board.getBricks()[i].getY(),
                        board.getBricks()[i].getX() + board.getBricks()[i].getWidth(),
                        board.getBricks()[i].getY() + board.getBricks()[i].getHeight(),
                        brickPaint);
            }
        }
    }

    /**
     * Draw the game score
     *
     * @param canvas to draw the game objects
     */
    public static void drawGameScore(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(board.getWidth() / 16);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.BLACK);
        canvas.drawText(
                "Score: " + board.getGameScore(),
                board.getWidth() / 8,
                board.getWidth() / 8,
                paint);

    }

    /**
     * Draw the Game Over Screen with the desired message
     *
     * @param canvas  to draw the game over
     * @param message is the text on finished game
     */
    public static void drawGameOverScreen(Canvas canvas, String message) {
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
     * @param gameBoard is the game board
     */
    public static void setBoard(GameBoard gameBoard) {
        board = gameBoard;
    }

    /**
     * Getter for the board
     *
     * @return board is the game board
     */
    public static GameBoard getBoard() {
        return board;
    }
}
