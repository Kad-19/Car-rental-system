package services;

import java.io.*;
import java.util.List;

import beans.Car;
import beans.Customer;
import beans.Rental;

import java.util.Date;

public abstract class Report {
    public static void carReport(String path) throws IOException{
        PrintWriter output = new PrintWriter(new FileWriter(path));
        List<Car> cars = ManageCars.getAllCars();
        Date date = new Date();
        System.out.println(date.toString());
        output.println(date.toString());
        for(Car car : cars){
            output.println("{" + car.getRegno() + ", " + car.getBrand() + ", " + car.getModel() + ", " + car.getStatus() + ", " + car.getPrice() + "}");
        }
        output.close();
    }

    public static void customerReport(String path) throws IOException{
        PrintWriter output = new PrintWriter(new FileWriter(path));
        List<Customer> customers = ManageCustomers.getAllCustomer();
        Date date = new Date();
        System.out.println(date.toString());
        output.println(date.toString());
        for(Customer customer : customers){
            output.println("{" + customer.getAccount().getUsername() + ", " + customer.getName() + ", " + customer.getEmail() + ", " + customer.getAddress() + "}");
        }
        output.close();
    }

    public static void rentalReport(String path) throws IOException{
        PrintWriter output = new PrintWriter(new FileWriter(path));
        List<Rental> rentals = ManageRental.getAllRental();
        Date date = new Date();
        System.out.println(date.toString());
        output.println(date.toString());
        for(Rental rental : rentals){
            output.println("\n{" + rental.getRentId() + ", " + rental.getUsername() + ", " + rental.getRegno() + ", " + rental.getStatus() + ", " + rental.getPrice() + ", " + rental.getStartDate().toString() + ", " + rental.getEndDate().toString() + "}");
        }
        output.close();
    }
}
