package com.bboehnert.atari_breakout.mvp;

import android.graphics.Canvas;

import com.bboehnert.atari_breakout.DrawController;

/**
 * Representing the Presenter of the MVP Pattern. It shall cover user interactions effecting
 * the game
 */
public interface Presenter {

    /**
     * Action on restart Button press
     */
    void restartButtonPressed();

    /**
     * Action on touching the paddle
     *
     * @param x is the location on the x-axis
     */
    void touchedPaddle(float x);

    /**
     * Action on getting the view canvas for drawing on it
     *
     * @param canvas is the drawing surface
     */
    void onDraw(Canvas canvas);

    /**
     * Action on binding the Drawer class to the Model. So the Drawer instance know the actual
     * model data
     *
     * @param drawer to bind the model for
     */
    void bindDrawerToModel(DrawController drawer);
}
