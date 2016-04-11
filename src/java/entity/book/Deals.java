/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.book;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author zOzDarKzOz
 */
public class Deals implements Serializable{

    private int idDeals;
    private String code;
    private String description;
    private int discount;
    private Date startDate;
    private Date endDate;
    private int status;

    public Deals() {
    }

    public Deals(int idDeals, String code, String description, int discount, Date startDate, Date endDate, int status) {
        this.idDeals = idDeals;
        this.code = code;
        this.description = description;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Deals(String code, String description, int discount, Date startDate, Date endDate, int status) {
        this.code = code;
        this.description = description;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public int getIdDeals() {
        return idDeals;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public int getDiscount() {
        return discount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setIdDeals(int idDeals) {
        this.idDeals = idDeals;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
