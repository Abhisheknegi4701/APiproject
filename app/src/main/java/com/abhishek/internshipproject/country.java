package com.abhishek.internshipproject;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class country {

    public String name;
    public String capital;
    public String region;
    public String flag;
    public String subregion;
    public String population;
    public JSONArray borders;
    public JSONArray languages;

    public country(String name, String capital, String region, String flag, String subregion, String population, JSONArray borders, JSONArray languages) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.flag = flag;
        this.subregion = subregion;
        this.population = population;
        this.borders = borders;
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public JSONArray getBorders() {
        return borders;
    }

    public void setBorders(JSONArray borders) {
        this.borders = borders;
    }

    public JSONArray getLanguages() {
        return languages;
    }

    public void setLanguages(JSONArray languages) {
        this.languages = languages;
    }
}
