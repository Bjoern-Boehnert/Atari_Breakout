package com.bboehnert.atari_breakout.entites;

abstract class GameEntity {
    protected float x, y;
    private final float width, height;

    public GameEntity(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    public boolean isIntersecting(GameEntity other) {
        return x < other.x + other.width && other.x < width + x
                && y < other.height + other.y && other.y < height + y;
    }

}
