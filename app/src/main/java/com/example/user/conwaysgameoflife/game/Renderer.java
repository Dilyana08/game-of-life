package com.example.user.conwaysgameoflife.game;

import android.content.res.Resources;
import android.widget.Button;

import com.example.user.conwaysgameoflife.game.grid.Point;

import java.util.Collection;

import static com.example.user.conwaysgameoflife.utils.ButtonUtils.drawGridButton;

public class Renderer {

    private final Resources resources;
    private final Collection<Button> buttons;

    public Renderer(Resources resources, Collection<Button> buttons) {
        this.resources = resources;
        this.buttons = buttons;
    }

    public void render(final Game game) {
        for (Button button : buttons) {
            final Point point = PointsConverter.getPoint(button.getId());
            drawGridButton(button, game.isAlive(point), resources);
        }
    }
}
