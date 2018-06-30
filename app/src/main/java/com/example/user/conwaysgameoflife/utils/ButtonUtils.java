package com.example.user.conwaysgameoflife.utils;

import android.content.res.Resources;
import android.widget.Button;

import com.example.user.conwaysgameoflife.R;

public class ButtonUtils {

    public static void setDefaultButtonColours(final Button button, Resources resources) {
        button.setBackgroundColor(resources.getColor(R.color.colorMain));
        button.setTextColor(resources.getColor(R.color.colorBlack));
    }

    public static void setActiveButtonColours(final Button button, Resources resources) {
        button.setBackgroundColor(resources.getColor(R.color.colorBlack));
        button.setTextColor(resources.getColor(R.color.colorMain));
    }

    public static void drawGridButton(Button button, boolean active, Resources resources) {
        if (active) {
            button.setBackgroundColor(resources.getColor(R.color.colorMain));
        } else {
            button.setBackgroundColor(resources.getColor(R.color.colorBlack));
        }
    }
}
