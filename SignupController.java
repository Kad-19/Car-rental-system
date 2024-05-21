import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignupController {
    private Stage mainwindow;

    public void setMainwindow(Stage mainwindow) {
        this.mainwindow = mainwindow;
    }

    @FXML
    private TextField addressField;

    @FXML
    private TextField confirmPasswordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField userNameField;

    @FXML
    void goToLogin(ActionEvent event) throws IOException {
        GUI.loginPage(mainwindow);
    }

    @FXML
    void onSubmit(ActionEvent event) {
        String name = nameField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String username = userNameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (nameField == null || 
            emailField == null ||  
            addressField == null || 
            userNameField == null ||
            passwordField == null || 
            confirmPasswordField == null || confirmPassword.isEmpty()) {

            showAlert("Error", "All fields must be filled out.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert("Error", "Passwords do not match.");
            return;
        }

        Customer newCustomer = new Customer(name, email, address, username, password);
        ManageCustomers.insertCustomer(newCustomer);

        showAlert("Success", "Account created successfully!");
        try {
            GUI.loginPage(mainwindow);
        } catch (IOException e) {
            e.printStackTrace();
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
