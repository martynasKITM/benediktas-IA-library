package com.benedict.minibank.Controllers;

import com.benedict.minibank.Models.Model;
import com.benedict.minibank.Views.MenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    public Button create_client_btn;
    public Button clients_btn;
    public Button deposit_btn;
    public Button logout_btn;
    public Button authors_btn;


    @Override
    public void initialize( URL url, ResourceBundle resourceBundle) {
        addListeners();
    }


    private void addListeners(){
        authors_btn.setOnAction(event->onAuthor());
        logout_btn.setOnAction(event->onLogout());
    }

    private void onAuthor(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(MenuOptions.AUTHORS);
    }



    private void onCreateClient(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(MenuOptions.CREATE_CLIENT);
    }

    private void onClient(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(MenuOptions.CLIENTS);
    }

    private void onDeposit(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(MenuOptions.DEPOSIT);
    }

    private void onLogout(){
        //Get stage
        Stage stage = (Stage) authors_btn.getScene().getWindow();
        //Close client window
        Model.getInstance().getViewFactory().closeStage(stage);
        //Show Login Window
        Model.getInstance().getViewFactory().showLoginWindow();
        //Set flag to false
        Model.getInstance().setClientAdminSuccessFlag(false);

    }


}
