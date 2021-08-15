package com.bboehnert.atari_breakout.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.bboehnert.atari_breakout.GameBoardView;
import com.bboehnert.atari_breakout.R;
import com.bboehnert.atari_breakout.SoundController;
import com.bboehnert.atari_breakout.entites.GameBoard;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Class for handling the Game interactions
 */
public class GameActivity extends AppCompatActivity {

    private GameBoardView view;
    private GameBoard board;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.view = findViewById(R.id.gameBoard);

    }

    /**
     * Button Click Event for restarting the game
     * Starting the game and initiate drawing
     *
     * @param view is the View
     */
    public void restartGameButtonPressed(View view) {
        SoundController.release();
        SoundController.initAudio(this);
        board.restartGame();
    }

    /**
     * Button Click Event for returning to the main menu
     *
     * @param view is the View
     */
    public void returnButtonPressed(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        board.movePaddle(e.getX());
        return true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (board == null) {
            this.board = new GameBoard();
            this.board.setHeight(view.getHeight());
            this.board.setWidth(view.getWidth());
            this.view.initBoard(board);

            this.board.setAudioPlayer(new SoundController());

        }
    }
}
