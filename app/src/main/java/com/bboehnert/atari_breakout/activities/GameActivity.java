package com.bboehnert.atari_breakout.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bboehnert.atari_breakout.GameBoardView;
import com.bboehnert.atari_breakout.R;

public class GameActivity extends AppCompatActivity {

    private GameBoardView gameBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.gameBoard = findViewById(R.id.gameBoard);

    }

    public void restartGameButtonPressed(View view) {
        this.gameBoard.startGame();
    }

    public void returnButtonPressed(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}
