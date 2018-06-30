package com.example.user.conwaysgameoflife.game;

import com.example.user.conwaysgameoflife.game.grid.Point;
import com.example.user.conwaysgameoflife.game.grid.Population;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.emptySet;

public class Game {

    private Population population;

    private Set<Point> selectedPoints;

    private boolean running;
    private final int speedLevel;
    private final int size;

    private int generationNumber;

    public Game(final int gridSize, final int speedLevel) {
        this.selectedPoints = new HashSet<>();
        this.size = gridSize;
        this.running = false;
        this.speedLevel = speedLevel;
    }

    public boolean changePointState(final Point point) {
        if (selectedPoints.contains(point)) {
            selectedPoints.remove(point);
            return false;
        } else {
            selectedPoints.add(point);
            return true;
        }
    }

    public synchronized void start() {
        running = true;
        population = new Population(size, selectedPoints);
    }

    public synchronized void stop() {
        running = false;
    }

    public synchronized void resume() {
        running = true;
    }

    public synchronized void clear() {
        population = new Population(size, emptySet());
        selectedPoints.clear();
    }

    public synchronized void restart() {
        running = false;
        generationNumber = 0;
        clear();
    }

    public synchronized boolean isRunning() {
        return running;
    }

    public synchronized int getGenerationNumber() {
        return generationNumber;
    }

    public synchronized boolean nextGeneration() {
        if (!population.isAlive()) {
            stop();
            return false;
        }
        final Population newPopulation = population.nextGeneration();
        population = newPopulation;
        Set<Point> visible = newPopulation.getVisible();
        if (!visible.isEmpty()) {
            generationNumber++;
        }
        return true;
    }

    public synchronized boolean isAlive(final Point point) {
        return population.isAlive(point);
    }

    public synchronized int getInterval() {
        return speedLevel * 10;
    }
}
