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

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import beans.Rental;

public class RentalListController {
    private Stage mainwindow;

    public void setMainwindow(Stage mainwindow) {
        this.mainwindow = mainwindow;
    }

    @FXML
    void onGobackClick(ActionEvent event) throws IOException {
        GUI.AdminDashboard(mainwindow);
    }

    @FXML
    private TableView<Rental> rentalTableView;

    @FXML
    private TableColumn<Rental, Integer> idColumn;

    @FXML
    private TableColumn<Rental, String> usernameColumn;

    @FXML
    private TableColumn<Rental, String> regnoColumn;

    @FXML
    private TableColumn<Rental, String> statusColumn;

    @FXML
    private TableColumn<Rental, Double> priceColumn;

    @FXML
    private TableColumn<Rental, String> startDateColumn;

    @FXML
    private TableColumn<Rental, String> endDateColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("rentId"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        regnoColumn.setCellValueFactory(new PropertyValueFactory<>("regno"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        loadRentals();
    }

    private void loadRentals() {
        ObservableList<Rental> rentals = FXCollections.observableArrayList();
        List<Rental> allRentals = ManageRental.getAllRental();
        Collections.reverse(allRentals);
        rentals.addAll(allRentals);
        rentalTableView.setItems(rentals);
    }
}
