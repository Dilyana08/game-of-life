package com.example.user.conwaysgameoflife;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.user.conwaysgameoflife.game.Game;
import com.example.user.conwaysgameoflife.game.Renderer;
import com.example.user.conwaysgameoflife.game.grid.GridCreator;
import com.example.user.conwaysgameoflife.game.grid.Point;
import com.example.user.conwaysgameoflife.utils.ColorUtils;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.example.user.conwaysgameoflife.game.PointsConverter.getPoint;
import static com.example.user.conwaysgameoflife.utils.ButtonUtils.drawGridButton;
import static com.example.user.conwaysgameoflife.utils.ButtonUtils.setActiveButtonColours;
import static com.example.user.conwaysgameoflife.utils.ButtonUtils.setDefaultButtonColours;

public class PlayActivity extends AppCompatActivity {

    public static final int MIN_SIZE = 8;
    public static final int MAX_SPEED = 11;

    private final GridCreator presenter;

    private Game game;
    private AtomicBoolean isGameStarted;

    private Renderer renderer;
    private SharedPreferences settings;

    public PlayActivity() {
        isGameStarted = new AtomicBoolean(false);
        presenter = new GridCreator();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        settings = getSharedPreferences("settings", Context.MODE_PRIVATE);

        final int speed = settings.getInt("interval", MODE_PRIVATE);
        int gridSize = settings.getInt("size", MODE_PRIVATE) + MIN_SIZE;
        game = new Game(gridSize, MAX_SPEED - speed);

        final TableLayout grid = findViewById(R.id.grid);
        final Collection<Button> gridButtons = presenter.createGrid(grid, gridSize, R.color.colorBlack);
        for (Button newButton : gridButtons) {
            setActiveButtonColours(newButton, getResources(), settings);
            newButton.setOnClickListener(this::registerGridButton);
        }

        renderer = new Renderer(getResources(), gridButtons, settings);

        final Button clearButton = findViewById(R.id.clearButton);
        setDefaultButtonColours(clearButton, getResources(), settings);
        clearButton.setOnClickListener(this::registerClearButton);

        final Button nextButton = findViewById(R.id.nextButton);
        setDefaultButtonColours(nextButton, getResources(), settings);
        nextButton.setOnClickListener(this::registerNextButton);

        final Button playButton = findViewById(R.id.startButton);
        setDefaultButtonColours(playButton, getResources(), settings);
        playButton.setOnClickListener(this::registerPlayButton);

        TextView generationsView = findViewById(R.id.generationsTextView);
        final int colorId = ColorUtils.getColorId(settings);
        generationsView.setTextColor(getResources().getColor(colorId));

        handleGenerations();
    }

    private void registerClearButton(final View view) {
        final Button button = (Button) view;
        if (isGameStarted.get()) {
            isGameStarted.set(false);
            game.restart();
            drawButtonClicked(button);
            button.setText(R.string.clear_grid);

            final Button playButton = findViewById(R.id.startButton);
            playButton.setText(R.string.start);
            handleGenerations();
        } else {
            game.clear();
            drawButtonClicked(button);
        }
        renderer.render(game);

    }

    private void registerPlayButton(final View view) {
        final boolean result = isGameStarted.compareAndSet(false, true);
        final Button button = (Button) view;
        if (result) {
            button.setText(R.string.pause);
            final Button restart = findViewById(R.id.clearButton);
            restart.setText(R.string.restart);
            game.start();
            gameLoop();
        } else if (game.isRunning()) {
            game.stop();
            button.setText(R.string.resume);
        } else {
            button.setText(R.string.pause);
            game.resume();
            gameLoop();
        }

        drawButtonClicked(button);
    }

    private void gameLoop() {
        final Handler handler = new Handler();
        final Runnable step = new Runnable() {
            @Override
            public void run() {
                if (!game.isRunning()) {
                    return;
                }
                if (game.nextGeneration()) {
                    renderer.render(game);
                    handleGenerations();
                }
                handler.postDelayed(this, game.getInterval());
            }
        };

        handler.post(step);
    }

    private void registerNextButton(final View view) {
        if (game.isRunning() || !isGameStarted.get()) {
            return;
        }
        game.nextGeneration();
        renderer.render(game);
        handleGenerations();
        drawButtonClicked((Button) view);
    }

    private void registerGridButton(final View view) {
        if (isGameStarted.get()) {
            return;
        }
        final Button clicked = (Button) view;
        final int id = clicked.getId();

        final Point point = getPoint(id);
        final boolean active = game.changePointState(point);
        drawGridButton(clicked, active, getResources(), settings);
    }

    @SuppressLint("StringFormatInvalid")
    private void handleGenerations() {
        TextView generationsView = findViewById(R.id.generationsTextView);
        final int generations = game.getGenerationNumber();
        final String text = getString(R.string.generations, generations);
        generationsView.setText(text);
    }

    private void drawButtonClicked(final Button button) {
        setActiveButtonColours(button, getResources(), settings);
        new Handler().postDelayed(() -> setDefaultButtonColours(button, getResources(), settings), 500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        game.stop();
    }
}
