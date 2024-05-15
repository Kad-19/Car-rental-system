import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public abstract class ManageCars {
    public static void insertCar(Car car){
        try{
            Connection conn = DatabaseManagement.createConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate("Insert into Cars values ('" + car.getRegno() + "', '" + car.getBrand() + "', '" + car.getModel() + "', '" + car.getStatus() + "', " + car.getPrice() + ")");
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void updateCar(String regno, String brand, String model, String status, float price){
        try{
            Connection conn = DatabaseManagement.createConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate("Update Cars Set Brand = '" + brand + "', Model = '" + model + "', Status = '" + status + "', Price = " + price + " Where RegNo = '" + regno + "'");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void updateCarStatus(String regno, String status){
        try{
            Connection conn = DatabaseManagement.createConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate("Update Cars Set Status = '" + status + "' Where RegNo = '" + regno + "'");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteCar(String regno){
        try{
            Connection conn = DatabaseManagement.createConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate("Delete from Cars Where RegNo = '" + regno + "'");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Car getCar(String regno){
        Car car = new Car();
        try{
            Connection conn = DatabaseManagement.createConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("Select * from Cars Where RegNo = '" + regno + "'");
            if(result.next()){
                car.setRegno(result.getString("RegNo"));
                car.setBrand(result.getString("Brand"));
                car.setModel(result.getString("Model"));
                car.setStatus(result.getString("Status"));
                car.setPrice((result.getBigDecimal("Price")).floatValue());
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return car;
    }

    public static List<Car> getAllCars(){
        ArrayList<Car> cars = new ArrayList<>();
        try{
            Connection conn = DatabaseManagement.createConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("Select * from Cars");
            while(result.next()){
                Car car = new Car();
                car.setRegno(result.getString("RegNo"));
                car.setBrand(result.getString("Brand"));
                car.setModel(result.getString("Model"));
                car.setStatus(result.getString("Status"));
                car.setPrice((result.getBigDecimal("Price")).floatValue());
                cars.add(car);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return cars;
    }
}
