package it.mirea.myapplication.user;

public class User {

    private String name, email, pass, phone;
    private TYPE type;

    public enum TYPE {
        READER,
        AUTHOR,
        SUPERVISOR
    }


    public User() {};

    public User(String name, String email, String pass, String phone, TYPE type) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.phone = phone;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }
}
