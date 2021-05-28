package com.nguyenhoa.weatherforcast.model;


import java.util.List;

public class WeatherForecastResult{
    public String cod;
    public double message;
    public int cnt;
    public List<MyList> list;
    public City city;

    public List<MyList> getList() {
        return list;
    }

    public void setList(List<MyList> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "WeatherForecastResult{" +
                "cod='" + cod + '\'' +
                ", message=" + message +
                ", cnt=" + cnt +
                ", list=" + list +
                ", city=" + city +
                '}';
    }
}
