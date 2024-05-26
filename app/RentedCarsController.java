package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ManageRental;
import beans.Rental;

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
    private TableColumn<Rental, Void> actionsColumn;

    @FXML
    public void initialize() {
        regnoColumn.setCellValueFactory(new PropertyValueFactory<>("regno"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        addActionsColumn();
    }

    public void setUsername(String username) {
        this.username = username;
        loadRentedCars();
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

    private void addActionsColumn() {
        Callback<TableColumn<Rental, Void>, TableCell<Rental, Void>> cellFactory = new Callback<TableColumn<Rental, Void>, TableCell<Rental, Void>>() {
            @Override
            public TableCell<Rental, Void> call(final TableColumn<Rental, Void> param) {
                final TableCell<Rental, Void> cell = new TableCell<Rental, Void>() {

                    private final Button btn = new Button("Return");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Rental rental = getTableView().getItems().get(getIndex());
                            handleReturnRental(rental);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        actionsColumn.setCellFactory(cellFactory);
    }

    private void handleReturnRental(Rental rental) {
        ManageRental.returnCar(rental.getRentId());
        AlertBox.showAlert("Confirmation", "Car returned Successfully");
        loadRentedCars();
    }
}
