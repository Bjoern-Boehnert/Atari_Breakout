package com.bboehnert.atari_breakout.mvp;

import android.graphics.Canvas;

import com.bboehnert.atari_breakout.DrawController;
import com.bboehnert.atari_breakout.SoundController;

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

    void doGameActions();
}
