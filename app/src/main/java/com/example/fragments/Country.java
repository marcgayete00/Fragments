package com.example.fragments;

public class Country {
    private String countryName;
    private double temp;
    private String description;

    public Country(String countryName, double temp, String description) {
        this.countryName = countryName;
        this.temp = temp;
        this.description = description;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
