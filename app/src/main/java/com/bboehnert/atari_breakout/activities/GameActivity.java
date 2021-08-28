package com.bboehnert.atari_breakout.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.bboehnert.atari_breakout.Contract;
import com.bboehnert.atari_breakout.GameBoardView;
import com.bboehnert.atari_breakout.Presenter;
import com.bboehnert.atari_breakout.R;
import com.bboehnert.atari_breakout.entites.GameBoard;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Class for handling the Game interactions
 */
public class GameActivity extends AppCompatActivity implements Contract.View {

    // View sub component for showing the game board
    private GameBoardView gameBoardView;
    private Contract.Model board;

    private Contract.Presenter presenter;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        // Init size, when GameBoard is
        if (board == null) {
            board = new GameBoard(
                    gameBoardView.getWidth(),
                    gameBoardView.getHeight());

            presenter = new Presenter(this, board);
            this.gameBoardView.setPresenter(presenter);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.gameBoardView = findViewById(R.id.gameBoard);
    }

    /**
     * Button Click Event fo    r restarting the game
     * Starting the game and initiate drawing
     *
     * @param view is the View
     */
    public void restartGameButtonPressed(View view) {
        presenter.restartButtonPressed();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        presenter.touchedPaddle(e.getX());
        return true;
    }

    @Override
    public void updateViewComponent() {
        gameBoardView.redraw();
    }

    @Override
    public int[] getComponentsColors() {
        return gameBoardView.getComponentsColors();
    }

    @Override
    public String getUndecidedMessage() {
        return getResources().getString(R.string.gameNotStartedMessage);
    }

    @Override
    public String getWinMessage() {
        return getResources().getString(R.string.gameWonMessage);
    }

    @Override
    public String getLostMessage() {
        return getResources().getString(R.string.gameLostMessage);
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

}
