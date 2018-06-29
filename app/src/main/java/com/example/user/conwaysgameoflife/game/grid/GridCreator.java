package com.example.user.conwaysgameoflife.game.grid;

import android.content.Context;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import java.util.Collection;
import java.util.HashSet;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.LinearLayout.HORIZONTAL;
import static com.example.user.conwaysgameoflife.PlayActivity.ROWS_OFFSET;

public class GridCreator {

    public Collection<Button> createGrid(final TableLayout grid, final int size, final int backgroundColour) {
        final Context gridContext = grid.getContext();

        final Collection<Button> createdButtons = new HashSet<>();

        for (int row = 0; row < size; row++) {
            final LinearLayout rowLayout = createRow((row + 1) * ROWS_OFFSET, gridContext);
            final Context rowContext = rowLayout.getContext();
            for (int column = 0; column < size; column++) {
                final int buttonId = rowLayout.getId() + column + 1;
                final Button cellButton = createCell(buttonId, rowContext, backgroundColour);
                rowLayout.addView(cellButton);
                createdButtons.add(cellButton);
            }
            grid.addView(rowLayout);
        }

        return createdButtons;
    }

    private LinearLayout createRow(final int id, final Context context) {
        final LinearLayout layout = new LinearLayout(context);
        layout.setId(id);
        layout.setOrientation(HORIZONTAL);

        final TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(0, WRAP_CONTENT, 1.0f);
        layoutParams.gravity = Gravity.CENTER;

        layout.setLayoutParams(layoutParams);

        return layout;
    }

    private Button createCell(final int id, final Context context, int backgroundColour) {
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, MATCH_PARENT, 1.0f);
        layoutParams.setMargins(1, 1, 1, 1);

        final Button button = new Button(context);
        button.setId(id);

        button.setLayoutParams(layoutParams);
        button.setBackgroundColor(backgroundColour);

        return button;
    }
}
