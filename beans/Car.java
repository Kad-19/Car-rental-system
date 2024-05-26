package beans;
public class Car {
    private String regno;
    private String brand;
    private String model;
    private String status;
    private float price;

    public Car(){
        this.regno = "";
    }
    public Car(String regno, String brand, String model, String status, float price){
        this.regno = regno;
        this.brand = brand;
        this.model = model;
        this.status = status;
        this.price = price;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public void setRegno(String regno) {
        this.regno = regno;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
    }
    public float getPrice() {
        return price;
    }
    public String getRegno() {
        return regno;
    }
    public String getStatus() {
        return status;
    }
}