package com.example.user.conwaysgameoflife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(this::goToPlay);

        final Button quitButton = findViewById(R.id.quit_button);
        quitButton.setOnClickListener(this::quitApp);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setDefaultButtonColours(R.id.play_button);
        setDefaultButtonColours(R.id.quit_button);
    }

    private void goToPlay(@SuppressWarnings("unused") final View ignored){
        setActiveButtonColours(R.id.play_button);
        Intent intent = new Intent(this , PlayActivity.class);
        this.startActivity(intent);
    }

    private void quitApp(@SuppressWarnings("unused") final View ignored){
        setActiveButtonColours(R.id.quit_button);
        finish();
    }

    private void setDefaultButtonColours(final int id) {
        final Button button = findViewById(id);
        button.setBackgroundColor(getResources().getColor(R.color.colorGreen));
        button.setTextColor(getResources().getColor(R.color.colorBlack));
    }

    private void setActiveButtonColours(final int id) {
        Button button = findViewById(id);
        button.setBackgroundColor(getResources().getColor(R.color.colorBlack));
        button.setTextColor(getResources().getColor(R.color.colorGreen));
    }
}
