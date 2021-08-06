package com.bboehnert.atari_breakout.entites;

public class Ball extends GameEntity {

    public static final int STEP_SIZE = 10;
    private double dx, dy, speed;

    public Ball(float x, float y, float size) {
        super(x, y, size, size);
        this.dx = 1;
        this.dy = 1;
        this.speed = 1;

    }

    public void move() {
        this.x += STEP_SIZE * dx * speed;
        this.y += STEP_SIZE * dy;
    }

    public void reflectX() {
        dx *= -1;
    }

    public void reflectY() {
        dy *= -1;
    }

    public void reflectByPaddle(float percentage) {
        double value = Math.cos(percentage * Math.PI);
        if (value <= 0) {
            value = value * -1;
        }
        this.speed = value;

        //Change reflecting direction on paddle
        if (percentage >= 0.5) {
            this.dx = 1;
        } else {
            this.dx = -1;
        }

    }

}
