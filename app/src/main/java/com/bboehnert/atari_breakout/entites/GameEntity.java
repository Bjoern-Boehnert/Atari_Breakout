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

        if (getX() + getWidth() >= other.getX() &&
                getY() + getHeight() >= other.getY()) {

            return true;

        } else if (getX() + getWidth() >= other.getX() &&
                getY() + getHeight() >= other.getY() + other.getHeight()) {

            return true;
        }
        return false;


    }

}
