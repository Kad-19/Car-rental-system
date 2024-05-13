import java.util.ArrayList;
import java.util.List;

public abstract class ManageCars {
    private static List<Car> carList = new ArrayList<>();

    public static void createCar(String regno, String brand, String model, String status, float price) {
        Car car = new Car(regno, brand, model, status, price);
        carList.add(car);
    }

    public static void updateCar(String regno, String brand, String model, String status, float price) {
        for (Car car : carList) {
            if (car.getRegno().equals(regno)) {
                car.setBrand(brand);
                car.setModel(model);
                car.setStatus(status);
                car.setPrice(price);
                break; // Stop searching after updating the car
            }
        }
    }

    public static void deleteCar(String regno) {
        Car carToRemove = null;
        for (Car car : carList) {
            if (car.getRegno().equals(regno)) {
                carToRemove = car;
                break; // Stop searching after finding the car to remove
            }
        }
        if (carToRemove != null) {
            carList.remove(carToRemove);
        }
    }

    public static Car getCarByRegno(String regno) {
        for (Car car : carList) {
            if (car.getRegno().equals(regno)) {
                return car;
            }
        }
        return null; // Car not found
    }

    public static List<Car> getAllCars() {
        return new ArrayList<>(carList); // Return a copy of the list to prevent direct modification
    }
}

