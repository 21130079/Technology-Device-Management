package com.example.technologydevicemanagement.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Account {
    private String username;
    private String passwd;
    private ArrayList<String> roles ;

    public Account(String username, String passwd, ArrayList<String> roles) {
        this.username = username;
        this.passwd = passwd;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", passwd='" + passwd + '\'' +
                ", roles=" + roles +
                '}';
    }

    public StringProperty usernameProperty() {
        return new SimpleStringProperty(username);
    }
    public StringProperty passwordProperty() {
        return new SimpleStringProperty(passwd);
    }
    public StringProperty rolesProperty() {
        return new SimpleStringProperty(roles.toString());
    }

}