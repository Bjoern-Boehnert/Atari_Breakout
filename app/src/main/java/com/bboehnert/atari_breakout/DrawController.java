package com.bboehnert.atari_breakout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.bboehnert.atari_breakout.entites.Brick;
import com.bboehnert.atari_breakout.entites.GameBoard;

import androidx.annotation.NonNull;

public class DrawController {

    private boolean isGameOver;
    private Context context;
    private GameBoard board;
    private final Paint ballPaint;
    private final Paint brickPaint;
    private final Paint paddlePaint;
    private final Paint backgroundPaint;
    private String gameOverMessage;



    public DrawController(Context context, AttributeSet attrs, GameBoard board) {
        this.context = context;
        this.board = board;
        this.isGameOver = false;

        // Parsing of the Attributes in attrs.xml
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
    }


    public void draw(@NonNull Canvas canvas, boolean isGameOver) {
        canvas.drawPaint(backgroundPaint);
        canvas.drawRect(board.getBall().getRectangle(), ballPaint);
        canvas.drawRect(board.getPaddle().getRectangle(), paddlePaint);
        for (Brick brick : board.getBricks()) {
            if (brick != null) {
                canvas.drawRect(brick.getRectangle(), brickPaint);
            }
        }

        if (isGameOver) {
            drawGameOverScreen(canvas, gameOverMessage);
        }

    }

    public void setGameOver(boolean value, String message) {
        this.isGameOver = value;
        this.gameOverMessage = message;
    }

    public void redrawGameState() {
        board.doGameActions();
    }

    public void drawGameOverScreen(@NonNull Canvas canvas, @NonNull String message) {
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
