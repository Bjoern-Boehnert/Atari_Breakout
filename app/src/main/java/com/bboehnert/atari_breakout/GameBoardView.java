package com.bboehnert.atari_breakout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.bboehnert.atari_breakout.entites.Brick;
import com.bboehnert.atari_breakout.entites.GameBoard;
import com.bboehnert.atari_breakout.entites.Redrawable;

public class GameBoardView extends View implements Redrawable {

    private final int ballColor;
    private final int brickColor;
    private final int paddleColor;
    private final int backgroundColor;

    private GameBoard board;
    private String gameOverMessage = "Klick On Restart!";

    public GameBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Parsing of the Attributes in attrs.xml
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.GameBoardView);
        this.ballColor = typedArray.getColor(R.styleable.GameBoardView_ballColor, Color.BLUE);
        this.brickColor = typedArray.getInt(R.styleable.GameBoardView_brickColor, Color.RED);
        this.paddleColor = typedArray.getInt(R.styleable.GameBoardView_paddleColor, Color.GREEN);
        this.backgroundColor = typedArray.getInt(R.styleable.GameBoardView_backgroundColor, Color.GRAY);
        typedArray.recycle();
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (board == null) {
            this.board = new GameBoard(right, bottom,
                    ballColor,
                    brickColor,
                    paddleColor,
                    this);
            board.initComponents();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(this.backgroundColor);
        canvas.drawRect(board.getBall().getRectangle(), board.getBall().getPaint());
        canvas.drawRect(board.getPaddle().getRectangle(), board.getPaddle().getPaint());
        for (Brick brick : board.getBricks()) {
            if (brick != null) {
                canvas.drawRect(brick.getRectangle(), brick.getPaint());
            }
        }

        if (!board.isGameStarted()) {
            showGameOverScreen(canvas, this.gameOverMessage);
        }
        board.checkBoundaries();
    }

    private void showGameOverScreen(Canvas canvas, String message) {
        canvas.drawColor(this.backgroundColor);

        Paint paint = new Paint();
        paint.setTextSize(getWidth() / 8);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.BLUE);
        canvas.drawText(
                message,
                getWidth() / 2,
                getHeight() / 2,
                paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        board.movePaddle(e.getX());
        return true;
    }

    public void startGame() {
        board.restartGame();
        invalidate();
    }

    @Override
    public void redraw() {
        invalidate();
    }

    @Override
    public void drawGameOver(String message) {
        this.gameOverMessage = message;
        invalidate();
    }
}