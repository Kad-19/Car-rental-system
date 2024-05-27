package app;
import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import services.Report;

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
        saveToDirectory(mainwindow);
    }

    @FXML
    void onRentsClick(ActionEvent event) throws IOException {
        GUI.RentalList(mainwindow);
    }

    public void saveToDirectory(Stage stage) {
        DirectoryChooser directoryChooser = new DirectoryChooser();

        File directory = directoryChooser.showDialog(stage);

        if (directory != null) {
            try {
                Report.carReport(directory.getAbsolutePath() + "/CarReport.txt");
                Report.customerReport(directory.getAbsolutePath() + "/CustomerReport.txt");
                Report.rentalReport(directory.getAbsolutePath() + "/RentalReport.txt");
                AlertBox.showAlert("Confirmation", "Your report is saved at " + directory.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
