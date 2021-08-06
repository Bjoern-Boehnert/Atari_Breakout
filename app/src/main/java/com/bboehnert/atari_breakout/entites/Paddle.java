package com.bboehnert.atari_breakout.entites;

/**
 * Class for representing the paddle entity
 */
public class Paddle extends GameEntity {

    /**
     * Constructor
     *
     * @param x      of origin X-coordinate
     * @param y      of origin Y-coordinate
     * @param width  of the entity
     * @param height of the entity
     */
    public Paddle(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    /**
     * Move the paddle to a new X-coordinate
     *
     * @param x is the new X-coordinate
     */
    public void move(float x) {
        this.x = x;
    }

    /**
     * Get the factor on the relative position of the balls X-coordinate
     *
     * @param xPos is the X-coordinate
     * @return a factor between 0.0 - 1.0 to describe the position
     */
    public float getReflectFactor(float xPos) {
        // Get the percentage of the hit x-position of the ball
        float reflectionPercentage = (xPos - x) / getWidth();

        // Avoid negative values
        if (reflectionPercentage < 0) {
            reflectionPercentage = 0;
        }
        return reflectionPercentage;
    }
}
