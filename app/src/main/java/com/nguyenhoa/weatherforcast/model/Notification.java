package com.nguyenhoa.weatherforcast.model;

public class Notification {
    private String day;
    private String title;
    private String content;
    private String general;
    private String win;
    private String main;
    private String clouds;

    public Notification() {
    }

    public Notification(String day, String title, String content, String general, String win, String main, String clouds) {
        this.day = day;
        this.title = title;
        this.content = content;
        this.general = general;
        this.win = win;
        this.main = main;
        this.clouds = clouds;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }
}
