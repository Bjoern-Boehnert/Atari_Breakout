package com.bboehnert.atari_breakout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.bboehnert.atari_breakout.entites.GameBoard;

/**
 * Class for handling user/game actions related to the game board
 */
public class GameBoardView extends View implements DrawListener {

    private final TypedArray colors;
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
        DrawController.setColors(colors);
    }

    /**
     * Init the board and drawer
     *
     * @param board is the game board
     */
    public void initBoard(GameBoard board) {
        this.board = board;
        this.board.setDrawer(this);
        this.board.initComponents();
        DrawController.setBoard(board);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (DrawController.getBoard() == null) {
            return;
        }

        // Show Game Over
        if (!board.isGameStarted()) {
            DrawController.drawGameOverScreen(canvas, this.message);
            return;
        }

        DrawController.drawGameObjects(canvas);
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
    public void redraw() {
        invalidate();
    }

}