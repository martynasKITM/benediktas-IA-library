package com.benedict.minibank.Controllers;
import com.benedict.minibank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import java.net.URL;
import java.util.ResourceBundle;

public class RouteController implements Initializable {

    public BorderPane admin_parent;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getuserSelectedMenuItem().addListener(( observableValue, oldVal, newVal) -> {
            switch (newVal){
                case AUTHORS -> admin_parent.setCenter(Model.getInstance().getViewFactory().getAuthorsView());
                case CREATE_AUTHOR -> admin_parent.setCenter(Model.getInstance().getViewFactory().getCreateAuthorView());
                case INCOME -> admin_parent.setCenter(Model.getInstance().getViewFactory().getIncomeView());
                case CREATE_INCOME -> admin_parent.setCenter(Model.getInstance().getViewFactory().getCreateIncomeView());
                default -> admin_parent.setCenter(Model.getInstance().getViewFactory().getAuthorsView());
            }
        });
    }
}
