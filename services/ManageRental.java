package services;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import beans.Car;
import beans.Customer;
import beans.Rental;

import java.util.Date;

public abstract class ManageRental {
    public static void insertRental(Rental rental){
        String sql = "Insert into Rentals(Username, RegNo, Status, Price, Start_Date, End_Date) values (?, ?, ?, ?, ?, ?)";
        try{
            Connection conn = DatabaseManagement.createConnection();

            java.sql.Date sDate = new java.sql.Date(rental.getStartDate().getTime());
            java.sql.Date eDate = new java.sql.Date(rental.getEndDate().getTime());

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, rental.getUsername());
            statement.setString(2, rental.getRegno());
            statement.setString(3, rental.getStatus());
            statement.setFloat(4, rental.getPrice());
            statement.setDate(5, sDate);
            statement.setDate(6, eDate);
            statement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void rent(Customer customer, Car car, Date startDate, Date EndDate){
        Rental rental = new Rental();
        rental.setUsername(customer.getAccount().getUsername());
        rental.setRegno(car.getRegno());
        rental.setStatus("Borrowed");
        rental.setStartDate(startDate);
        rental.setEndDate(EndDate);
        long duration = (EndDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
        float price = car.getPrice();
        if(duration != 0) price *= duration; 
        rental.setPrice(price);
        ManageRental.insertRental(rental);
        ManageCars.updateCarStatus(car.getRegno(), "Rented");
    }

    public static void updateRentalStatus(int id, String status){
        try{
            Connection conn = DatabaseManagement.createConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate("Update Rentals Set Status = '" + status + "' Where Id = " + id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void returnCar(int id){
        Rental rental = ManageRental.getRental(id);
        ManageRental.updateRentalStatus(id, "Returned");
        ManageCars.updateCarStatus(rental.getRegno(), "Free");
    }

    public static void deleteRental(int id){
        try{
            Connection conn = DatabaseManagement.createConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate("Delete from Rentals Where Id = " + id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Rental getRental(int id){
        Rental rental = new Rental();
        try{
            Connection conn = DatabaseManagement.createConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("Select * from Rentals Where Id = " + id);
            if(result.next()){
                rental.setRentId(result.getInt("Id"));
                rental.setUsername(result.getString("Username"));
                rental.setRegno(result.getString("RegNo"));
                rental.setStatus(result.getString("Status"));
                rental.setPrice((result.getBigDecimal("Price")).floatValue());
                rental.setStartDate(result.getDate("Start_Date"));
                rental.setEndDate(result.getDate("End_Date"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return rental;
    }

    public static List<Rental> getAllRental(){
        ArrayList<Rental> rentals = new ArrayList<>();
        try{
            Connection conn = DatabaseManagement.createConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("Select * from Rentals");
            while(result.next()){
                Rental rental = new Rental();
                rental.setRentId(result.getInt("Id"));
                rental.setUsername(result.getString("Username"));
                rental.setRegno(result.getString("RegNo"));
                rental.setStatus(result.getString("Status"));
                rental.setPrice((result.getBigDecimal("Price")).floatValue());
                rental.setStartDate(result.getDate("Start_Date"));
                rental.setEndDate(result.getDate("End_Date"));
                rentals.add(rental);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return rentals;
    }
}
