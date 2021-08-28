package com.bboehnert.atari_breakout.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import com.bboehnert.atari_breakout.DrawController;
import com.bboehnert.atari_breakout.mvp.Model;
import com.bboehnert.atari_breakout.mvp.Presenter;
import com.bboehnert.atari_breakout.R;

/**
 * Class for handling user/game actions related to the game board (View)
 */
public class GameBoardView extends View {

    private int[] colorArray;

    private Presenter presenter;
    private DrawController drawer;
    private String message;
    private boolean isGameOver;

    public GameBoardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        // Set Colors
        TypedArray colors = context.obtainStyledAttributes(attributeSet, R.styleable.GameBoardView);
        int backgroundColor = colors.getInt(R.styleable.GameBoardView_backgroundColor, Color.GRAY);
        int ballColor = colors.getColor(R.styleable.GameBoardView_ballColor, Color.BLUE);
        int brickColor = colors.getInt(R.styleable.GameBoardView_brickColor, Color.RED);
        int paddleColor = colors.getInt(R.styleable.GameBoardView_paddleColor, Color.GREEN);

        colorArray = new int[4];
        colorArray[0] = backgroundColor;
        colorArray[1] = ballColor;
        colorArray[2] = brickColor;
        colorArray[3] = paddleColor;

        colors.recycle();
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDraw(Canvas canvas) {

        if (presenter == null || drawer == null || drawer.getModel() == null) {
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

    public int[] getComponentsColors() {
        return this.colorArray;
    }

    public void redraw(Model.Drawer model) {
        isGameOver = false;
        drawer.setModel(model);

        invalidate();
    }

    public void setDrawer(DrawController drawer) {
        this.drawer = drawer;
    }

}