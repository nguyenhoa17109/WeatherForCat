package com.nguyenhoa.weatherforcast.model;

public class City {
    public int id;
    public String name;
    public Coord coord;
    public String country;

    @Override
    public String toString() {
        return name + ',' + country;
    }
}
