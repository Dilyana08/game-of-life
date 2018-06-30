package com.example.user.conwaysgameoflife.game;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.widget.Button;

import com.example.user.conwaysgameoflife.game.grid.Point;

import java.util.Collection;

import static com.example.user.conwaysgameoflife.utils.ButtonUtils.drawGridButton;

public class Renderer {

    private final Resources resources;
    private final SharedPreferences settings;
    private final Collection<Button> buttons;

    public Renderer(final Resources resources, final Collection<Button> buttons, final SharedPreferences settings) {
        this.resources = resources;
        this.buttons = buttons;
        this.settings = settings;
    }

    public void render(final Game game) {
        for (Button button : buttons) {
            final Point point = PointsConverter.getPoint(button.getId());
            drawGridButton(button, game.isAlive(point), resources, settings);
        }
    }
}
