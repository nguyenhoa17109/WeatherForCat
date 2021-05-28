package com.nguyenhoa.weatherforcast.model;

import com.nguyenhoa.weatherforcast.common.Common;

import java.io.Serializable;
import java.util.List;

public class WeatherResult implements Serializable {
    private Coord coord;
    public List<Weather> weather;
    private String base;
    public Main main;
    public Wind wind;
    private Clouds clouds;
    private int dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;

    public WeatherResult() {
    }

    public WeatherResult(Coord coord, String base, Main main, Wind wind, Clouds clouds, int dt, int timezone, String name) {
        this.coord = coord;
        this.base = base;
        this.main = main;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.timezone = timezone;
        this.name = name;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    @Override
    public String toString() {
        return "Weather Detail:" +
                "\nDate: " + Common.converUnixToDate(dt) +
                "\nLocation: '" + name + '\''+
                "\nCoord: " + coord +
                "\nMain: " + main.toString() +
                "\nWind: " + wind.toString();
    }

    public String getNo(){
        return "Weather Detail:" +
                "\nDate: " + Common.converUnixToDate(dt) +
                "\nLocation: '" + name + '\''+
                "\nCoord: " + coord +
                "\nMain: " + main.toString() +
                "\nWind: " + wind.toString();
    }
}
