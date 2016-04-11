/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.person.customer;

import entity.bank.KcoinBank;
import entity.person.address.Address;
import entity.person.fullname.FullName;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author zOzDarKzOz
 */
public class CustomerMember extends Customer implements Serializable {

    private int idCustomerMember;
    private String username;
    private String password;
    private KcoinBank kcoinBank;
    private Date createDate;

    public CustomerMember(int idCustomerMember, String username,
            String password, KcoinBank kcoinBank, Date createDate,
            String phoneNum, String email) {
        super(phoneNum, email);
        this.idCustomerMember = idCustomerMember;
        this.username = username;
        this.password = password;
        this.kcoinBank = kcoinBank;
        this.createDate = createDate;
    }

    public CustomerMember(int idCustomerMember, String username,
            String password, KcoinBank kcoinBank, Date createDate) {
        this.idCustomerMember = idCustomerMember;
        this.username = username;
        this.password = password;
        this.kcoinBank = kcoinBank;
        this.createDate = createDate;
    }

    public CustomerMember(int idCustomerMember, String username,
            String password, KcoinBank kcoinBank, Date createDate,
            int idPerson, FullName fullName, Address address,
            String phoneNum, String email) {
        super(idPerson, fullName, address, phoneNum, email);
        this.idCustomerMember = idCustomerMember;
        this.username = username;
        this.password = password;
        this.kcoinBank = kcoinBank;
        this.createDate = createDate;
    }

    public CustomerMember(int idCustomerMember, String username,
            String password, KcoinBank kcoinBank, Date createDate,
            int idPerson, FullName fullName, Address address, int idCustomer,
            String phoneNum, String email) {
        super(idPerson, fullName, address, idCustomer, phoneNum, email);
        this.idCustomerMember = idCustomerMember;
        this.username = username;
        this.password = password;
        this.kcoinBank = kcoinBank;
        this.createDate = createDate;
    }

    public CustomerMember(int idCustomerMember, String username,
            String password, KcoinBank kcoinBank, Date createDate,
            FullName fullName, Address address,
            String phoneNum, String email) {
        super(fullName, address, phoneNum, email);
        this.idCustomerMember = idCustomerMember;
        this.username = username;
        this.password = password;
        this.kcoinBank = kcoinBank;
        this.createDate = createDate;
    }

    public CustomerMember(int idCustomerMember, String username,
            String password, KcoinBank kcoinBank, Date createDate,
            int idCustomer, String phoneNum, String email) {
        super(idCustomer, phoneNum, email);
        this.idCustomerMember = idCustomerMember;
        this.username = username;
        this.password = password;
        this.kcoinBank = kcoinBank;
        this.createDate = createDate;
    }

    public CustomerMember(String username, String password,
            KcoinBank kcoinBank, Date createDate,
            String phoneNum, String email) {
        super(phoneNum, email);
        this.username = username;
        this.password = password;
        this.kcoinBank = kcoinBank;
        this.createDate = createDate;
    }

    public CustomerMember(String username, String password,
            KcoinBank kcoinBank, Date createDate) {
        this.username = username;
        this.password = password;
        this.kcoinBank = kcoinBank;
        this.createDate = createDate;
    }

    public CustomerMember(String username, String password,
            KcoinBank kcoinBank, Date createDate,
            int idPerson, FullName fullName, Address address,
            String phoneNum, String email) {
        super(idPerson, fullName, address, phoneNum, email);
        this.username = username;
        this.password = password;
        this.kcoinBank = kcoinBank;
        this.createDate = createDate;
    }

    public CustomerMember(String username, String password,
            KcoinBank kcoinBank, Date createDate,
            FullName fullName, Address address, String phoneNum, String email) {
        super(fullName, address, phoneNum, email);
        this.username = username;
        this.password = password;
        this.kcoinBank = kcoinBank;
        this.createDate = createDate;
    }

    public CustomerMember(String username, String password,
            KcoinBank kcoinBank, Date createDate,
            int idCustomer, String phoneNum, String email) {
        super(idCustomer, phoneNum, email);
        this.username = username;
        this.password = password;
        this.kcoinBank = kcoinBank;
        this.createDate = createDate;
    }

    public CustomerMember() {
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public int getIdCustomerMember() {
        return idCustomerMember;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public KcoinBank getKcoinBank() {
        return kcoinBank;
    }

    public void setIdCustomerMember(int idCustomerMember) {
        this.idCustomerMember = idCustomerMember;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setKcoinBank(KcoinBank kcoinBank) {
        this.kcoinBank = kcoinBank;
    }

    @Override
    public String getCustomerType() {
        return "customerMember";
    }

//    @Override
//    public CustomerNotMember createCustomerNotMember() {
//        return null;
//    }
//
//    @Override
//    public CustomerMember createCustomerMember(int idCustomerMember, String username, String password, KcoinBank kcoinBank, Date createDate, int idPerson, FullName fullName, Address address, int idCustomer, String phoneNum, String email) {
//        return new CustomerMember(idCustomerMember, username, password, kcoinBank, createDate, idPerson, fullName, address, idCustomer, phoneNum, email);
//    }
//
//    @Override
//    public CustomerMember createCustomerMember() {
//        return new CustomerMember();
//    }
    @Override
    public String getCustomerMemberUsername() {
        return this.username;
    }

    @Override
    public String getCustomerMemberPassword() {
        return this.password;
    }

    @Override
    public void setCustomerMemberUsername(String username) {
        this.setUsername(username);
    }

    @Override
    public void setCustomerMemberPassword(String password) {
        this.setPassword(password);
    }

    @Override
    public int getCustomerMemberId() {
        return this.idCustomerMember;
    }

}
