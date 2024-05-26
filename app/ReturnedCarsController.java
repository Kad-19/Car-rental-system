package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ManageRental;
import beans.Rental;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ReturnedCarsController {
    private Stage mainwindow;

    public void setMainwindow(Stage mainwindow) {
        this.mainwindow = mainwindow;
    }


    private String username;

    @FXML
    void onGobackClick(ActionEvent event) throws IOException {
        GUI.CustomerDashboard(mainwindow, username);
    }

    public void setUsername(String username) {
        this.username = username;
        loadReturnedRentals();
    }

    @FXML
    private TableView<Rental> rentalsTableView;


    @FXML
    private TableColumn<Rental, String> regnoColumn;

    @FXML
    private TableColumn<Rental, String> statusColumn;

    @FXML
    private TableColumn<Rental, Double> priceColumn;

    @FXML
    private TableColumn<Rental, Date> startDateColumn;

    @FXML
    private TableColumn<Rental, Date> endDateColumn;

    @FXML
    public void initialize() {
        regnoColumn.setCellValueFactory(new PropertyValueFactory<>("regno"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        // loadReturnedRentals();
    }

    private void loadReturnedRentals() {
        ObservableList<Rental> returnedRentals = FXCollections.observableArrayList();
        List<Rental> allRentals = ManageRental.getAllRental();
        for (Rental rental : allRentals) {
            if (username.equals(rental.getUsername()) && "Returned".equals(rental.getStatus())) {
                returnedRentals.add(rental);
            }
        }
        rentalsTableView.setItems(returnedRentals);
    }
}

