package com.filet.bioscoopfilet.DomainModel;

import java.io.Serializable;

/**
 * Created by Felix on 28-3-2017.
 */

public class Cinema implements Serializable {

    private int cinemaID;
    private String name;
    private String city;
    private String address;
    private String zipCode;
    private String phone;

    public Cinema(int cinemaID, String name, String city, String address, String zipCode, String phone) {
        this.cinemaID = cinemaID;
        this.name = name;
        this.city = city;
        this.address = address;
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(int cinemaID) {
        this.cinemaID = cinemaID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "cinemaID=" + cinemaID +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
