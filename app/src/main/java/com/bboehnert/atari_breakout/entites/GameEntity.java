package com.bboehnert.atari_breakout.entites;

import android.graphics.Paint;
import android.graphics.RectF;

import androidx.annotation.ColorInt;

abstract class GameEntity {
    protected float x, y;
    private final float width, height;
    private final RectF rectangle;

    public GameEntity(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.rectangle = new RectF(x, y,
                x + width,
                y + height);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public RectF getRectangle() {
        return rectangle;
    }
}
