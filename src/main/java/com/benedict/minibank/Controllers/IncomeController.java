package com.benedict.minibank.Controllers;

import com.benedict.minibank.Models.Model;
import com.benedict.minibank.Views.MenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class IncomeController implements Initializable {
    public Button add_btn;
    public TableView income_table;
    public TableColumn date_col;
    public TableColumn client_col;
    public TableColumn service_col;
    public TableColumn total_col;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        add_btn.setOnAction(event->onCreateIncome());
    }

    public void onCreateIncome(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(MenuOptions.CREATE_INCOME);
    }
}
