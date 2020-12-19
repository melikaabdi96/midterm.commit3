package com.company.model;

import java.io.File;
import java.util.ArrayList;
/**
 * This class holds food information
 *
 * @author Melika
 * @since 2020
 */
public class FoodModel {
    private String name;
    private String day;
    private String cost;
    private ArrayList<File> stuents;
    private String date;

    /**
     * create food
     * @param name of food
     * @param day of food reservation
     * @param cost of food
     */
    public FoodModel(String name, String day, String cost, String date){
        this.name = name;
        this.cost= cost;
        this.day = day;
        this.date = date;
    }

    @Override
    public String toString() {
        return
                "food:" +
                        "name='" + name + '\'' +
                        ", day='" + day + '\'' +
                        ", cost='" + cost + '\'' +
                        ", date='" + date + '\'';
    }

    //getter
    public String getName() {
        return name;
    }
    //getter
    public String getDay() {
        return day;
    }
    //getter
    public String getDate() {
        return date;
    }
    //getter
    public ArrayList<File> getStuents() {
        return stuents;
    }

    //setter
    public void setStuents(ArrayList<File> stuents) {
        this.stuents = stuents;
    }
}
