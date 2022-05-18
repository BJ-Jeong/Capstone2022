package com.example.capstone2022;

public class Contactnum {

    private String phone_id;
    private String phone_name;
    private String phone_number;
    private byte user_image;

    public Contactnum(String phone_id, String phone_name, String phone_number, byte user_image){
        this.phone_id = phone_id;
        this.phone_name = phone_name;
        this.phone_number = phone_number;
        this.user_image = user_image;
    }

    public String getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(String phone_id) {
        this.phone_id = phone_id;
    }

    public String getPhone_name() {
        return phone_name;
    }

    public void setPhone_name(String phone_name) {
        this.phone_name = phone_name;
    }



}
