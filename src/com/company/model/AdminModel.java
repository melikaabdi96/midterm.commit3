package com.company.model;

import java.io.Serializable;

/**
 * This class holds admin information
 *
 * @author Melika
 * @since 2020
 */
public class AdminModel implements Serializable {

    private String username;
    private String password;
    private String date;

    /**
     * create an admin
     * @param username of admin
     * @param password of admin
     * @param date of day
     */
    public AdminModel (String username, String password, String date) {
        this.username = password;
        this.password =password;
        this.date = date;
    }

    @Override
    public String toString() {
        return
                "Admin :" +
                "username='" + username + '\'' +
                        ", password='" + password + '\'' +
                        ", date='" + date + '\'';
    }

    //getter
    public String getUsername() {
        return username;
    }

    //getter
    public String getDate() {
        return date;
    }

    //getter
    public String getPassword() {
        return password;
    }

    //setter
    public void setPassword(String password) {
        this.password = password;
    }

    //setter
    public void setUsername(String username) {
        this.username = username;
    }
}

