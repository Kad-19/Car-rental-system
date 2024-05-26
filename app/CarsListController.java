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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ManageCars;

import java.io.IOException;
import java.util.List;

import beans.Car;

public class CarsListController {

    private Stage mainwindow;

    public void setMainwindow(Stage mainwindow) {
        this.mainwindow = mainwindow;
    }

    @FXML
    private TableView<Car> carTableView;

    @FXML
    private TableColumn<Car, String> regnoColumn;

    @FXML
    private TableColumn<Car, String> brandColumn;

    @FXML
    private TableColumn<Car, String> modelColumn;

    @FXML
    private TableColumn<Car, String> statusColumn;

    @FXML
    private TableColumn<Car, Double> priceColumn;

    @FXML
    private TableColumn<Car, Void> actionColumn;

    @FXML
    void onGobackClick(ActionEvent event) throws IOException {
        GUI.AdminDashboard(mainwindow);
    }

    @FXML
    public void initialize() {
        regnoColumn.setCellValueFactory(new PropertyValueFactory<>("regno"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        addButtonsToTable();

        loadCars();
    }

    private void loadCars() {
        ObservableList<Car> cars = FXCollections.observableArrayList();
        List<Car> allCars = ManageCars.getAllCars();
        cars.addAll(allCars);
        carTableView.setItems(cars);
    }

    private void addButtonsToTable() {
        Callback<TableColumn<Car, Void>, TableCell<Car, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Car, Void> call(final TableColumn<Car, Void> param) {
                final TableCell<Car, Void> cell = new TableCell<>() {

                    private final Button deleteButton = new Button("Delete");
                    private final Button updateButton = new Button("Update");

                    {
                        deleteButton.setOnAction((ActionEvent event) -> {
                            Car car = getTableView().getItems().get(getIndex());
                            deleteCar(car);
                        });

                        updateButton.setOnAction((ActionEvent event) -> {
                            Car car = getTableView().getItems().get(getIndex());
                            updateCar(car);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox actionButtons = new HBox(deleteButton, updateButton);
                            setGraphic(actionButtons);
                        }
                    }
                };
                return cell;
            }
        };

        actionColumn.setCellFactory(cellFactory);
    }

    private void deleteCar(Car car) {
        // Implement your delete logic here
        ManageCars.deleteCar(car.getRegno());
        loadCars(); // Reload the cars to reflect the changes
    }

    private void updateCar(Car car) {
        // Implement your update logic here
        // For example, open a new window with car details for editing
    }

    @FXML
    void onAddCarClick(ActionEvent event) {
        // Implement your logic to open the add car form
        // After adding, you can refresh the car list
        loadCars();
    }
}

