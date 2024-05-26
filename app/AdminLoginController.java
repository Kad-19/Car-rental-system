package app;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminLoginController {

    private Stage mainwindow;

    public void setMainwindow(Stage mainwindow) {
        this.mainwindow = mainwindow;
    }

    @FXML
    private TextField passwordField;

    @FXML
    private TextField userNameField;

    @FXML
    void onGobackClick(ActionEvent event) throws IOException {
        GUI.mainScene(mainwindow);
    }

    @FXML
    void onLoginClick(ActionEvent event) {
        if(userNameField == null || passwordField == null){
            showAlert("Error", "Username field must not be empty.");
            return;
        }
        String username = userNameField.getText();
        String password = passwordField.getText();

        if (username == null || username.isEmpty()) {
            showAlert("Error", "Username field must not be empty.");
            return;
        }

        if (password == null || password.isEmpty()) {
            showAlert("Error", "Password field must not be empty.");
            return;
        }

        if (username.equals("admin") && password.equals("admin123")) {
            showAlert("Success", "Login successful!");
            try {
                GUI.AdminDashboard(mainwindow);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Error", "Invalid username or password.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
