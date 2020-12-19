package com.company.model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * This class holds professor information
 *
 * @author Melika
 * @since 2020
 */
public class ProfessorModel implements Serializable {

    private String username;
    private String password;
    private ArrayList<File> classes;
    private ArrayList<File> students;
    private String date;

    /**
     * create a professor
     * @param username of professor
     * @param password of professor
     * @param date
     */
    public ProfessorModel(String username, String password, String date) {
        this.username = username;
        this.password = password;
        this.classes = new ArrayList<>();
        this.students = new ArrayList<>();
        this.date = date;
    }

    @Override
    public String toString() {
        return
                "Professor:" +
                "username='" + username + '\'' +
                        ", password='" + password + '\'' +
                        ", classes'" + classes + '\'' +
                        ", students='" + students + '\'' +
                        ", date='" + date + '\'';
    }

    public String getPassword() {
        return password;
    }

    public String getDate() {
        return date;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<File> getClasses() {
        return classes;
    }

    public ArrayList<File> getStudents() {
        return students;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}


