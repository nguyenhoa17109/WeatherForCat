package com.nguyenhoa.weatherforcast.model;

import com.nguyenhoa.weatherforcast.common.Common;

import java.util.List;

public class MyList {
    public int dt;
    public Main main;
    public List<Weather> weather;
    public Clouds clouds;
    public Wind wind;
    public Rain rain;
    public Sys sys;
    public String dt_txt;

    @Override
    public String toString() {
        return "dt=" + Common.converUnixToDate(dt) +
                ", main=" + main.toString() +
                ", clouds=" + clouds.toString() +
                ", wind=" + wind.toString() +
                ", rain=" + rain.toString() +
                ", sys=" + sys;
    }
}
