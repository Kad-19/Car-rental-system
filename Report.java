import java.io.*;
import java.util.List;
import java.util.Date;

public abstract class Report {
    public static void carReport() throws IOException{
        String path = "CarReport.txt";
        DataOutputStream output = new DataOutputStream(new FileOutputStream(path, true));
        List<Car> cars = ManageCars.getAllCars();
        Date date = new Date();
        output.writeUTF("\n\n" + date.toString());
        for(Car car : cars){
            output.writeUTF("\n{" + car.getRegno() + ", " + car.getBrand() + ", " + car.getModel() + ", " + car.getStatus() + ", " + car.getPrice() + "}");
        }
        output.close();
    }

    public static void customerReport() throws IOException{
        String path = "CustomerReport.txt";
        DataOutputStream output = new DataOutputStream(new FileOutputStream(path, true));
        List<Customer> customers = ManageCustomers.getAllCustomer();
        Date date = new Date();
        output.writeUTF("\n\n" + date.toString());
        for(Customer customer : customers){
            output.writeUTF("\n{" + customer.getAccount().getUsername() + ", " + customer.getName() + ", " + customer.getEmail() + ", " + customer.getAddress() + "}");
        }
        output.close();
    }

    public static void rentalReport() throws IOException{
        String path = "RentalReport.txt";
        DataOutputStream output = new DataOutputStream(new FileOutputStream(path, true));
        List<Rental> rentals = ManageRental.getAllRental();
        Date date = new Date();
        output.writeUTF("\n\n" + date.toString());
        for(Rental rental : rentals){
            output.writeUTF("\n{" + rental.getRentId() + ", " + rental.getUsername() + ", " + rental.getRegno() + ", " + rental.getStatus() + ", ");
            output.writeUTF(rental.getPrice() + ", " + rental.getStartDate().toString() + ", " + rental.getEndDate().toString() + "}");
        }
        output.close();
    }
}
