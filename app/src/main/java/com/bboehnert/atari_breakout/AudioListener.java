package com.bboehnert.atari_breakout;

public interface AudioListener {

    /**
     * play sound of paddle collision
     */
    void playPaddleCollision();

    /**
     * play sound of brick collision
     */
    void playBrickCollision();
}
