package com.bboehnert.atari_breakout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.bboehnert.atari_breakout.entites.GameBoard;
import com.bboehnert.atari_breakout.entites.Redrawable;

/**
 * Class for handling user/game actions related to the game board
 */
public class GameBoardView extends View implements Redrawable {

    private String gameOverMessage = "Neustart klicken!";
    private DrawController drawController;
    private GameBoard gameBoard;
    private final TypedArray colors;

    /**
     * Constructor
     *
     * @param context      of the app
     * @param attributeSet for getting the component custom colors
     */
    public GameBoardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.colors = context.obtainStyledAttributes(attributeSet, R.styleable.GameBoardView);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        // Init game board width and height
        if (this.gameBoard == null) {
            this.gameBoard = new GameBoard(this, right, bottom);
            gameBoard.initComponents();
            drawController = new DrawController(colors, gameBoard);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawController.drawGameObjects(canvas);

        if (!gameBoard.isGameStarted()) {
            drawController.drawGameOverScreen(canvas, this.gameOverMessage);
            return;
        }
        drawController.processAction(gameBoard.getCurrentAction());
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        gameBoard.movePaddle(e.getX());
        return true;
    }

    @Override
    public void redraw() {
        invalidate();
    }

    @Override
    public void drawGameOver(String message) {
        gameOverMessage = message;
        invalidate();
    }

    /**
     * Starting the game and initiate drawing
     */
    public void startGame() {
        gameBoard.restartGame();
        invalidate();
    }
}