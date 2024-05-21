import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class RentedCarsController {
    private Stage mainwindow;

    public void setMainwindow(Stage mainwindow) {
        this.mainwindow = mainwindow;
    }

    private String username;

    @FXML
    void onGobackClick(ActionEvent event) throws IOException {
        GUI.CustomerDashboard(mainwindow, username);
    }
    @FXML
    void onLoadClick(ActionEvent event) throws IOException {
        loadRentedCars();
    }

    @FXML
    private TableView<Rental> rentalsTableView;

    @FXML
    private TableColumn<Rental, String> regnoColumn;

    @FXML
    private TableColumn<Rental, Double> priceColumn;

    @FXML
    private TableColumn<Rental, Date> startDateColumn;

    @FXML
    private TableColumn<Rental, Date> endDateColumn;

    @FXML
    public void initialize() {
        regnoColumn.setCellValueFactory(new PropertyValueFactory<>("regno"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        // loadRentedCars();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private void loadRentedCars() {
        ObservableList<Rental> rentedCars = FXCollections.observableArrayList();
        List<Rental> allRentals = ManageRental.getAllRental();
        for (Rental rental : allRentals) {
            if (username.equals(rental.getUsername()) && "Borrowed".equals(rental.getStatus())) {
                rentedCars.add(rental);
            }
        }
        rentalsTableView.setItems(rentedCars);
    }
}
