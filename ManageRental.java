import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class ManageRental {
    public static void insertRental(Rental rental){
        try{
            Connection conn = DatabaseManagement.createConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate("Insert into Rentals(Username, RegNo, Status, Price, Start_Date, End_Date) values ('" + rental.getUsername() + "', '" + rental.getRegno() + "', '" + rental.getStatus() + "', '" + rental.getPrice() + "', '" + rental.getStartDate() + "', '" + rental.getPrice());
        }
        catch(Exception e){
            e.printStackTrace();
        }
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
