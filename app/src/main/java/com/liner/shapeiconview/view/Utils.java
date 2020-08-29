package com.liner.shapeiconview.view;

import android.content.res.Resources;
import android.util.DisplayMetrics;

public class Utils {
    public static float dpToPx(float dp) {
        return dp * ((float) Resources.getSystem().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
