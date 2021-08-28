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
public class DrawController {

    private static Contract.Model.Drawer model;
    private static Paint ballPaint;
    private static Paint brickPaint;
    private static Paint paddlePaint;
    private static Paint backgroundPaint;

    /**
     * Setter for colors
     *
     * @param colors of the game entities
     */
    public void setColors(int[] colors) {
        // Parsing of the Attributes in gameBoard_Attributes.xml
        backgroundPaint = new Paint();
        backgroundPaint.setColor(colors[0]);

        ballPaint = new Paint();
        ballPaint.setColor(colors[1]);

        brickPaint = new Paint();
        brickPaint.setColor(colors[2]);

        paddlePaint = new Paint();
        paddlePaint.setColor(colors[3]);
    }

    /**
     * Draw the paddle, bricks and ball on the canvas
     *
     * @param canvas to draw the game objects
     */
    public void drawGameObjects(Canvas canvas) {
        canvas.drawPaint(backgroundPaint);

        Ball ball = model.getBall();
        canvas.drawRect(ball.getX(),
                ball.getY(),
                ball.getX() + ball.getWidth(),
                ball.getY() + ball.getHeight(),
                ballPaint);

        Paddle paddle = model.getPaddle();
        canvas.drawRect(paddle.getX(),
                paddle.getY(),
                paddle.getX() + paddle.getWidth(),
                paddle.getY() + paddle.getHeight(),
                paddlePaint);

        for (int i = 0; i < model.getBricks().length; i++) {
            if (model.getBricks()[i] != null) {
                canvas.drawRect(model.getBricks()[i].getX(),
                        model.getBricks()[i].getY(),
                        model.getBricks()[i].getX() + model.getBricks()[i].getWidth(),
                        model.getBricks()[i].getY() + model.getBricks()[i].getHeight(),
                        brickPaint);
            }
        }
    }

    /**
     * Draw the game score
     *
     * @param canvas to draw the game objects
     */
    public void drawGameScore(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(model.getWidth() / 16);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.BLACK);
        canvas.drawText(
                "Score: " + model.getGameScore(),
                model.getWidth() / 8,
                model.getWidth() / 8,
                paint);

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
        paint.setTextSize(model.getWidth() / 8);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.BLUE);
        canvas.drawText(
                message,
                model.getWidth() / 2,
                model.getHeight() / 2,
                paint);
    }

    /**
     * Setter for the board
     *
     * @param model is the game board
     */
    public void setModel(Contract.Model.Drawer model) {
        this.model = model;
    }

    /**
     * Getter for the board
     *
     * @return board is the game board
     */
    public Contract.Model.Drawer getModel() {
        return model;
    }
}
