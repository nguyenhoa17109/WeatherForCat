package com.nguyenhoa.weatherforcast.model;

public class Clouds {
    private int all;

    public Clouds() {
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "Clouds{" +
                "all=" + all +
                '}';
    }
}
