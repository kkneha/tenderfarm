package com.example.tenderfarm1;

public class Userdata {
    public String Name;
    public String Email;
    public String Type;
    public String Phone;
    public String Address;

    public Userdata() {
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Userdata(String name, String email, String type, String phone, String address) {
        Name = name;
        Email = email;
        Type = type;
        Phone = phone;
        Address = address;
    }
}
