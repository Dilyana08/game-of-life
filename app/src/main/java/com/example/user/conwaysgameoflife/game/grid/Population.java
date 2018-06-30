package com.example.user.conwaysgameoflife.game.grid;

import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.abs;

public class Population {

    private final Set<Point> living;
    private final int size;

    public Population(final int size, final Set<Point> living) {
        this.size = size;
        this.living = living;
    }

    public Set<Point> getVisible() {
        final Set<Point> visible = new HashSet<>();
        for (Point alive : living) {
            if (isVisible(alive)) {
                visible.add(alive);
            }
        }
        return visible;
    }

    public boolean isAlive(final Point point) {
        return living.contains(point);
    }

    public boolean isAlive() {
        for (Point alive : living) {
            if (isVisible(alive)) {
                return true;
            }
        }
        return false;
    }

    public Population nextGeneration() {
        final Set<Point> allPoints = generatePoints();
        final Set<Point> alive = new HashSet<>();
        for (Point point : allPoints) {

            int liveNeighbours = 0;
            boolean isAliveNow = false;

            for (Point livePoint : living) {
                if (livePoint.equals(point)) {
                    isAliveNow = true;
                } else if (abs(livePoint.getColumn() - point.getColumn()) <= 1 &&
                        abs(livePoint.getRow() - point.getRow()) <= 1) {
                    liveNeighbours++;
                }
            }

            if (!shouldBeDead(liveNeighbours, isAliveNow)) {
                alive.add(point);
            }

        }
        return new Population(size, alive);
    }

    private Set<Point> generatePoints() {
        final Set<Point> allPoints = new HashSet<>();

        for (int row = -1; row <= size; row++) {
            for (int column = -1; column <= size; column++) {
                final Point point = new Point(row, column);
                allPoints.add(point);
            }
        }

        return allPoints;
    }

    private boolean shouldBeDead(final int liveNeighbours, final boolean isAliveNow) {
        return liveNeighbours < 2 || liveNeighbours > 3 || (liveNeighbours == 2 && !isAliveNow);
    }

    private boolean isVisible(final Point point) {
        return isVisible(point.getColumn()) && isVisible(point.getRow());
    }

    private boolean isVisible(final int coordinate) {
        return coordinate >= 0 && coordinate < size;
    }

}
