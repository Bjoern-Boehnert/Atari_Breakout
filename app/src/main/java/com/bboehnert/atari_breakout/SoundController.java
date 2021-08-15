package com.bboehnert.atari_breakout;

import android.content.Context;
import android.media.MediaPlayer;

import com.bboehnert.atari_breakout.entites.GameBoard;

/**
 * Controller for handling all sound events for the game
 */
public class SoundController implements AudioListener {
    private MediaPlayer destroyedBrick, paddleHit;
    private Context context;

    /**
     * Constructor
     *
     * @param context of the game
     */
    public SoundController(Context context) {
        this.context = context;
    }

    /**
     * Register the audio to the board
     *
     * @param board of the game
     */
    public void register(GameBoard board) {
        board.setAudioPlayer(this);
    }

    /**
     * Init the Audio Player
     */
    public void initAudio() {
        destroyedBrick = MediaPlayer.create(context, R.raw.brickdestroyed);
        paddleHit = MediaPlayer.create(context, R.raw.paddlehit);
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
