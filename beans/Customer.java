package beans;
public class Customer extends Person {
    public Customer(String name, String email, String address, String userName, String password){
        this.name = name;
        this.email = email;
        this.address = address;
        this.setAccount(userName, password);
    }
    public Customer(){
        this.getAccount().setUserName("");
    }
    public String getUsername() {
        return this.getAccount().getUsername();
    }
}    
