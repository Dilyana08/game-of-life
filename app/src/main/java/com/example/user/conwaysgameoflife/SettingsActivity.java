package com.example.user.conwaysgameoflife;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.conwaysgameoflife.utils.ButtonUtils;
import com.example.user.conwaysgameoflife.utils.ColorUtils;

public class SettingsActivity extends AppCompatActivity {

    private int chosenRadioButtonId;
    private int speedValue;
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings = getSharedPreferences("settings", Context.MODE_PRIVATE);

        setCurrentSettings();

        final Button settingsButton = findViewById(R.id.save_button);
        settingsButton.setOnClickListener(this::saveNewSettings);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ButtonUtils.setDefaultButtonColours(findViewById(R.id.save_button), getResources(), settings);
    }

    private void setCurrentSettings() {
        int colorId = settings.getInt("colorId", R.id.greenRadioButton);
        RadioGroup colours = findViewById(R.id.radioGroup);
        colours.check(colorId);
        changeColours();

        int speedVal = settings.getInt("interval", 5);
        SeekBar speed = findViewById(R.id.speedBar);
        speed.setMax(10);
        speed.setProgress(speedVal);

        int bestRes = settings.getInt("bestResults", 0);
        TextView bestResult = findViewById(R.id.bestResultView);
        bestResult.setText("" + bestRes);
    }

    private void changeColours() {
        int colorId = ColorUtils.getColorId(settings);

        TextView settingsText = findViewById(R.id.settingsMenu);
        settingsText.setTextColor(getResources().getColor(colorId));

        TextView chooseColor = findViewById(R.id.colorRadio);
        chooseColor.setTextColor(getResources().getColor(colorId));

        RadioButton red = findViewById(R.id.redRadioButton);
        red.setTextColor(getResources().getColor(colorId));

        RadioButton green = findViewById(R.id.greenRadioButton);
        green.setTextColor(getResources().getColor(colorId));

        RadioButton blue = findViewById(R.id.blueRadioButton);
        blue.setTextColor(getResources().getColor(colorId));

        TextView chooseSpeed = findViewById(R.id.speedBarText);
        chooseSpeed.setTextColor(getResources().getColor(colorId));

        TextView bestResultsText = findViewById(R.id.bestResultText);
        bestResultsText.setTextColor(getResources().getColor(colorId));

        TextView bestResult = findViewById(R.id.bestResultView);
        bestResult.setTextColor(getResources().getColor(colorId));
    }


    private void saveNewSettings(@SuppressWarnings("unused") final View ignored) {
        final Button settingsButton = findViewById(R.id.save_button);
        ButtonUtils.setActiveButtonColours(settingsButton, getResources(), settings);

        writeSettings();

        Toast.makeText(this, "Settings were saved", Toast.LENGTH_LONG).show();
        finish();
    }

    private void writeSettings() {
        RadioGroup colours = findViewById(R.id.radioGroup);
        chosenRadioButtonId = colours.getCheckedRadioButtonId();

        SeekBar speed = findViewById(R.id.speedBar);
        speedValue = speed.getProgress();

        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("colorId", chosenRadioButtonId);
        editor.putInt("interval", speedValue);
        editor.apply();
    }
}
