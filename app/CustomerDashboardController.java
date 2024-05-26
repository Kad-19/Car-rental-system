package app;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CustomerDashboardController {
    private Stage mainwindow;
    private String userName;

    @FXML
    private Label usernameLabel;

    public void setMainwindow(Stage mainwindow) {
        this.mainwindow = mainwindow;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        updateUsernameLabel();
    }

    private void updateUsernameLabel() {
        if (usernameLabel != null && userName != null) {
            usernameLabel.setText("Welcome, " + userName);
        }
    }

    @FXML
    void onBookacarClick(ActionEvent event) throws IOException {
        GUI.ReturnedCarsList(mainwindow, userName);
    }

    @FXML
    void onRentedcarsClick(ActionEvent event) throws IOException {
        GUI.RentedCarsList(mainwindow, userName);
    }

    @FXML
    void onViewallcarsClick(ActionEvent event) throws IOException {
        GUI.AvailableCarsList(mainwindow, userName);
    }
    @FXML
    void onLogout(ActionEvent event) throws IOException {
        GUI.loginPage(mainwindow);
    }
}
