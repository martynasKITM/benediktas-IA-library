package com.benedict.minibank.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private  StringProperty name;
    private  StringProperty username;
    private  StringProperty password;

    public User(String name, String username, String password) {
        this.name = new SimpleStringProperty(name);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }

    public User(String username) {
        this.username = new SimpleStringProperty(username);
    }

    public String getName() {
        return name.get();
    }

    public String getUsername() {
        return username.get();
    }

    public String getPassword() {
        return password.get();
    }


    public void setName(String name) {
        this.name.set(name);
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }


    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty passwordProperty() {
        return password;
    }
}
