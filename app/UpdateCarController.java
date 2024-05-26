package app;

import java.io.IOException;

import beans.Car;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ManageCars;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class UpdateCarController {
    private Stage mainwindow;

    public void setMainwindow(Stage mainwindow, String regno) {
        this.mainwindow = mainwindow;
        this.existingCar = ManageCars.getCar(regno);
        setCar(existingCar);
    }

    @FXML
    private TextField regnoField;

    @FXML
    private TextField brandField;

    @FXML
    private TextField modelField;

    @FXML
    private TextField statusField;

    @FXML
    private TextField priceField;

    @FXML
    private Button submitButton;

    private Car existingCar;

    @FXML
    private void initialize() {
        // Initialization if necessary
    }

    public void setCar(Car car) {
        this.existingCar = car;
        if (car != null) {
            regnoField.setText(car.getRegno());
            brandField.setText(car.getBrand());
            modelField.setText(car.getModel());
            statusField.setText(car.getStatus());
            priceField.setText(String.valueOf(car.getPrice()));
            submitButton.setText("Update Car");
        } else {
            submitButton.setText("Add Car");
        }
    }

    @FXML
    private void handleSubmit() {
        String regno = regnoField.getText();
        String brand = brandField.getText();
        String model = modelField.getText();
        String status = statusField.getText();
        String priceText = priceField.getText();

        if (regno.isEmpty() || brand.isEmpty() || model.isEmpty() || status.isEmpty() || priceText.isEmpty()) {
            showAlert("Error", "All fields must be filled out.");
            return;
        }

        float price;
        try {
            price = Float.parseFloat(priceText);
        } catch (NumberFormatException e) {
            showAlert("Error", "Price must be a valid number.");
            return;
        }

        if (existingCar == null) {
            Car newCar = new Car(regno, brand, model, status, price);
            ManageCars.insertCar(newCar);
            showAlert("Success", "Car added successfully!");
        } else {
            existingCar.setBrand(brand);
            existingCar.setModel(model);
            existingCar.setStatus(status);
            existingCar.setPrice(price);
            ManageCars.updateCar(regno, brand, model, status, price);
            showAlert("Success", "Car updated successfully!");
        }

        try {
            GUI.CarList(mainwindow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        regnoField.clear();
        brandField.clear();
        modelField.clear();
        statusField.clear();
        priceField.clear();
        submitButton.setText("Add Car");
        existingCar = null;
    }

    @FXML
    private void onGobackClick() throws IOException {
        GUI.CarList(mainwindow);
    }
}

