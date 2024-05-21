import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

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
    void goToSignup(ActionEvent event) throws IOException {
        GUI.signupPage(mainwindow);
    }

    @FXML
    void onLoginClick(ActionEvent event) {
        if(userNameField == null || passwordField == null){
            showAlert("Error", "Please enter both username and password.");
            return;
        }
        String username = userNameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Please enter both username and password.");
            return;
        }

        try {
            Customer customer = ManageCustomers.getCustomer(username);
            if (customer != null) {
                if (password.equals(customer.getAccount().getPassword())) {
                    showAlert("Success", "Login successful!");
                    GUI.CustomerDashboard(mainwindow, username);
                } else {
                    showAlert("Error", "Incorrect password. Please try again.");
                }
            } else {
                showAlert("Error", "User not found. Please try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while trying to log in. Please try again.");
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
