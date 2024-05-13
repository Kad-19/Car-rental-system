import java.util.ArrayList;
import java.util.List;

// Abstract class for managing customers
public abstract class ManageCustomers {
    // Static list to hold customer objects
    private static List<Customer> customerList = new ArrayList<>();

    // Static method to create a new customer object
    public static void createCustomer(String name, String email, String address, String userName, String password) {
        Customer customer = new Customer(name, email, address, userName, password);
        customerList.add(customer);
    }

    // Static method to update an existing customer by username
    public static void updateCustomer(String userName, String name, String email, String address) {
        for (Customer customer : customerList) {
            if (customer.getAccount().getUsername().equals(userName)) {
                customer.setName(name);
                customer.setEmail(email);
                customer.setAddress(address);
                break; // Stop searching after updating the customer
            }
        }
    }

    // Static method to delete a customer by username
    public static void deleteCustomer(String userName) {
        Customer customerToRemove = null;
        for (Customer customer : customerList) {
            if (customer.getAccount().getUsername().equals(userName)) {
                customerToRemove = customer;
                break; // Stop searching after finding the customer to remove
            }
        }
        if (customerToRemove != null) {
            customerList.remove(customerToRemove);
        }
    }

    // Static method to retrieve a customer object by username
    public static Customer getCustomer(String userName) {
        for (Customer customer : customerList) {
            if (customer.getAccount().getUsername().equals(userName)) {
                return customer;
            }
        }
        return null; // Customer not found
    }

    // Static method to retrieve all customers as a list
    public static List<Customer> getAllCustomers() {
        return new ArrayList<>(customerList); // Return a copy of the list to prevent direct modification
    }
}
