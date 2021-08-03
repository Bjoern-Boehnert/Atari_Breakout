package com.bboehnert.atari_breakout.entites;


public class Paddle extends GameEntity {

    public Paddle(float x, float y, float width, float height, int colorCode) {
        super(x, y, width, height, colorCode);
    }

    public void move(float x) {

        this.x = x;
        getRectangle().set(x, this.y,
                x + getWidth(),
                this.y + getHeight());
    }

    public float getReflectFactor(float xPos) {
        // Get the percentage of the hit x-position of the ball
        float reflectionPercentage = (xPos - getX()) / getWidth();

        // Avoid negative values
        if (reflectionPercentage < 0) {
            reflectionPercentage = 0;
        }

        System.out.println("Paddle: " + reflectionPercentage);
        return reflectionPercentage;
    }
}
