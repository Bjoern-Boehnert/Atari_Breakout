package com.bboehnert.atari_breakout;

import android.graphics.Canvas;

import com.bboehnert.atari_breakout.entites.GameBoard;

public class Presenter implements Contract.Presenter,
        Contract.Model.AudioListener,
        Contract.Model.DrawListener {

    private Contract.View view;
    private Contract.Model model;

    // Considering moving the controllers to the view-layer
    private DrawController drawer;
    private SoundController sound;

    public Presenter(Contract.View view, Contract.Model model) {
        this.view = view;
        this.model = model;

        model.initComponents(this);

        drawer = new DrawController();
        drawer.setModel((Contract.Model.Drawer) model);
        drawer.setColors(view.getComponentsColors());

        sound = new SoundController();
    }

    @Override
    public void restartButtonPressed() {
        //sound.release();
        //sound.initAudio(this);

        model.restartGame();
        model.initComponents(this);
    }

    @Override
    public void touchedPaddle(float x) {
        model.movePaddle(x, this);
    }

    @Override
    public void playPaddleCollision() {
        //sound.playPaddleCollision();
    }

    @Override
    public void playBrickCollision() {
        //sound.playBrickCollision();
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
