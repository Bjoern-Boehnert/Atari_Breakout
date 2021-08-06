package com.bboehnert.atari_breakout.entites;


public class Paddle extends GameEntity {

    public Paddle(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public void move(float x) {
        this.x = x;

    }

    public float getReflectFactor(float xPos) {
        // Get the percentage of the hit x-position of the ball
        float reflectionPercentage = (xPos - getX()) / getWidth();

        // Avoid negative values
        if (reflectionPercentage < 0) {
            reflectionPercentage = 0;
        }
        return reflectionPercentage;
    }
}
