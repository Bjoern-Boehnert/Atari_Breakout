package com.bboehnert.atari_breakout;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Controller for handling all sound events for the game
 */
public final class SoundController implements Contract.Model.AudioListener {
    private MediaPlayer destroyedBrick, paddleHit;

    /**
     * Init the Audio Player
     */
    public void initAudio(Context context) {
        destroyedBrick = MediaPlayer.create(context, R.raw.brickdestroyed);
        paddleHit = MediaPlayer.create(context, R.raw.paddlehit);
    }

    /**
     * Releases the Audio Player
     */
    public void release() {
        if (destroyedBrick != null && paddleHit != null) {
            destroyedBrick.release();
            paddleHit.release();
        }

    }

    @Override
    public void playPaddleCollision() {
        paddleHit.start();
    }

    @Override
    public void playBrickCollision() {
        destroyedBrick.start();
    }

}
