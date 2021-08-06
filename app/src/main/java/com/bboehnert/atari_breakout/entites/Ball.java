package com.bboehnert.atari_breakout.entites;

/**
 * Class for representing the ball entity
 */
public class Ball extends GameEntity {

    public static final int STEP_SIZE = 10;
    double dx, dy, speed;

    /**
     * Constructor
     *
     * @param x    of origin X-coordinate
     * @param y    of origin Y-coordinate
     * @param size of the entity
     */
    public Ball(float x, float y, float size) {
        super(x, y, size, size);
        this.dx = 1;
        this.dy = 1;
        this.speed = 1;
    }

    /**
     * Move the ball depending on the step size of the ball in the current XY-direction
     * The Speed attribute is the reflecting factor of the paddle, which is calculated in Paddle
     *
     * @see Paddle
     */
    public void move() {
        this.x += STEP_SIZE * dx * speed;
        this.y += STEP_SIZE * dy;
    }

    /**
     * Mirror the direction of the X-coordinate to bounce of on bounds of the game board
     *
     * @see GameBoard
     */
    public void reflectX() {
        dx *= -1;
    }

    /**
     * Mirror the direction of the Y-coordinate to bounce of on bounds of the game board
     *
     * @see GameBoard
     */
    public void reflectY() {
        dy *= -1;
    }

    /**
     * Calculate a smooth bounce from the paddles hit location by using the cosinus-function
     *
     * @param factor of the proportion of the paddles X-Coordinate
     * @see Paddle
     */
    public void reflectByPaddle(float factor) {
        double value = Math.cos(factor * Math.PI);
        if (value <= 0) {
            value = value * -1;
        }
        this.speed = value;

        //Change reflecting direction on paddle
        if (factor >= 0.5) {
            this.dx = 1;
        } else {
            this.dx = -1;
        }

    }

}
