package com.solarexsoft.radarview;

/**
 * Created by houruhou on 2018/5/23.
 */

public class RadarData {
    private String title;
    private double percent;

    public RadarData(String title, double percent) {
        this.title = title;
        this.percent = percent;
    }

    public String getTitle() {
        return title;
    }

    public double getPercent() {
        return percent;
    }
}
