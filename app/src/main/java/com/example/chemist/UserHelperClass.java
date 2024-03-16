package com.example.chemist;

public class UserHelperClass {

//    Helper class to help store user data

    String name, email, password, phone, roles;

    //empty constructor to avoid errors
    public UserHelperClass() {
    }

    //constructor
    public UserHelperClass(String name,  String email, String password, String phone, String roles) {
        this.name = name;
        this.roles = roles;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    //Add getters and setters


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
