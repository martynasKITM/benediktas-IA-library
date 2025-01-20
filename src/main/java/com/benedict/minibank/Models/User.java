package com.benedict.minibank.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private  StringProperty username;


    public User(String username) {
        this.username = new SimpleStringProperty(username);
    }

    public String usernameProperty() {
        return  username.get();
    }


}
