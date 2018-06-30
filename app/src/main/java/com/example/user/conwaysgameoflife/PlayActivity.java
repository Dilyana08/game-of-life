package com.example.user.conwaysgameoflife;

import android.annotation.SuppressLint;
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

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.example.user.conwaysgameoflife.game.PointsConverter.getPoint;
import static com.example.user.conwaysgameoflife.utils.ButtonUtils.drawGridButton;
import static com.example.user.conwaysgameoflife.utils.ButtonUtils.setActiveButtonColours;
import static com.example.user.conwaysgameoflife.utils.ButtonUtils.setDefaultButtonColours;

public class PlayActivity extends AppCompatActivity {

    public static final int SIZE = 15;

    // @TODO get from properties
    public static final int SPEED_LEVEL = 10;

    public final int GRID_ID = R.id.grid;

    private final GridCreator presenter;
    private final Game game;
    private AtomicBoolean isGameStarted;

    private Renderer renderer;

    public PlayActivity() {
        isGameStarted = new AtomicBoolean(false);
        presenter = new GridCreator();
        game = new Game(SIZE, SPEED_LEVEL);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        final TableLayout grid = findViewById(GRID_ID);
        final Collection<Button> gridButtons = presenter.createGrid(grid, SIZE, R.color.colorBlack);
        for (Button newButton : gridButtons) {
            setActiveButtonColours(newButton, getResources());
            newButton.setOnClickListener(this::registerGridButton);
        }

        renderer = new Renderer(getResources(), gridButtons);

        final Button clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(this::registerClearButton);

        final Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(this::registerNextButton);

        final Button playButton = findViewById(R.id.startButton);
        playButton.setOnClickListener(this::registerPlayButton);

        handleGenerations();
    }

    private void registerClearButton(final View view) {
        if (isGameStarted.get()) {
            return;
        }
        final Button button = (Button) view;

        game.clear();
        renderer.render(game);
        drawButtonClicked(button);
    }

    private void registerPlayButton(final View view) {


        final boolean result = isGameStarted.compareAndSet(false, true);
        final Button button = (Button) view;
        if (result) {
            button.setText(R.string.pause);
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
        drawGridButton(clicked, active, getResources());
    }

    @SuppressLint("StringFormatInvalid")
    private void handleGenerations() {
        TextView generationsView = findViewById(R.id.generationsTextView);
        final int generations = game.getGenerationNumber();
        final String text = getString(R.string.generations, generations);
        generationsView.setText(text);
    }

    private void drawButtonClicked(Button button) {
        setActiveButtonColours(button, getResources());
        new Handler().postDelayed(() -> setDefaultButtonColours(button, getResources()), 500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        game.stop();
    }
}
