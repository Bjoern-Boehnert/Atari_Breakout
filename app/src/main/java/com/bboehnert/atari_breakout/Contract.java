package com.bboehnert.atari_breakout;

import android.graphics.Canvas;

import com.bboehnert.atari_breakout.entites.Ball;
import com.bboehnert.atari_breakout.entites.Brick;
import com.bboehnert.atari_breakout.entites.GameBoard;
import com.bboehnert.atari_breakout.entites.Paddle;

public interface Contract {
    interface Model {
        void restartGame();

        void movePaddle(float x, DrawListener drawer);

        void doGameAction(DrawListener drawListener,
                          AudioListener audioListener);

        void initComponents(DrawListener drawer);

        boolean isGameStarted();
        GameBoard.GameState getState();

        interface Drawer {
            Ball getBall();

            Brick[] getBricks();

            Paddle getPaddle();

            float getWidth();

            float getHeight();

            int getGameScore();
        }

        interface AudioListener {

            void playPaddleCollision();

            void playBrickCollision();
        }

        interface DrawListener {
            void redraw();
        }
    }

    interface Presenter {
        void restartButtonPressed();

        void touchedPaddle(float x);

        void onDraw(Canvas canvas);
    }

    interface View {
        void updateViewComponent();

        int[] getComponentsColors();

        String getUndecidedMessage();

        String getWinMessage();

        String getLostMessage();
    }
}
