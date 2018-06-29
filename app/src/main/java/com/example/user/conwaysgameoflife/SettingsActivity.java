package com.example.user.conwaysgameoflife;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private RadioGroup colours;
    private int chosenRadioButtonId;
    private SeekBar speed;
    private int speedValue;
    private TextView bestResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        colours = findViewById(R.id.radioGroup);
        chosenRadioButtonId = colours.getCheckedRadioButtonId();

        speed = findViewById(R.id.speedBar);
        speedValue = speed.getProgress();

        final Button settingsButton = findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(this::saveNewSettings);
    }

    private void saveNewSettings(@SuppressWarnings("unused") final View ignored) {
        setActiveButtonColours(R.id.settings_button);

        SharedPreferences settings = getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("colorId", chosenRadioButtonId);
        editor.putInt("interval", 10-speedValue);
        editor.apply();

        Toast.makeText(this, "Settings were saved", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this , MainActivity.class);
        this.startActivity(intent);
    }

    private void setActiveButtonColours(final int id) {
        Button button = findViewById(id);
        button.setBackgroundColor(getResources().getColor(R.color.colorBlack));
        button.setTextColor(getResources().getColor(R.color.colorMain));
    }

}
