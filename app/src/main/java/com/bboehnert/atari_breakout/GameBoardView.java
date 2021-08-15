package com.bboehnert.atari_breakout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.bboehnert.atari_breakout.entites.GameBoard;

import java.util.Observable;
import java.util.Observer;

/**
 * Class for handling user/game actions related to the game board
 */
public class GameBoardView extends View implements Observer {

    private final TypedArray colors;
    private final DrawController drawController;
    private GameBoard board;
    private String message;

    /**
     * Constructor
     *
     * @param context      of the app
     * @param attributeSet for getting the component custom colors
     */
    public GameBoardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.message = getResources().getString(R.string.gameNotStartedMessage);
        this.colors = context.obtainStyledAttributes(attributeSet, R.styleable.GameBoardView);
        this.drawController = new DrawController(colors);
    }

    /**
     * Init the board and drawer
     *
     * @param board is the game board
     */
    public void initBoard(GameBoard board) {
        this.board = board;
        this.board.addObserver(this);
        this.board.initComponents();
        this.drawController.setBoard(board);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (drawController.getBoard() == null) {
            return;
        }

        if (!board.isGameStarted()) {
            drawController.drawGameOverScreen(canvas, this.message);
            return;
        }

        drawController.drawGameObjects(canvas);
        GameBoard.GameAction action = board.getCurrentAction();
        board.processAction(action);
        this.message = getMessage(action);

    }

    /**
     * Get the text of the action
     *
     * @param action is the current game board action
     */
    private String getMessage(GameBoard.GameAction action) {
        if (action == GameBoard.GameAction.GameWin) {
            return getResources().getString(R.string.gameWonMessage);
        } else if (action == GameBoard.GameAction.GameLose) {
            return getResources().getString(R.string.gameLostMessage);
        }
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        invalidate();
    }

}