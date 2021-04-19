package com.nguyenhoa.weatherforcast.model;

public class WeatherOfDay {
    private String hour, day, date, month, year;
    private String weather;
    private int temp;
    private int humidity;
    private int img;

    public WeatherOfDay(String day, int temp, int img) {
        this.day = day;
        this.temp = temp;
        this.img = img;
    }

    public WeatherOfDay(String day, String hour, int temp, int img) {
        this.hour = hour;
        this.temp = temp;
        this.img = img;
    }

    public WeatherOfDay() {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
