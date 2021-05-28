package com.nguyenhoa.weatherforcast.model;

public class Wind {
    private double speed;
    private double deg;
    public double gust;

    public double getGust() {
        return gust;
    }

    public void setGust(double gust) {
        this.gust = gust;
    }

    public Wind() {
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }

    @Override
    public String toString() {
        return "Speed: " + speed +
                "km/h; Deg: " + deg +
                "°C; Gust: " + gust +" knots";
    }
}
