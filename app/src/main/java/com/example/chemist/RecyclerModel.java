package com.example.chemist;

public class RecyclerModel {

    String employee_id;
    String employee_name;
    String employee_email;
    String employee_branch;
    String employee_phone;
    String employee_url;

    RecyclerModel(){

    }

    public RecyclerModel(String employee_id, String employee_name, String employee_email, String employee_branch, String employee_phone, String employee_url) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.employee_email = employee_email;
        this.employee_branch = employee_branch;
        this.employee_phone = employee_phone;
        this.employee_url = employee_url;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_email() {
        return employee_email;
    }

    public void setEmployee_email(String employee_email) {
        this.employee_email = employee_email;
    }

    public String getEmployee_branch() {
        return employee_branch;
    }

    public void setEmployee_branch(String employee_branch) {
        this.employee_branch = employee_branch;
    }

    public String getEmployee_phone() {
        return employee_phone;
    }

    public void setEmployee_phone(String employee_phone) {
        this.employee_phone = employee_phone;
    }

    public String getEmployee_url() {
        return employee_url;
    }

    public void setEmployee_url(String employee_url) {
        this.employee_url = employee_url;
    }


}
