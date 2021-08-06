package com.bboehnert.atari_breakout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.bboehnert.atari_breakout.entites.GameBoard;
import com.bboehnert.atari_breakout.entites.Redrawable;

import androidx.annotation.NonNull;

public class DrawManager {
    private final Context context;
    private final Redrawable redrawable;
    private final AttributeSet attributeSet;
    private DrawController controller;
    private GameBoard gameBoard;

    public DrawManager(@NonNull Context context, AttributeSet attributeSet, Redrawable redrawable) {
        this.context = context;
        this.attributeSet = attributeSet;
        this.redrawable = redrawable;
    }

    public void init(int width, int height) {
        this.gameBoard = new GameBoard(redrawable, width, height);
        gameBoard.initComponents();
        controller = new DrawController(context, attributeSet, gameBoard);
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void updateGameState() {
        controller.updateGameState();
    }

    public void draw(@NonNull Canvas canvas) {
        controller.draw(canvas, false);
    }

    public void drawGameOver(@NonNull Canvas canvas, String message) {
        controller.setGameOver(true, message);
        controller.draw(canvas, true);
    }

}
