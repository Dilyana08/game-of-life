package com.example.user.conwaysgameoflife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

import com.example.user.conwaysgameoflife.grid.GridCreator;

public class PlayActivity extends AppCompatActivity {

    public static final int ROWS_OFFSET = 1000;
    public static final int CELLS_OFFSET = 10000;

    public final int GRID_ID = R.id.grid;

    private final GridCreator presenter;

    public PlayActivity() {
        presenter = new GridCreator();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        final TableLayout grid = findViewById(GRID_ID);
        presenter.createGrid(grid, 8, R.color.colorBlack);
    }

}
