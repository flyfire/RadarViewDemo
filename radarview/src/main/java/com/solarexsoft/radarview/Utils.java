package com.solarexsoft.radarview;

import android.content.Context;

/**
 * Created by houruhou on 2018/5/24.
 */

public class Utils {
    public static int dp2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dp*scale + 0.5f);
    }
}
