package com.company.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This class holds class information
 *
 * @author Melika
 * @since 2020
 */
public class ClassModel implements Serializable {
    private String name;
    private String unit;
    private String time;
    private String day;
    private String capacity;
    private String grade;
    private String date;
    private ProfessorModel professorModel;
    private HashMap<StudentModel, String> students;

    /**
     * create a class
     * @param name of class
     * @param unit of class
     * @param professorModel of class
     * @param time of class
     * @param day of class
     * @param capacity of class
     * @param date of day
     */
    public ClassModel (String name, String unit, ProfessorModel professorModel, String time, String day, String capacity, String date) {
        this.name = name;
        this.unit = unit;
        this.time = time;
        this.capacity = capacity;
        this.grade = null;
        this.date = date;
        this.professorModel = professorModel;
        this.students = new HashMap<StudentModel, String>();
    }

    @Override
    public String toString() {
        return
                "Class :" +
                        "name='" + name + '\'' +
                        ", unit='" + unit + '\'' +
                        ", professor='" + professorModel + '\'' +
                        ", time='" + time + '\'' +
                        ", day='" + day + '\'' +
                        ", capacity='" + capacity + '\'' +
                        ", student and grades='" + students + '\'' +
                        ", date='" + date + '\'';
    }


    //getter
    public String getDate() {
        return date;
    }
    //getter
    public String getName() {
        return name;
    }
    //getter
    public String getCapacity() {
        return capacity;
    }
    //getter
    public String getTime() {
        return time;
    }
    //getter
    public String getUnit() {
        return unit;
    }
}
