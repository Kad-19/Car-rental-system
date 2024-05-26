package beans;
public class Customer extends Person {
    private String username;
    public Customer(String name, String email, String address, String userName, String password){
        this.name = name;
        this.email = email;
        this.address = address;
        this.setAccount(userName, password);
        this.username = userName;
    }
    public Customer(){
        this.getAccount().setUserName("");
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}    
