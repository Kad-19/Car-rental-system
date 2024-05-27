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
import services.ManageCustomers;

import java.io.IOException;
import java.util.List;

import beans.Customer;

public class CustomerListController {
    private Stage mainwindow;

    public void setMainwindow(Stage mainwindow) {
        this.mainwindow = mainwindow;
    }

    @FXML
    private TableView<Customer> customerTableView;

    @FXML
    private TableColumn<Customer, String> usernameColumn;

    @FXML
    private TableColumn<Customer, String> nameColumn;

    @FXML
    private TableColumn<Customer, String> emailColumn;

    @FXML
    private TableColumn<Customer, String> addressColumn;

    @FXML
    private TableColumn<Customer, Void> actionColumn;

    @FXML
    void onGobackClick(ActionEvent event) throws IOException {
        GUI.AdminDashboard(mainwindow);
    }

    @FXML
    public void initialize() {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        addButtonToTable();

        loadCustomers();
    }

    private void loadCustomers() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        List<Customer> allCustomers = ManageCustomers.getAllCustomer();
        customers.addAll(allCustomers);
        customerTableView.setItems(customers);
    }

    private void addButtonToTable() {
        Callback<TableColumn<Customer, Void>, TableCell<Customer, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Customer, Void> call(final TableColumn<Customer, Void> param) {
                final TableCell<Customer, Void> cell = new TableCell<>() {
                    private final Button deleteButton = new Button("Delete");

                    {
                        deleteButton.setOnAction((ActionEvent event) -> {
                            Customer customer = getTableView().getItems().get(getIndex());
                            deleteCustomer(customer);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(deleteButton);
                        }
                    }
                };
                return cell;
            }
        };

        actionColumn.setCellFactory(cellFactory);
    }

    private void deleteCustomer(Customer customer) {
        boolean confirmed = ConfirmationBox.showConfirmation("Confirm Delete", "Are you sure you want to delete this Customer?");
        if (confirmed) {
            ManageCustomers.deleteCustomer(customer.getAccount().getUsername());
            AlertBox.showAlert("Delete", "The Customor was Succesfuly deleted");
        } 
        loadCustomers(); 
    }

    @FXML
    void onAddCustomerClick(ActionEvent event) {
      
        loadCustomers();
    }
    @FXML
    void onDeleteButtonClick(ActionEvent event) {

    }
}
