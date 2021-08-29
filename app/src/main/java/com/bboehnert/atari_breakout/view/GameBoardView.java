package com.bboehnert.atari_breakout.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import com.bboehnert.atari_breakout.R;
import com.bboehnert.atari_breakout.mvp.ModelDisplayable;

/**
 * Class for handling user/game actions related to the game board (View)
 */
public class GameBoardView extends View {

    private final DrawController drawer;
    private String message;
    private boolean isGameOver;
    public OnFinishListener finishDrawing;

    public GameBoardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        // Set Colors
        TypedArray colors = context.obtainStyledAttributes(attributeSet, R.styleable.GameBoardView);

        int[] colorArray = new int[4];
        colorArray[0] = colors.getInt(R.styleable.GameBoardView_backgroundColor, Color.GRAY);
        colorArray[1] = colors.getInt(R.styleable.GameBoardView_ballColor, Color.BLUE);
        colorArray[2] = colors.getInt(R.styleable.GameBoardView_brickColor, Color.RED);
        colorArray[3] = colors.getInt(R.styleable.GameBoardView_paddleColor, Color.GREEN);
        colors.recycle();

        drawer = new DrawController();
        drawer.setColors(colorArray);
    }

    @Override
    public void onDraw(Canvas canvas) {

        if (drawer.getModel() == null) {
            return;
        }

        if (isGameOver) {
            drawer.drawGameOverScreen(canvas, message);
        } else {
            drawer.drawGameObjects(canvas);
            drawer.drawGameScore(canvas);
            finishDrawing.finish();
        }

    }

    public void drawGameOver(ModelDisplayable model, String message) {
        isGameOver = true;
        drawer.setModel(model);
        this.message = message;
    }

    public void redraw(ModelDisplayable model) {
        isGameOver = false;
        drawer.setModel(model);
    }

}