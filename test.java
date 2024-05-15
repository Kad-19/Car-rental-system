import java.util.Date;
import java.time.LocalDate;;

public class test {
    public static void main(String[] args) {
        //A class made to test the functionality of the other classes
        
        //Add cars to database
        for(int i = 0; i < 15; ++i){
            Car car = new Car();
            car.setRegno("Car0" + String.valueOf(i));
            car.setBrand("Brand" + String.valueOf(i));
            car.setModel("Model" + String.valueOf(i));
            car.setPrice(20 + (i * 5));
            car.setStatus("Free");
            if(ManageCars.getCar(car.getRegno()).getRegno() == "") ManageCars.insertCar(car);
            else System.out.println("A car with this RegNo already exists");
        }
        //Generate car report
        try{
            Report.carReport();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        //Add customers to database
        for(int i = 0; i < 10; ++i){
            Customer customer = new Customer();
            customer.setAccount("Customer" + String.valueOf(i), "Password" + String.valueOf(i));
            customer.setName("Name " + String.valueOf(i));
            customer.setEmail("asdf" + String.valueOf(i) + "@gmail.com");
            customer.setAddress("Addis Ababa");
            if(ManageCustomers.getCustomer(customer.getAccount().getUsername()).getAccount().getUsername() == ""){
                ManageCustomers.insertCustomer(customer);
            }
            else System.out.println("A customer with this username already exists");
        }
        //Generate customer report
        try{
            Report.customerReport();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        //Add rentals
        for(int i = 0; i < 5; ++i){
            Car car = ManageCars.getCar("Car0" + String.valueOf(3*i));
            Customer customer = ManageCustomers.getCustomer("Customer" + String.valueOf(2*i));
            Date sDate = new Date();
            Date eDate = java.sql.Date.valueOf(LocalDate.now().plusDays(7 + i));
            
            ManageRental.rent(customer, car, sDate, eDate);
        }
        //Generate all reports
        try{
            Report.carReport();
            Report.customerReport();
            Report.rentalReport();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        //Return a car
        ManageRental.returnCar(2);
        //Generate all reports
        try{
            Report.carReport();
            Report.customerReport();
            Report.rentalReport();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
