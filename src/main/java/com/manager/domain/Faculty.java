package com.manager.domain;

public class Faculty {

    private Integer id;
    private String name;

    private String address;
    private String phoneNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString(){
        return "Id: "+getId()+"; Name: "+getName()+"; Address: "+getAddress()+"; Phone Number: "+getPhoneNumber();
    }

}
