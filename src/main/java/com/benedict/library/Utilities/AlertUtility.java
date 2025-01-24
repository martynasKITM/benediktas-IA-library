package com.benedict.library.Utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

/**
 * Utility class for displaying various types of alerts in the application.
 * This class provides methods for showing informational, confirmation, and error dialogs.
 */
public class AlertUtility {

    /**
     * Displays an informational alert with the given content.
     *
     * @param content the content message to display in the alert
     */
    public static void displayInformation(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        styleDialog(alert);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    /**
     * Displays a confirmation alert with the given question and waits for the user's response.
     *
     * @param question the question to ask in the confirmation dialog
     * @return true if the user clicked OK, false otherwise
     */
    public static boolean displayConfirmation(String question) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        styleDialog(alert);
        alert.setContentText(question);
        alert.setHeaderText(null);
        alert.showAndWait();
        return alert.getResult() == ButtonType.OK;
    }

    /**
     * Displays an error alert with the given content.
     *
     * @param content the error message to display in the alert
     */
    public static void displayError(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        styleDialog(alert);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    /**
     * Styles the dialog to apply common styling for all alert types.
     *
     * @param dialog the dialog to style
     */
    private static void styleDialog(Dialog<?> dialog) {
        DialogPane dialogPane = dialog.getDialogPane();
        // Additional styling can be applied here if needed
    }
}
