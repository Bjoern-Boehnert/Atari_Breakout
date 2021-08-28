package com.bboehnert.atari_breakout;

import com.bboehnert.atari_breakout.entites.GameBoard;
import com.bboehnert.atari_breakout.mvp.Model;
import com.bboehnert.atari_breakout.mvp.ModelDisplayable;
import com.bboehnert.atari_breakout.mvp.Presenter;
import com.bboehnert.atari_breakout.mvp.View;

/**
 * Concrete Presenter Implementation of the Presenter in the MVP Pattern. It shall only manage
 * the view and model communication.
 * <p>
 * Though necessary is that the drawer has to be bound to the model here for displaying the game
 */
public class ConcretePresenter implements Presenter,
        Model.AudioListener,
        Model.DrawListener {

    private final View view;
    private final Model model;

    public ConcretePresenter(View view, Model model) {
        this.view = view;
        this.model = model;
        model.initComponents(this);
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
        view.updateViewComponent((ModelDisplayable) model);

        if (!model.isGameStarted()) {
            view.drawGameOverScreen(getMessage());
        }
    }

    @Override
    public void doGameActions() {
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
