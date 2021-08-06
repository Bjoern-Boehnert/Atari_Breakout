package com.bboehnert.atari_breakout.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bboehnert.atari_breakout.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Class for handling the Main Menu
 */
public class MainMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
    }

    /**
     * Button Click Event for switching to the game
     *
     * @param view is the View
     */
    public void onStartGameButtonClick(View view) {
        Intent intentMain = new Intent(this, GameActivity.class);
        startActivity(intentMain);
    }

}
