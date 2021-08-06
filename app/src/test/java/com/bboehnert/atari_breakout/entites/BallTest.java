package com.bboehnert.atari_breakout.entites;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the methods of the Ball Class
 */
public class BallTest {

    private Ball ball;
    private final double delta = 1e-5;

    @Before
    public void setUp() throws Exception {
        ball = new Ball(0, 0, 50);
    }

    /**
     * Tests the balls basic movement by coordinates
     */
    @Test
    public void ballMovingTest() {
        Assert.assertEquals(0, ball.getX(), delta);
        Assert.assertEquals(0, ball.getY(), delta);
        Assert.assertEquals(50, ball.getWidth(), delta);
        Assert.assertEquals(50, ball.getHeight(), delta);

        ball.move();
        // Ball move per Step sizes
        Assert.assertEquals(Ball.STEP_SIZE, ball.getX(), delta);
        Assert.assertEquals(Ball.STEP_SIZE, ball.getY(), delta);

        ball.move();
        // Ball move per two Step sizes
        Assert.assertEquals(2 * Ball.STEP_SIZE, ball.getX(), delta);
        Assert.assertEquals(2 * Ball.STEP_SIZE, ball.getY(), delta);
    }

    /**
     * Tests the change in reflection of the ball
     */
    @Test
    public void ballReflectionChangeTest() {
        Assert.assertEquals(1, ball.dx, delta);
        Assert.assertEquals(1, ball.dy, delta);

        // Reflect Y direction
        ball.reflectX();
        Assert.assertEquals(-1, ball.dx, delta);
        Assert.assertEquals(1, ball.dy, delta);
        ball.reflectY();
        Assert.assertEquals(-1, ball.dx, delta);
        Assert.assertEquals(-1, ball.dy, delta);
        ball.reflectX();
        Assert.assertEquals(1, ball.dx, delta);
        Assert.assertEquals(-1, ball.dy, delta);
        ball.reflectY();
        Assert.assertEquals(1, ball.dx, delta);
        Assert.assertEquals(1, ball.dy, delta);
    }

    /**
     * Tests the affected move of the ball reflection
     */
    @Test
    public void ballReflectionMoveTest() {
        ball.reflectX();
        ball.move();
        Assert.assertEquals(-Ball.STEP_SIZE, ball.getX(), delta);
        Assert.assertEquals(Ball.STEP_SIZE, ball.getY(), delta);
        ball.reflectY();
        ball.move();
        Assert.assertEquals(2 * -Ball.STEP_SIZE, ball.getX(), delta);
        Assert.assertEquals(0, ball.getY(), delta);
        ball.reflectX();
        ball.move();
        Assert.assertEquals(-Ball.STEP_SIZE, ball.getX(), delta);
        Assert.assertEquals(-Ball.STEP_SIZE, ball.getY(), delta);
        ball.reflectY();
        ball.move();
        Assert.assertEquals(0, ball.getX(), delta);
        Assert.assertEquals(0, ball.getY(), delta);
    }

    /**
     * Tests the intersecting of two game entities
     */
    @Test
    public void ballIntersectingTest() {

        Assert.assertTrue(ball.isIntersecting(new Paddle(0, 0, 50, 50)));
        Assert.assertTrue(ball.isIntersecting(new Paddle(25, 25, 50, 50)));
        Assert.assertTrue(ball.isIntersecting(new Paddle(49, 49, 50, 50)));
        Assert.assertTrue(ball.isIntersecting(new Paddle(-49, 0, 50, 50)));
        Assert.assertTrue(ball.isIntersecting(new Paddle(0, -49, 50, 50)));

        Paddle intersecting = new Paddle(-49, -49, 50, 50);
        Assert.assertTrue(ball.isIntersecting(intersecting));
        Assert.assertTrue(intersecting.isIntersecting(ball));

        // No Intersection - Only touch by corner
        Assert.assertFalse(ball.isIntersecting(new Paddle(-50, -50, 50, 50)));
        Assert.assertFalse(ball.isIntersecting(new Paddle(50, 50, 50, 50)));
    }

    /**
     * Tests the reflection of the paddle on the ball
     */
    @Test
    public void paddleReflectionTest() {
        ball.reflectByPaddle(0);
        Assert.assertEquals(-1, ball.dx, delta);
        Assert.assertEquals(1, ball.speed, delta);
        ball.reflectByPaddle(0.5F);
        Assert.assertEquals(1, ball.dx, delta);
        Assert.assertEquals(0, ball.speed, delta);
        ball.reflectByPaddle(1);
        Assert.assertEquals(1, ball.dx, delta);
        Assert.assertEquals(1, ball.speed, delta);
        ball.move();
        Assert.assertEquals(Ball.STEP_SIZE * ball.dx * ball.speed, ball.getX(), delta);

    }
}