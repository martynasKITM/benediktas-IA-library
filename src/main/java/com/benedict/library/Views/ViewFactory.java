package com.benedict.library.Views;

import com.benedict.library.Controllers.RouteController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Factory class responsible for managing and displaying various views in the application.
 * This class handles the loading of FXML files and creation of corresponding scenes for different parts of the application.
 */
public class ViewFactory {

    private final ObjectProperty<MenuOptions> userSelectedMenuItem;
    private AnchorPane authorsView;
    private AnchorPane createAuthorView;
    private AnchorPane incomeView;
    private AnchorPane createIncome;
    private AnchorPane dashboard;

    /**
     * Constructor initializing the userSelectedMenuItem property.
     */
    public ViewFactory(){
        this.userSelectedMenuItem = new SimpleObjectProperty<>();
    }

    /**
     * Getter for the userSelectedMenuItem property.
     *
     * @return the ObjectProperty representing the menu option selected by the user
     */
    public ObjectProperty<MenuOptions> getuserSelectedMenuItem(){
        return userSelectedMenuItem;
    }

    /**
     * Displays the login window by loading the corresponding FXML file.
     */
    public void showLoginWindow (){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }

    /**
     * Displays the registration window by loading the corresponding FXML file.
     */
    public void showRegisterWindow (){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Register.fxml"));
        createStage(loader);
    }

    /**
     * Loads and returns the Authors view.
     * If the view is not yet loaded, it loads it from the FXML file.
     *
     * @return the loaded AnchorPane for the Authors view
     */
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

    /**
     * Loads and returns the Create Author view.
     * If the view is not yet loaded, it loads it from the FXML file.
     *
     * @return the loaded AnchorPane for the Create Author view
     */
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

    /**
     * Loads and returns the Income view.
     * If the view is not yet loaded, it loads it from the FXML file.
     *
     * @return the loaded AnchorPane for the Income view
     */
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

    /**
     * Loads and returns the Create Income view.
     * If the view is not yet loaded, it loads it from the FXML file.
     *
     * @return the loaded AnchorPane for the Create Income view
     */
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

    /**
     * Displays the admin window by loading the corresponding FXML file and setting the controller.
     */
    public void showAdminWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Main.fxml"));
        RouteController controller = new RouteController();
        loader.setController(controller);
        createStage(loader);
    }

    /**
     * Creates and displays a new stage with the given FXML loader.
     * Sets the scene, icon, and title for the stage.
     *
     * @param loader the FXMLLoader instance to load the FXML file and create the scene
     */
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

    /**
     * Closes the provided stage.
     *
     * @param stage the stage to close
     */
    public void closeStage(Stage stage){
        stage.close();
    }
}
