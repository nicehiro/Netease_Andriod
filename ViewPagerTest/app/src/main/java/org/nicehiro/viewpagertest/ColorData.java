package org.nicehiro.viewpagertest;

import android.graphics.Color;

/**
 * Created by root on 16-10-17.
 */

public class ColorData implements IColorData {

    private static final String[] colors = new String[] {
            "#F44336",
            "#E91E63",
            "#9C27B0",
            "#673AB7",
            "#3F51B5",
            "#2196F3",
            "#03A9F4",
            "#00BCD4",
    };

    @Override
    public int getCount() {
        return colors.length;
    }

    @Override
    public String getColorName(int index) {
        return colors[index];
    }

    @Override
    public int getColorValue(int index) {
        return Color.parseColor(colors[index]);
    }
}
