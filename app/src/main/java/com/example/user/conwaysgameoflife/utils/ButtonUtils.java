package com.example.user.conwaysgameoflife.utils;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.widget.Button;

import com.example.user.conwaysgameoflife.R;

import static com.example.user.conwaysgameoflife.utils.ColorUtils.getColorId;

public class ButtonUtils {

    public static void setDefaultButtonColours(final Button button, Resources resources, final SharedPreferences settings) {
        int colorId = getColorId(settings);
        button.setBackgroundColor(resources.getColor(colorId));
        button.setTextColor(resources.getColor(R.color.colorBlack));
    }

    public static void setActiveButtonColours(final Button button, Resources resources, final SharedPreferences settings) {
        int colorId = getColorId(settings);
        button.setBackgroundColor(resources.getColor(R.color.colorBlack));
        button.setTextColor(resources.getColor(colorId));
    }

    public static void drawGridButton(Button button, boolean active, Resources resources, final SharedPreferences settings) {
        int colorId = getColorId(settings);
        if (active) {
            button.setBackgroundColor(resources.getColor(colorId));
        } else {
            button.setBackgroundColor(resources.getColor(R.color.colorBlack));
        }
    }
}
