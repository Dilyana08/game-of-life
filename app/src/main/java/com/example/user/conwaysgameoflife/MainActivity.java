package com.example.user.conwaysgameoflife;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.conwaysgameoflife.utils.ColorUtils;

import static com.example.user.conwaysgameoflife.utils.ButtonUtils.setActiveButtonColours;
import static com.example.user.conwaysgameoflife.utils.ButtonUtils.setDefaultButtonColours;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = getSharedPreferences("settings", Context.MODE_PRIVATE);
        setCurrentSettings();

        final Button playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(this::goToPlay);

        final Button settingsButton = findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(this::goToSettings);

        final Button quitButton = findViewById(R.id.quit_button);
        quitButton.setOnClickListener(this::quitApp);
    }


    @Override
    protected void onResume() {
        super.onResume();
        setCurrentSettings();
        setDefaultButtonColours(findViewById(R.id.play_button), getResources(), settings);
        setDefaultButtonColours(findViewById(R.id.settings_button), getResources(), settings);
        setDefaultButtonColours(findViewById(R.id.quit_button), getResources(), settings);
    }

    private void setCurrentSettings() {
        changeColours();
    }

    private void changeColours() {
        int colorId = ColorUtils.getColorId(settings);

        TextView gameName = findViewById(R.id.gameName);
        gameName.setTextColor(getResources().getColor(colorId));

        ImageView logo = findViewById(R.id.logo);
        ColorUtils.setNewLogo(settings, logo);
    }

    private void goToPlay(@SuppressWarnings("unused") final View ignored) {
        setActiveButtonColours(findViewById(R.id.play_button), getResources(), settings);
        Intent intent = new Intent(this, PlayActivity.class);
        this.startActivity(intent);
    }

    private void goToSettings(@SuppressWarnings("unused") final View view) {
        setActiveButtonColours(findViewById(R.id.settings_button), getResources(), settings);
        Intent intent = new Intent(this, SettingsActivity.class);
        this.startActivity(intent);
    }

    private void quitApp(@SuppressWarnings("unused") final View ignored) {
        setActiveButtonColours(findViewById(R.id.quit_button), getResources(), settings);
        finish();
    }

}
