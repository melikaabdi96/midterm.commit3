package com.company.model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * This class holds student information
 *
 * @author Melika
 * @since 2020
 */
public class StudentModel implements Serializable {

    private String username;
    private String password;
    private ArrayList<File> classes;
    private String balance;
    private String grade;
    private String date;

    /**
     * create a student
     * @param username of student
     * @param password of student
     * @param date of day
     */
    public StudentModel(String username, String password, String date) {
        this.username = username;
        this.password = password;
        this.classes = new ArrayList<>();
        this.balance = null;
        this.grade = null;
        this.date = date;
    }

    @Override
    public String toString() {
        return
                "Student:" +
                        "username='" + username + '\'' +
                        ", password='" + password + '\'' +
                        ", classes'" + classes + '\'' +
                        ", balance='" + balance + '\'' +
                        ", grade='" + grade + '\'' +
                        ", date='" + date + '\'';
    }

    //getter
    public String getPassword() {
        return password;
    }
    //getter
    public String getDate() {
        return date;
    }
    //getter
    public String getUsername() {
        return username;
    }
    //getter
    public ArrayList<File> getClasses() {
        return classes;
    }
    //getter
    public String getBalance() {
        return balance;
    }
    //getter
    public String getGrade() {
        return grade;
    }
    //setter
    public void setUsername(String username) {
        this.username = username;
    }
    //setter
    public void setPassword(String password) {
        this.password = password;
    }
    //setter
    public void setBalance(String balance) {
        this.balance = balance;
    }
    //setter
    public void setGrade(String grade) {
        this.grade = grade;
    }
}



