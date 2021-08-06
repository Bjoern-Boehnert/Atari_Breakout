package com.bboehnert.atari_breakout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.bboehnert.atari_breakout.entites.Ball;
import com.bboehnert.atari_breakout.entites.GameBoard;
import com.bboehnert.atari_breakout.entites.Paddle;

class DrawController {

    private boolean isGameOver;
    private Context context;
    private GameBoard board;
    private final Paint ballPaint;
    private final Paint brickPaint;
    private final Paint paddlePaint;
    private final Paint backgroundPaint;
    private String gameOverMessage;

    private RectF[] bricksRects;

    public DrawController(Context context, AttributeSet attrs, GameBoard board) {
        this.context = context;
        this.board = board;
        this.isGameOver = false;

        // Parsing of the Attributes in gameBoard_Attributes.xml
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GameBoardView);
        int ballColor = typedArray.getColor(R.styleable.GameBoardView_ballColor, Color.BLUE);
        int brickColor = typedArray.getInt(R.styleable.GameBoardView_brickColor, Color.RED);
        int paddleColor = typedArray.getInt(R.styleable.GameBoardView_paddleColor, Color.GREEN);
        int backgroundColor = typedArray.getInt(R.styleable.GameBoardView_backgroundColor, Color.GRAY);
        typedArray.recycle();

        ballPaint = new Paint();
        ballPaint.setColor(ballColor);

        brickPaint = new Paint();
        brickPaint.setColor(brickColor);

        paddlePaint = new Paint();
        paddlePaint.setColor(paddleColor);

        backgroundPaint = new Paint();
        backgroundPaint.setColor(backgroundColor);

        bricksRects = new RectF[board.getBricks().length];

        for (int i = 0; i < bricksRects.length; i++) {
            bricksRects[i] = new RectF(board.getBricks()[i].getX(),
                    board.getBricks()[i].getY(),
                    board.getBricks()[i].getX() + board.getBricks()[i].getWidth(),
                    board.getBricks()[i].getY() + board.getBricks()[i].getHeight());
        }

    }

    public void draw(Canvas canvas, boolean isGameOver) {
        canvas.drawPaint(backgroundPaint);
        drawBall(canvas, ballPaint);
        drawPaddle(canvas, paddlePaint);

        for (int i = 0; i < this.board.getBricks().length; i++) {
            if (this.board.getBricks()[i] != null) {
                canvas.drawRect(this.bricksRects[i], brickPaint);
            }
        }

        if (isGameOver) {
            drawGameOverScreen(canvas, gameOverMessage);
        }

    }

    private void drawBall(Canvas canvas, Paint paint) {
        Ball ball = board.getBall();
        canvas.drawRect(ball.getX(),
                ball.getY(),
                ball.getX() + ball.getWidth(),
                ball.getY() + ball.getHeight(),
                paint);
    }

    private void drawPaddle(Canvas canvas, Paint paint) {
        Paddle paddle = board.getPaddle();
        canvas.drawRect(paddle.getX(),
                paddle.getY(),
                paddle.getX() + paddle.getWidth(),
                paddle.getY() + paddle.getHeight(),
                paint);
    }

    public void setGameOver(boolean value, String message) {
        this.isGameOver = value;
        this.gameOverMessage = message;
    }

    public void updateGameState() {
        board.doGameActions();
    }

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

}
