package beans;
public class Adminstrator extends Person {
    private String adminId;

    public void setAdminId(String adminId){
        this.adminId = adminId;
    }
    public String getAdminId(){
        return adminId;
    }

    public Adminstrator(String name, String email, String address, String userName, String password){
        this.name = name;
        this.email = email;
        this.address = address;
        this.setAccount(userName, password);
    }
    public Adminstrator(){
        this.getAccount().setUserName("None");
    }
}
