package app;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ManageCustomers;

public class ChangePasswordController {
    private Stage mainwindow;
    private String userName;

    public void setMainwindow(Stage mainwindow) {
        this.mainwindow = mainwindow;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        updateUserName();
    }

    @FXML
    private TextField passwordField;

    @FXML
    private Label userNameLabel;

    @FXML
    void onGobackClick(ActionEvent event) throws IOException {
        GUI.CustomerDashboard(mainwindow, userName);
    }

    @FXML
    void onLoginClick(ActionEvent event) throws IOException {
        String password = passwordField.getText();
        if(password != ""){
            ManageCustomers.updatePassword(userName, password);
            AlertBox.showAlert("Conformation", "You have successfully changed your password");
            GUI.CustomerDashboard(mainwindow, password);
        }else{
            AlertBox.showAlert("Warning", "Please enter your password");
            
        }
    }

    public void updateUserName(){
        userNameLabel.setText(userName);
    }

}

