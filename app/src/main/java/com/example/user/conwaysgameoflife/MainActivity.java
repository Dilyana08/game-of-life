package com.example.user.conwaysgameoflife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.user.conwaysgameoflife.utils.ButtonUtils.setActiveButtonColours;
import static com.example.user.conwaysgameoflife.utils.ButtonUtils.setDefaultButtonColours;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        setDefaultButtonColours(findViewById(R.id.play_button), getResources());
        setDefaultButtonColours(findViewById(R.id.settings_button), getResources());
        setDefaultButtonColours(findViewById(R.id.quit_button), getResources());
    }

    private void goToPlay(@SuppressWarnings("unused") final View ignored) {
        setActiveButtonColours(findViewById(R.id.play_button), getResources());
        Intent intent = new Intent(this, PlayActivity.class);
        this.startActivity(intent);
    }

    private void goToSettings(@SuppressWarnings("unused") final View view) {
        setActiveButtonColours(findViewById(R.id.settings_button), getResources());
        Intent intent = new Intent(this, SettingsActivity.class);
        this.startActivity(intent);
    }

    private void quitApp(@SuppressWarnings("unused") final View ignored) {
        setActiveButtonColours(findViewById(R.id.quit_button), getResources());
        finish();
    }

}
