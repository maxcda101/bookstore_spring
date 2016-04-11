/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.order;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author zOzDarKzOz
 */
public class Order implements Serializable {
    
    private int id;
    private Payment payment;
    private ShippingInfor spif;
    private Date date;
    private String state;

    public Order() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setSpif(ShippingInfor spif) {
        this.spif = spif;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public Payment getPayment() {
        return payment;
    }

    public ShippingInfor getSpif() {
        return spif;
    }

    public Date getDate() {
        return date;
    }

    public String getState() {
        return state;
    }

    public Order(Payment payment, ShippingInfor spif, Date date, String state) {
        this.payment = payment;
        this.spif = spif;
        this.date = date;
        this.state = state;
    }

    public Order(int id, Payment payment, ShippingInfor spif, Date date, String state) {
        this.id = id;
        this.payment = payment;
        this.spif = spif;
        this.date = date;
        this.state = state;
    }

}
