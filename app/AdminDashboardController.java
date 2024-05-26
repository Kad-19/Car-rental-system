package app;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AdminDashboardController {
    private Stage mainwindow;

    public void setMainwindow(Stage mainwindow) {
        this.mainwindow = mainwindow;
    }


    @FXML
    void onCarsClick(ActionEvent event) throws IOException {
        GUI.CarList(mainwindow);
    }
    @FXML
    void onLogout(ActionEvent event) throws IOException {
        GUI.AdminLogin(mainwindow);
    }

    @FXML
    void onCustomersClick(ActionEvent event) throws IOException {
        GUI.CustomerList(mainwindow);
    }

    @FXML
    void onGeneratereportClick(ActionEvent event) {

    }

    @FXML
    void onRentsClick(ActionEvent event) throws IOException {
        GUI.RentalList(mainwindow);
    }

}
