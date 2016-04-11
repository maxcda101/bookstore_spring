/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.order;

import entity.person.customer.Customer;
import java.io.Serializable;

/**
 *
 * @author zOzDarKzOz
 */
public class ShippingInfor implements Serializable {

    private int idShippingInfor;
    private String num;
    private String lane;
    private String street;
    private String ward;
    private String district;
    private String city;
    private String country;
    private Customer customer;
    
    public ShippingInfor(int idShippingInfor, String num, String lane, String street, String ward, String district, String city, String country) {
        this.idShippingInfor = idShippingInfor;
        this.num = num;
        this.lane = lane;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
        this.country = country;
    }

    public ShippingInfor(int idShippingInfor, String num, String lane, String street, String ward, String district, String city, String country, Customer customer) {
        this.idShippingInfor = idShippingInfor;
        this.num = num;
        this.lane = lane;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
        this.country = country;
        this.customer = customer;
    }

    public ShippingInfor() {
    }

    public ShippingInfor(String num, String lane, String street, String ward, String district, String city, String country, Customer customer) {
        this.num = num;
        this.lane = lane;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
        this.country = country;
        this.customer = customer;
    }

    public int getIdShippingInfor() {
        return idShippingInfor;
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

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setIdShippingInfor(int idShippingInfor) {
        this.idShippingInfor = idShippingInfor;
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

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
