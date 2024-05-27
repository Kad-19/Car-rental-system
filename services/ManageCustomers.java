package services;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Customer;

public abstract class ManageCustomers {
    public static void insertCustomer(Customer customer){
        try{
            Connection conn = DatabaseManagement.createConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate("Insert into Customers values ('" + customer.getAccount().getUsername() + "', '" + customer.getAccount().getPassword() + "', '" + customer.getName() + "', '" + customer.getEmail() + "', '" + customer.getAddress() + "')");
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void updateCustomer(String username, String name, String email, String address){
        try{
            Connection conn = DatabaseManagement.createConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate("Update Customers Set Name = '" + name + "', Email = '" + email + "', Address = '" + address + "' Where Username = '" + username + "'");
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void updatePassword(String username, String password){
        try{
            Connection conn = DatabaseManagement.createConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate("Update Customers Set Password = '" + password + "' Where Username = '" + username + "'");
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteCustomer(String username){
        try{
            Connection conn = DatabaseManagement.createConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate("Delete from Customers Where Username = '" + username + "'");
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Customer getCustomer(String username){
        Customer customer = new Customer();
        try{
            Connection conn = DatabaseManagement.createConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("Select * from Customers Where Username = '" + username + "'");
            if(result.next()){
                customer.setAccount(result.getString("Username"), result.getString("Password"));
                customer.setName(result.getString("Name"));
                customer.setEmail(result.getString("Email"));
                customer.setAddress(result.getString("Address"));
            }
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return customer;
    }

    public static List<Customer> getAllCustomer(){
        ArrayList<Customer> customers = new ArrayList<>();
        try{
            Connection conn = DatabaseManagement.createConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("Select * from Customers");
            while(result.next()){
                Customer customer = new Customer();
                customer.setAccount(result.getString("Username"), result.getString("Password"));
                customer.setName(result.getString("Name"));
                customer.setEmail(result.getString("Email"));
                customer.setAddress(result.getString("Address"));
                customers.add(customer);
            }
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return customers;
    }
}
