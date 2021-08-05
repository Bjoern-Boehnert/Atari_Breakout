package com.bboehnert.atari_breakout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.bboehnert.atari_breakout.entites.*;

public class GameBoardView extends View {

    private final int ballColor;
    private final int brickColor;
    private final int paddleColor;
    private final int backgroundColor;
    private Ball ball;
    private Paddle paddle;
    private Brick[] bricks = new Brick[24];
    private boolean isInitialised;
    private boolean isStarted;

    public GameBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Parsing of the Attributes in attrs.xml
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.GamefieldView);
        this.ballColor = typedArray.getColor(R.styleable.GamefieldView_ballColor, Color.BLUE);
        this.brickColor = typedArray.getInt(R.styleable.GamefieldView_brickColor, Color.RED);
        this.paddleColor = typedArray.getInt(R.styleable.GamefieldView_paddleColor, Color.GREEN);
        this.backgroundColor = typedArray.getInt(R.styleable.GamefieldView_backgroundColor, Color.GRAY);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(this.backgroundColor);

        if (!isInitialised) {
            initComponents();
            isInitialised = true;
        }

        canvas.drawRect(ball.getRectangle(), ball.getPaint());
        canvas.drawRect(paddle.getRectangle(), paddle.getPaint());

        boolean isWin = true;
        for (int i = 0; i < bricks.length; i++) {
            if (bricks[i] == null) {
                continue;
            } else if (bricks[i].getRectangle().intersect(ball.getRectangle())) {
                bricks[i] = null;
                ball.reflectY();
            } else {
                // Draw Brick
                isWin = false;
                canvas.drawRect(bricks[i].getRectangle(), bricks[i].getPaint());
            }
        }

        if (!isStarted) {
            return;
        }

        if (ball.getX() < 0 || (ball.getX() + ball.getWidth()) > getWidth()) {
            ball.reflectX();
        } else if (ball.getY() < 0) {
            ball.reflectY();
        } else if (ball.getRectangle().intersect(paddle.getRectangle())) {
            // Is reflected by paddle
            ball.reflectY();
            float reflectingPos = paddle.getReflectFactor(ball.getX() + ball.getWidth() / 2);
            ball.reflectByPaddle(reflectingPos);

        } else if (ball.getY() + ball.getWidth() > getHeight()) {
            // Game Over
            showGameOverScreen(canvas, "You lost!");
            return;

        } else if (isWin) {
            showGameOverScreen(canvas, "You won!");
            return;
        }

        moveBall();
        invalidate();
    }

    private void initComponents() {
        ball = new Ball(getWidth() / 2,
                getHeight() / 2,
                getWidth() / 32,
                ballColor);

        int paddleHeight = getHeight() / 16;
        paddle = new Paddle(
                getWidth() / 3,
                getHeight() - paddleHeight,
                getWidth() / 3,
                paddleHeight,
                this.paddleColor);

        int spacing = getWidth() / 256;
        int brickInRow = 8;
        int rowsCount = 3;

        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < bricks.length / rowsCount; j++) {
                bricks[(i * brickInRow) + j] = new Brick(
                        (j * getWidth() / brickInRow),
                        i * getHeight() / 16,
                        getWidth() / brickInRow - spacing,
                        getHeight() / 16 - spacing,
                        this.brickColor);
            }
        }
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

    private void moveBall() {
        ball.move();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        // Position the paddle always centrally
        float xPos = e.getX() - paddle.getWidth() / 2;

        if (xPos < 0) {
            // Exceeds left limit
            paddle.move(0);
        } else if (xPos + paddle.getWidth() > getWidth()) {
            // Exceeds Right limit
            paddle.move(getWidth() - paddle.getWidth());
        } else {
            paddle.move(xPos);
        }
        return true;
    }

    public void startGame() {
        this.isStarted = true;
        if (isInitialised) {
            isInitialised = false;
        }
        invalidate();
    }

}