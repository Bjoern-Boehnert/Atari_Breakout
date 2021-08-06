package com.bboehnert.atari_breakout.entites;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the methods of the Paddle Class
 */
public class PaddleTest {

    private Paddle paddle;
    private final double delta = 1e-5;

    @Before
    public void setUp() throws Exception {
        paddle = new Paddle(0, 0, 100, 50);
    }

    /**
     * Tests the paddle movement
     */
    @Test
    public void paddleMovingTest() {
        Assert.assertEquals(0, paddle.getX(), delta);
        Assert.assertEquals(0, paddle.getY(), delta);
        Assert.assertEquals(100, paddle.getWidth(), delta);
        Assert.assertEquals(50, paddle.getHeight(), delta);
        paddle.move(-10);
        Assert.assertEquals(-10, paddle.getX(), delta);
    }

    /**
     * Tests the hit location of the ball
     */
    @Test
    public void paddleHitFactorTest() {
        Assert.assertEquals(0.5, paddle.getReflectFactor(50), delta);
        Assert.assertEquals(1, paddle.getReflectFactor(100), delta);
        Assert.assertEquals(0, paddle.getReflectFactor(-40), delta);
    }
}