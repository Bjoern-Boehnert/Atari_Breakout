package com.bboehnert.atari_breakout.entites;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.bboehnert.atari_breakout.DrawController;

import androidx.annotation.NonNull;

public class DrawManager {
    private DrawController controller;
    private GameBoard gameBoard;


    public DrawManager(@NonNull Context context, AttributeSet attributeSet, Redrawable redrawable) {
        this.gameBoard = new GameBoard(redrawable);
        gameBoard.initComponents();
        controller = new DrawController(context, attributeSet, gameBoard);
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void updateGameState() {
        controller.redrawGameState();
    }

    public void draw(@NonNull Canvas canvas) {
        controller.draw(canvas, false);
    }

    public void drawGameOver(@NonNull Canvas canvas, String message) {
        controller.setGameOver(true, message);
        controller.draw(canvas, true);
    }

}
