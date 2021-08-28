package com.bboehnert.atari_breakout.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.bboehnert.atari_breakout.ConcretePresenter;
import com.bboehnert.atari_breakout.DrawController;
import com.bboehnert.atari_breakout.view.GameBoardView;
import com.bboehnert.atari_breakout.mvp.Model;
import com.bboehnert.atari_breakout.mvp.Presenter;
import com.bboehnert.atari_breakout.R;
import com.bboehnert.atari_breakout.SoundController;
import com.bboehnert.atari_breakout.entites.GameBoard;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Class for handling the Game interactions (View)
 */
public class GameActivity extends AppCompatActivity implements com.bboehnert.atari_breakout.mvp.View {

    private Presenter presenter;

    // View sub component for showing the game board
    private GameBoardView gameBoardView;

    // Controller
    private DrawController drawer;
    private SoundController sound;

    private Model board;


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        // Init size, when GameBoard is
        if (board == null) {
            board = new GameBoard(
                    gameBoardView.getWidth(),
                    gameBoardView.getHeight());

            drawer = new DrawController();
            drawer.setColors(gameBoardView.getComponentsColors());
            gameBoardView.setDrawer(drawer);

            presenter = new ConcretePresenter(this, board);
            this.gameBoardView.setPresenter(presenter);

            //presenter.bindDrawerToModel(drawer);

            sound = new SoundController();
            sound.initAudio(this);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.gameBoardView = findViewById(R.id.gameBoard);
    }

    /**
     * Button Click Event for restarting the game
     * Using the underlying Presenter from the MVP Pattern
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
    public void updateViewComponent(Model.Drawer model) {
        gameBoardView.redraw(model);
    }

    @Override
    public void playPaddleCollision() {
        sound.playPaddleCollision();
    }

    @Override
    public void playBrickCollision() {
        sound.playBrickCollision();
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

    @Override
    public void drawGameOverScreen(String message) {
        gameBoardView.drawGameOver(message);
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
