package com.example.user.conwaysgameoflife.utils;

import android.content.SharedPreferences;
import android.widget.ImageView;

import com.example.user.conwaysgameoflife.R;

public class ColorUtils {

    public static int getColorId(final SharedPreferences settings) {
        int colorButtonId = settings.getInt("colorId", R.id.greenRadioButton);

        switch (colorButtonId) {
            case R.id.redRadioButton:
                return R.color.Red;
            case R.id.greenRadioButton:
                return R.color.Green;
            case R.id.blueRadioButton:
                return R.color.Blue;
            default:
                return R.color.Green;

        }

    }

    public static void setNewLogo(final SharedPreferences settings, ImageView logo) {
        int colorButtonId = settings.getInt("colorId", R.id.greenRadioButton);

        switch (colorButtonId) {
            case R.id.redRadioButton:
                logo.setImageResource(R.drawable.red);
            case R.id.greenRadioButton:
                logo.setImageResource(R.drawable.green);
            case R.id.blueRadioButton:
                logo.setImageResource(R.drawable.blue);
            default:
                logo.setImageResource(R.drawable.green);
        }
    }
}
