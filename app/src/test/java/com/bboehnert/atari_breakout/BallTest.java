package com.bboehnert.atari_breakout;

import android.graphics.RectF;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Tests the methods of the Ball Class
 */
@RunWith(MockitoJUnitRunner.class)
public class BallTest {
    @Test
    public void ballReflectingTest() {
/*
        Ball ball = new Ball(0, 0, 0, Color.BLUE);
        ball.reflectByPaddle(0.5F);
        ball.move();

        final double delta = 1e-15;
        //  float expected = Ball.STEP_SIZE + reflectingValue;


        Assert.assertEquals(1.12345678910, 1.12345678910, delta);*/


        RectF d = new RectF();
        RectF f = new RectF();
        d.intersect(f);

    }
}