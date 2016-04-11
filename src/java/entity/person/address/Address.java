/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.person.address;

import java.io.Serializable;

/**
 *
 * @author zOzDarKzOz
 */
public class Address implements Serializable {

    private int idAddress;
    private String num;
    private String lane;
    private String street;
    private String ward;
    private String district;
    private String city;
    private String country;

    public Address(
            int idAddress, String num, String lane, String street, 
            String ward, String district, String city, String country
    ) {
        this.idAddress = idAddress;
        this.num = num;
        this.lane = lane;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
        this.country = country;
    }

    public Address(
            String num, String lane, String street, String ward, 
            String district, String city, String country
    ) {
        this.num = num;
        this.lane = lane;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
        this.country = country;
    }

    public int getIdAddress() {
        return idAddress;
    }

    public String getNum() {
        return num;
    }

    public String getLane() {
        return lane;
    }

    public String getStreet() {
        return street;
    }

    public String getWard() {
        return ward;
    }

    public String getDistric() {
        return district;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Address() {
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public void setDistric(String district) {
        this.district = district;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
