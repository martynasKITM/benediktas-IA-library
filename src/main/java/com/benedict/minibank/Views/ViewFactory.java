package com.benedict.minibank.Views;

import com.benedict.minibank.Controllers.RouteController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {
    private final ObjectProperty<MenuOptions> userSelectedMenuItem;
    private AnchorPane authorsView;
    private AnchorPane createAuthorView;
    private AnchorPane incomeView;
    private AnchorPane createIncome;
    private AnchorPane dashboard;


    public ViewFactory(){
        this.userSelectedMenuItem = new SimpleObjectProperty<>();
    }

    public ObjectProperty<MenuOptions> getuserSelectedMenuItem(){
        return userSelectedMenuItem;
    }


    public void showLoginWindow (){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }

    /*
     * User register
     */

    public void showRegisterWindow (){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Register.fxml"));
        createStage(loader);
    }



    public AnchorPane getAuthorsView() {
        if(authorsView == null){
            try {
                authorsView = new FXMLLoader(getClass().getResource("/Fxml/Authors.fxml")).load();
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return authorsView;
    }

    public AnchorPane getCreateAuthorView() {
        if(createAuthorView == null){
            try {
                createAuthorView = new FXMLLoader(getClass().getResource("/Fxml/CreateAuthor.fxml")).load();
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return createAuthorView;
    }

    public AnchorPane getIncomeView() {
        if(incomeView == null){
            try {
                incomeView = new FXMLLoader(getClass().getResource("/Fxml/Income.fxml")).load();
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return incomeView;
    }

    public AnchorPane getCreateIncomeView() {
        if(createIncome == null){
            try {
                createIncome = new FXMLLoader(getClass().getResource("/Fxml/AddIncome.fxml")).load();
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return createIncome;
    }


    public void showAdminWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Main.fxml"));
        RouteController controller = new RouteController();
        loader.setController(controller);
        createStage(loader);
    }



    public void createStage(FXMLLoader loader){
        Scene scene = null;
        try{
            scene = new Scene(loader.load());
        }catch(Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/icon.png"))));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Benedikto knygynas");
        stage.show();
    }

    public void closeStage(Stage stage){
        stage.close();
    }
}
