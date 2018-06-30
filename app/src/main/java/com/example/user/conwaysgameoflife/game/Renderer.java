package com.example.user.conwaysgameoflife.game;

import android.content.res.Resources;
import android.widget.Button;

import com.example.user.conwaysgameoflife.game.grid.Point;
import com.example.user.conwaysgameoflife.utils.ButtonUtils;

import java.util.Collection;

import static com.example.user.conwaysgameoflife.utils.ButtonUtils.setActiveButtonColours;
import static com.example.user.conwaysgameoflife.utils.ButtonUtils.setDefaultButtonColours;

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
            if (game.getPointState(point)) {
                setDefaultButtonColours(button, resources);
            } else {
                setActiveButtonColours(button, resources);
            }
        }
    }
}
