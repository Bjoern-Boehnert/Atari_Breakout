package com.bboehnert.atari_breakout.entites;

import android.graphics.Paint;
import android.graphics.RectF;

abstract class GameEntity {
    protected float x, y;
    private final float width, height;
    private final Paint paint;
    private final RectF rectangle;

    public GameEntity(float x, float y, float width, float height, int colorCode) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.paint = new Paint();
        this.paint.setColor(colorCode);

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

    public Paint getPaint() {
        return paint;
    }

    public RectF getRectangle() {
        return rectangle;
    }
}
