package com.example.user.conwaysgameoflife.game;

import com.example.user.conwaysgameoflife.game.grid.Point;
import com.example.user.conwaysgameoflife.game.grid.Population;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Game {

    private Population population;

    private int speedLevel;
    private boolean running;

    private int generationNumber;

    public Game(final Collection<Point> alivePoints, final int gridSize, final int speedLevel) {
        this.population = new Population(gridSize, new HashSet<>(alivePoints));
        this.running = false;
        this.speedLevel = speedLevel;
    }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public void resume() {
        running = true;
    }

    public boolean isRunning() {
        return running;
    }

    public int getGenerationNumber() {
        return generationNumber;
    }

    public Collection<Point> nextGeneration() {
        final Population newPopulation = population.nextGeneration();
        population = newPopulation;
        Set<Point> visible = newPopulation.getVisible();
        if (!visible.isEmpty()) {
            generationNumber++;
        }
        return visible;
    }

    public int getInterval() {
        return speedLevel * 10;
    }
}
