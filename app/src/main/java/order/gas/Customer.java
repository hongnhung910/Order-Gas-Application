package order.gas;

public class Customer {
    String name;
    String phone;
    String address;
    String password;
    String username;

    public Customer(String name, String phone, String address, String password, String username) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.username = username;
    }
    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
