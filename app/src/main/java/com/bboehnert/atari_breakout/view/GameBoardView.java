package com.bboehnert.atari_breakout.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import com.bboehnert.atari_breakout.DrawController;
import com.bboehnert.atari_breakout.mvp.ModelDisplayable;
import com.bboehnert.atari_breakout.mvp.Presenter;
import com.bboehnert.atari_breakout.R;

/**
 * Class for handling user/game actions related to the game board (View)
 */
public class GameBoardView extends View {

    private Presenter presenter;
    private DrawController drawer;
    private String message;
    private boolean isGameOver;

    public GameBoardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        // Set Colors
        TypedArray colors = context.obtainStyledAttributes(attributeSet, R.styleable.GameBoardView);

        int[] colorArray = new int[4];
        colorArray[0] = colors.getInt(R.styleable.GameBoardView_backgroundColor, Color.GRAY);
        colorArray[1] = colors.getColor(R.styleable.GameBoardView_ballColor, Color.BLUE);
        colorArray[2] = colors.getInt(R.styleable.GameBoardView_brickColor, Color.RED);
        colorArray[3] = colors.getInt(R.styleable.GameBoardView_paddleColor, Color.GREEN);
        colors.recycle();

        drawer = new DrawController();
        drawer.setColors(colorArray);

    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDraw(Canvas canvas) {

        if (presenter == null) {
            return;
        }

        if (isGameOver) {
            drawer.drawGameOverScreen(canvas, message);
            return;
        }
        drawer.drawGameObjects(canvas);
        drawer.drawGameScore(canvas);

        presenter.doGameActions();

    }

    public void drawGameOver(String message) {
        isGameOver = true;
        this.message = message;
        invalidate();
    }

    public void redraw(ModelDisplayable model) {
        isGameOver = false;
        drawer.setModel(model);
        invalidate();
    }

}