package com.example.user.conwaysgameoflife.game;

import com.example.user.conwaysgameoflife.game.grid.Point;
import com.example.user.conwaysgameoflife.game.grid.Population;

import java.util.Collection;
import java.util.HashSet;

public class Game {

    private Population population;

    private int speedLevel;
    private boolean running;

    public Game(final Collection<Point> alivePoints, final int gridSize, final boolean running, final int speedLevel) {
        this.population = new Population(gridSize, new HashSet<>(alivePoints));
        this.running = running;
        this.speedLevel = speedLevel;
    }

    public void stop() {
        running = false;
    }

    public void start() {
        running = true;
    }

    public void resume() {
        running = true;
    }

    public boolean isRunning() {
        return running;
    }

    public Collection<Point> nextGeneration() {
        final Population newPopulation = population.nextGeneration();
        population = newPopulation;
        return newPopulation.getVisible();
    }

    public int getInterval() {
        return speedLevel * 10;
    }
}
