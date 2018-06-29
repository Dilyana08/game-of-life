package com.example.user.conwaysgameoflife.grid;

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

    public Population nextGeneration() {
        final Set<Point> allPoints = generatePoints();
        for (Point point : allPoints) {

            int liveNeighbours = 0;
            boolean isAliveNow = false;

            for (Point livePoint : living) {
                if (livePoint.equals(point)) {
                    isAliveNow = true;
                } else if (distanceBetween(point, livePoint) == 1) {
                    liveNeighbours++;
                }
            }

            if (!shouldBeDead(liveNeighbours, isAliveNow)) {
                allPoints.add(point);
            }

        }
        return new Population(size, allPoints);
    }

    private int distanceBetween(final Point a, final Point b) {
        return abs(a.getColumn() - b.getColumn()) + abs(a.getRow() + b.getRow());
    }

    private boolean shouldBeDead(final int liveNeighbours, final boolean isAliveNow) {
        return !(liveNeighbours < 2 || liveNeighbours > 3 || (liveNeighbours == 2 && !isAliveNow));
    }

    public boolean isAlive() {
        for (Point alive : living) {
            if (isVisible(alive)) {
                return true;
            }
        }
        return false;
    }

    private boolean isVisible(final Point point) {
        return isVisible(point.getColumn()) && isVisible(point.getRow());
    }

    private boolean isVisible(final int coordinate) {
        return coordinate >= 0 && coordinate < size;
    }

}
