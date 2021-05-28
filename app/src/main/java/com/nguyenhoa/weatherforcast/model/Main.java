package com.nguyenhoa.weatherforcast.model;

public class Main {
    private float temp;
    private float feels_like;
    private double temp_min;
    private double temp_max;
    private float pressure;
    private int humidity;
    private float sea_level;
    private float grnd_level;

    public Main() {
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public double getSea_level() {
        return sea_level;
    }

    public void setSea_level(float sea_level) {
        this.sea_level = sea_level;
    }

    public double getGrnd_level() {
        return grnd_level;
    }

    public void setGrnd_level(float grnd_level) {
        this.grnd_level = grnd_level;
    }

    public double getTemp() {
        return Math.round(temp);
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(float feels_like) {
        this.feels_like = feels_like;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "Temperature: " + temp +
                "°C\nFeel like: " + Math.round(feels_like) +
                "°C\nTemp min: " + Math.round(temp_min) +
                "°C\nTemp max: " + Math.round(temp_max) +
                "°C\nPressure: " + pressure +
                " mb\nHumidity: " + humidity +
                "%\nSea level: " + sea_level ;
    }

    //    @Override
//    public String toString() {
//        return String.valueOf(temp);
//    }
}
