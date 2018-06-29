package com.example.user.conwaysgameoflife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.example.user.conwaysgameoflife.game.Game;
import com.example.user.conwaysgameoflife.game.grid.GridCreator;
import com.example.user.conwaysgameoflife.game.grid.Point;

import java.util.Collection;

import static com.example.user.conwaysgameoflife.utils.ButtonUtils.setActiveButtonColours;
import static com.example.user.conwaysgameoflife.utils.ButtonUtils.setDefaultButtonColours;

public class PlayActivity extends AppCompatActivity {

    public static final int ROWS_OFFSET = 1_000;
    public static final int SIZE = 12;

    // @TODO get from properties
    public static final int SPEED_LEVEL = 10;

    public final int GRID_ID = R.id.grid;

    private final GridCreator presenter;
    private Game game;
    private boolean isGameStarted;

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
        final Collection<Button> newButtons = presenter.createGrid(grid, SIZE, R.color.colorBlack);
        for (Button newButton : newButtons) {
            setActiveButtonColours(newButton, getResources());
            newButton.setOnClickListener(this::registerGridButton);
        }

    }

    private void registerGridButton(final View view) {
        if (game.isRunning()) {
            return;
        }
        final Button clicked = (Button) view;
        final int id = clicked.getId();

        final int row = (id / ROWS_OFFSET) - 1;
        final int column = (id % ROWS_OFFSET) - 1;

        final Point point = new Point(row, column);
        final boolean active = game.changePointState(point);

        if (active) {
            setDefaultButtonColours(clicked, getResources());
        } else {
            setActiveButtonColours(clicked, getResources());
        }
    }

}
