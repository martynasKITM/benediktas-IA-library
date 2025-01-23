package com.benedict.minibank.Utilities;
import javafx.scene.control.*;
public class AlertUtility {

    public static void displayInformation(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        styleDialog(alert);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public static boolean displayConfirmation(String question) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        styleDialog(alert);
        alert.setContentText(question);
        alert.setHeaderText(null);
        alert.showAndWait();
        return alert.getResult() == ButtonType.OK;
    }

    public static void displayError(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        styleDialog(alert);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private static void styleDialog(Dialog<?> dialog) {
        DialogPane dialogPane = dialog.getDialogPane();
    }
}
