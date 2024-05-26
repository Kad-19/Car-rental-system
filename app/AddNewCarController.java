package app;

import java.io.IOException;

import beans.Car;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ManageCars;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AddNewCarController {
    private Stage mainwindow;

    public void setMainwindow(Stage mainwindow) {
        this.mainwindow = mainwindow;
    }

    @FXML
    private TextField regnoField;

    @FXML
    private TextField brandField;

    @FXML
    private TextField modelField;

    @FXML
    private TextField priceField;

    @FXML
    void onGobackClick(ActionEvent event) throws IOException {
        GUI.CarList(mainwindow);
    }

    @FXML
    private void handleAddCar() {
        String regno = regnoField.getText();
        String brand = brandField.getText();
        String model = modelField.getText();
        String priceText = priceField.getText();

        if (regno.isEmpty() || brand.isEmpty() || model.isEmpty() || priceText.isEmpty()) {
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

        Car newCar = new Car(regno, brand, model, "Free", price);
        ManageCars.insertCar(newCar);
        showAlert("Success", "Car added successfully!");
        clearFields();
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
        priceField.clear();
    }
}
