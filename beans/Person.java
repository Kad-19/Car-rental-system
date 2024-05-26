package beans;
public abstract class Person {
    protected String name, email, address;
    protected Account account = new Account();

    public void setAccount(String userName, String password){
        account.setUserName(userName);
        account.setPassword(password);
    }
    public Account getAccount(){
        return account;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getName(){ return name; }
    public String getEmail(){ return email; }
    public String getAddress(){ return address; }
}
