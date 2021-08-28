package com.bboehnert.atari_breakout;

import android.graphics.Canvas;

import com.bboehnert.atari_breakout.entites.GameBoard;
import com.bboehnert.atari_breakout.mvp.Model;
import com.bboehnert.atari_breakout.mvp.View;

/**
 * Concrete Presenter Implementation of the Presenter in the MVP Pattern. It shall only manage
 * the view and model communication.
 *
 * Though necessary is that the drawer has to be bound to the model here for displaying the game
 *
 */
public class ConcretePresenter implements com.bboehnert.atari_breakout.mvp.Presenter,
        Model.AudioListener,
        Model.DrawListener {

    private View view;
    private Model model;

    private DrawController drawer;

    public ConcretePresenter(View view, Model model) {
        this.view = view;
        this.model = model;
        model.initComponents(this);
    }

    @Override
    public void bindDrawerToModel(DrawController drawer) {
        if(drawer != null){
            drawer.setModel((Model.Drawer) model);
            this.drawer=drawer;
        }
    }


    @Override
    public void restartButtonPressed() {
        model.restartGame();
        model.initComponents(this);
    }

    @Override
    public void touchedPaddle(float x) {
        model.movePaddle(x, this);
    }

    @Override
    public void playPaddleCollision() {
        view.playPaddleCollision();
    }

    @Override
    public void playBrickCollision() {
        view.playBrickCollision();
    }

    @Override
    public void redraw() {
        // Trigger next drawing
        view.updateViewComponent();
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (drawer == null || drawer.getModel() == null) {
            return;
        }

        if (!model.isGameStarted()) {
            drawer.drawGameOverScreen(canvas, getMessage());
            return;
        }

        drawer.drawGameObjects(canvas);
        drawer.drawGameScore(canvas);

        //Draw next Frame
        model.doGameAction(this, this);
    }

    private String getMessage() {
        if (model.getState() == GameBoard.GameState.Won) {
            return view.getWinMessage();

        } else if (model.getState() == GameBoard.GameState.Lost) {
            return view.getLostMessage();

        } else if (model.getState() == GameBoard.GameState.Undecided) {
            return view.getUndecidedMessage();
        }
        return null;
    }

}
