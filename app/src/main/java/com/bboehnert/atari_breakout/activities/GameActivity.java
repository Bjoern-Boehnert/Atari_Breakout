package com.bboehnert.atari_breakout.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bboehnert.atari_breakout.GamefieldView;
import com.bboehnert.atari_breakout.R;

public class GameActivity extends AppCompatActivity {

    private GamefieldView gamefield;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.gamefield = findViewById(R.id.gamefield);

    }

    public void restartGame(View view) {
        this.gamefield.startGame();
    }

    public void returnButtonPressed(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}
