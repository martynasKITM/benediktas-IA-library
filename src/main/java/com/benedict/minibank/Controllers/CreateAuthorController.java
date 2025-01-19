package com.benedict.minibank.Controllers;

import com.benedict.minibank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CreateAuthorController implements Initializable {
    public TextField fName_fld;
    public TextField lName_fld;
    public TextField email_fld;
    public PasswordField city_fld;
    public Button create_author_btn;
    public Label error_lbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        create_author_btn.setOnAction(event -> createClient());
    }

    private void createClient() {
        String fName = fName_fld.getText();
        String lName = lName_fld.getText();
        String email = email_fld.getText();
        String city  = city_fld.getText();
        Model.getInstance().getDatabaseDriver().createAuthor(fName, lName, email, city, LocalDate.now());
        System.out.println(fName + ", " + lName + ", " + email + ", " + city);
        emptyFields();
        error_lbl.setStyle("-fx-text-fill: blue; -fx-font-size: 1.3em; -fx-font-weight: bold");
        error_lbl.setText("Client Created Successfully!");
    }

    private void emptyFields() {
        fName_fld.setText("");
        lName_fld.setText("");
        email_fld.setText("");
        city_fld.setText("");
    }
}
