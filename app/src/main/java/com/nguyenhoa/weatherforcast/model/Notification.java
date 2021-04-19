package com.nguyenhoa.weatherforcast.model;

public class Notification {
    private String day;
    private String title;
    private String content;

    public Notification() {
    }

    public Notification(String day, String title, String content) {
        this.day = day;
        this.title = title;
        this.content = content;
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
}
