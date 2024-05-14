import java.util.Date;

public class Rental {
    private int RentId;
    private String username, regno, status;
    private float price;
    private Date startDate, endDate;

    public Rental(){
        this.RentId = 0;
    }

    public Rental(int RentId, String username, String regno, String status, float price, Date startDate, Date endDate){
        this.RentId = RentId;
        this.username = username;
        this.regno = regno;
        this.status = status;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setRentId(int RentId){
        this.RentId = RentId;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setRegno(String regno){
        this.regno = regno;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setPrice(float price){
        this.price = price;
    }

    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }

    public int getRentId(){
        return RentId;
    }

    public String getUsername(){
        return username;
    }

    public String getRegno(){
        return regno;
    }

    public String getStatus(){
        return status;
    }

    public float getPrice(){
        return price;
    }

    public Date getStartDate(){
        return startDate;
    }

    public Date getEndDate(){
        return endDate;
    }
}
