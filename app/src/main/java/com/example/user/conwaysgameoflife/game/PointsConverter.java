package com.example.user.conwaysgameoflife.game;

import com.example.user.conwaysgameoflife.game.grid.Point;

public class PointsConverter {

    private static final int ROWS_OFFSET = 1_000;

    public static Point getPoint(final int id) {
        final int row = (id / ROWS_OFFSET) - 1;
        final int column = (id % ROWS_OFFSET) - 1;

        return new Point(row, column);
    }

    public static int getRowId(final int rowNumber) {
        return (rowNumber + 1) * ROWS_OFFSET;
    }

    public static int getCellId(final int row, int column) {
        return row + column + 1;
    }
}
