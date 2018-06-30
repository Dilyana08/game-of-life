package com.example.user.conwaysgameoflife;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.example.user.conwaysgameoflife.game.Game;
import com.example.user.conwaysgameoflife.game.Renderer;
import com.example.user.conwaysgameoflife.game.grid.GridCreator;
import com.example.user.conwaysgameoflife.game.grid.Point;

import java.util.Collection;

import static com.example.user.conwaysgameoflife.game.PointsConverter.getPoint;
import static com.example.user.conwaysgameoflife.utils.ButtonUtils.drawGridButton;
import static com.example.user.conwaysgameoflife.utils.ButtonUtils.setActiveButtonColours;
import static com.example.user.conwaysgameoflife.utils.ButtonUtils.setDefaultButtonColours;

public class PlayActivity extends AppCompatActivity {

    public static final int SIZE = 12;

    // @TODO get from properties
    public static final int SPEED_LEVEL = 10;

    public final int GRID_ID = R.id.grid;

    private final GridCreator presenter;
    private final Game game;
    private boolean isGameStarted;

    private Renderer renderer;

    public PlayActivity() {
        isGameStarted = false;
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
    }

    private void registerClearButton(final View view) {
        if (game.isRunning()) {
            return;
        }
        final Button button = (Button) view;
        setActiveButtonColours(button, getResources());

        game.clear();
        renderer.render(game);
        new Handler().postDelayed(() -> setDefaultButtonColours(button, getResources()), 500);
    }

    private void registerGridButton(final View view) {
        if (game.isRunning()) {
            return;
        }
        final Button clicked = (Button) view;
        final int id = clicked.getId();

        final Point point = getPoint(id);
        final boolean active = game.changePointState(point);
        drawGridButton(clicked, active, getResources());
    }

}
