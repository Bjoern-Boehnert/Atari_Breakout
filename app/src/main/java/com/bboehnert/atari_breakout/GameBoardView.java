package com.bboehnert.atari_breakout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.bboehnert.atari_breakout.entites.Brick;
import com.bboehnert.atari_breakout.entites.DrawManager;
import com.bboehnert.atari_breakout.entites.GameBoard;
import com.bboehnert.atari_breakout.entites.Redrawable;

public class GameBoardView extends View implements Redrawable {

    private String gameOverMessage = "Klick On Restart!";
    private DrawManager drawManager;

    public GameBoardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        drawManager = new DrawManager(context, attributeSet, this);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        drawManager.init(right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawManager.draw(canvas);

        if (!drawManager.getGameBoard().isGameStarted()) {
            drawManager.drawGameOver(canvas, this.gameOverMessage);
            return;
        }

        drawManager.updateGameState();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        drawManager.getGameBoard().movePaddle(e.getX());
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

    public void startGame() {
        drawManager.getGameBoard().restartGame();
        invalidate();
    }
}