package com.example.fragments;

public class Country {
    private String countryName;
    private String description;
    private Double temperature;

    public Country(String countryName, String description, Double temperature) {
        this.countryName = countryName;
        this.description = description;
        this.temperature = temperature;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}

