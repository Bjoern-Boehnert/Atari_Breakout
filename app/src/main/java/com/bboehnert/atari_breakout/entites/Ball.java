package com.bboehnert.atari_breakout.entites;

import java.util.Random;

public class Ball extends GameEntity {

    public static final int STEP_SIZE = 10;
    private double dx, dy, speed;

    public Ball(float x, float y, float size, int colorCode) {
        super(x, y, size, size, colorCode);

        Random entryDirection = new Random();
        int val = entryDirection.nextInt(360 - 0 + 1) + 0;
        this.dx = Math.sin(val);
        this.dy = Math.cos(val);
        this.speed = 1;

    }

    public void move() {
        this.x += STEP_SIZE * dx * speed;
        this.y += STEP_SIZE * dy;

        getRectangle().set(x, y,
                x + getWidth(),
                y + getWidth());
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
