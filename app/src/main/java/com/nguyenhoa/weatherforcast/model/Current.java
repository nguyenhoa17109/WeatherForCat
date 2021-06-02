package com.nguyenhoa.weatherforcast.model;

import com.nguyenhoa.weatherforcast.common.Common;

import java.util.List;

public class Current {
    public int dt;
    public int sunrise;
    public int sunset;
    public double temp;
    public double feels_like;
    public int pressure;
    public int humidity;
    public double dew_point;
    public float uvi;
    public int clouds;
    public int visibility;
    public double wind_speed;
    public int wind_deg;
    public List<Weather> weather;

    public Current() {
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public int getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(double feels_like) {
        this.feels_like = feels_like;
    }

    public int getPressure() {
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

    public double getDew_point() {
        return dew_point;
    }

    public void setDew_point(double dew_point) {
        this.dew_point = dew_point;
    }

    public float getUvi() {
        return Math.round(uvi);
    }

    public void setUvi(int uvi) {
        this.uvi = uvi;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public int getWind_deg() {
        return wind_deg;
    }

    public void setWind_deg(int wind_deg) {
        this.wind_deg = wind_deg;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "Sunrise: " + Common.converUnixToHour(sunrise) +
                "\nSunset: " + Common.converUnixToHour(sunset) +
                "\nTemperature: " + temp + "°C"+
                "\nFeels_like: " + feels_like + "°C"+
                "\nPressure: " + pressure +
                "mb\nHumidity: " + humidity +
                "%\nDew_point: " + dew_point +
                "°\nUVI: " + uvi +
                "\nClouds: " + clouds +
                "\nVisibility: " + visibility +
                "km\nWind_speed: " + wind_speed +
                "km/h\nWind_deg=" + wind_deg;
    }

    public String getWin(){
        return "Win:" +
                "\nWind_speed=" + wind_speed +
                "km/h\nWind_deg=" + wind_deg;
    }
    public String getMain(){
        return "Main:" +
                "\nTemperature: " + temp +
                "°C\nFeels_like=" + feels_like +
                "°C\nPressure=" + pressure +
                " mb\nHumidity=" + humidity;
    }
}
