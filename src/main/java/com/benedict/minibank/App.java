package com.benedict.minibank;

import com.benedict.minibank.Models.Model;
import com.benedict.minibank.Utilities.UserAuthUtils;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class App extends Application {
    private static final Logger logger = Logger.getLogger(App.class.getName());

    @Override
    public void start( Stage stage){
        Model.getInstance().getViewFactory().showLoginWindow();
        String password = "123456";
        String hashedPassword = UserAuthUtils.hashPassword(password);
        System.out.println("Hashed Password: " + hashedPassword);
        logger.info("Program started");
    }
}
