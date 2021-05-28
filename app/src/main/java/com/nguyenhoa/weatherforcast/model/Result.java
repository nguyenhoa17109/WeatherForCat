package com.nguyenhoa.weatherforcast.model;

import java.io.Serializable;

public class Result implements Serializable {
    private int id;
    private String weather;
    private String coord;
    private String main;
    private String win;
    private String clouds;
    private String date;
    private String timezone;
    private String name;

    public Result() {
    }

    public Result(int id, String weather, String coord, String main, String win, String clouds, String date, String timezone, String name) {
        this.id = id;
        this.weather = weather;
        this.coord = coord;
        this.main = main;
        this.win = win;
        this.clouds = clouds;
        this.date = date;
        this.timezone = timezone;
        this.name = name;
    }

    public Result(String weather, String coord, String main, String win, String clouds, String date, String timezone, String name) {
        this.weather = weather;
        this.coord = coord;
        this.main = main;
        this.win = win;
        this.clouds = clouds;
        this.date = date;
        this.timezone = timezone;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoord() {
        return coord;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", coord='" + coord + '\'' +
                ", main='" + main + '\'' +
                ", win='" + win + '\'' +
                ", clouds='" + clouds + '\'' +
                ", date='" + date + '\'' +
                ", timezone='" + timezone + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
