package app;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ManageCars;
import services.ManageCustomers;
import services.ManageRental;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import beans.Car;
import beans.Customer;

public class AvailableCarsListController {
    private Stage mainwindow;
    private String username;

    public void setMainwindow(Stage mainwindow) {
        this.mainwindow = mainwindow;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @FXML
    void onGobackClick(ActionEvent event) throws IOException {
        GUI.CustomerDashboard(mainwindow, username);
    }

    @FXML
    private TableView<Car> carsTableView;

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
    public void initialize() {
        regnoColumn.setCellValueFactory(new PropertyValueFactory<>("regno"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        addButtonToTable();

        loadAvailableCars();
    }

    private void loadAvailableCars() {
        ObservableList<Car> availableCars = FXCollections.observableArrayList();
        for (Car car : ManageCars.getAllCars()) {
            if ("Free".equals(car.getStatus())) {
                availableCars.add(car);
            }
        }
        carsTableView.setItems(availableCars);
    }

    private void addButtonToTable() {
        Callback<TableColumn<Car, Void>, TableCell<Car, Void>> cellFactory = new Callback<TableColumn<Car, Void>, TableCell<Car, Void>>() {
            @Override
            public TableCell<Car, Void> call(final TableColumn<Car, Void> param) {
                final TableCell<Car, Void> cell = new TableCell<Car, Void>() {

                    private final Button btn = new Button("Rent");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Car car = getTableView().getItems().get(getIndex());
                            
                            Customer customer = ManageCustomers.getCustomer(username); 
                            Date startDate = getStartDate(); 
                            Date endDate = showDateInputDialog(mainwindow, "Rent a car", "Select your return date");
                            if(endDate != null){

                                ManageRental.rent(customer, car, startDate, endDate);
                                showAlert("Rented", "You have succefuly rented a car");
                                try {
                                    GUI.CustomerDashboard(mainwindow, username);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            else{
                                showAlert("Warning", "Please select valid date");
                            }
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

        actionColumn.setCellFactory(cellFactory);
    }

    
    private Date getStartDate() {
        return new Date();
    }

    private Date getEndDate() {
        return new Date();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public Date showDateInputDialog(Stage owner, String title, String content) {
        Dialog<LocalDate> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(content);

        // Set the button types
        ButtonType okButtonType = new ButtonType("OK", ButtonType.OK.getButtonData());
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonType.CANCEL.getButtonData());
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, cancelButtonType);

        // Create the date picker
        DatePicker datePicker = new DatePicker();
        VBox vbox = new VBox();
        vbox.getChildren().add(datePicker);
        dialog.getDialogPane().setContent(vbox);

        // Convert the result to a LocalDate when the OK button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return datePicker.getValue();
            }
            return null;
        });

        Optional<LocalDate> result = dialog.showAndWait();
        if (result.isPresent()) {
            LocalDate localDate = result.get();
            return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        return null;
    }
}
