package com.benedict.minibank.Controllers;

import com.benedict.minibank.Models.Client;
import com.benedict.minibank.Models.Model;
import com.benedict.minibank.Views.ClientCellFactory;
import com.benedict.minibank.Views.MenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthorsController  implements Initializable {
    public ListView clients_listview;
    public Button add_author;

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle) {
       add_author.setOnAction(event->onCreateAuthor());
    }

    private void onCreateAuthor() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(MenuOptions.CREATE_AUTHOR);
  }

}
