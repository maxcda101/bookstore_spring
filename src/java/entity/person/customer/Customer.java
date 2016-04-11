/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.person.customer;

import entity.person.Person;
import entity.person.address.Address;
import entity.person.fullname.FullName;
import java.io.Serializable;

/**
 *
 * @author zOzDarKzOz
 */
public abstract class Customer extends Person implements Serializable {

    protected int idCustomer;
    protected String phoneNum;
    protected String email;

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public Customer(String phoneNum, String email) {
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public Customer() {
    }

    public Customer(
            int idPerson, FullName fullName, Address address,
            String phoneNum, String email
    ) {
        super(idPerson, fullName, address);
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public Customer(
            int idPerson, FullName fullName, Address address,
            int idCustomer, String phoneNum, String email
    ) {
        super(idPerson, fullName, address);
        this.idCustomer = idCustomer;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public Customer(
            FullName fullName, Address address, String phoneNum,
            String email
    ) {
        super(fullName, address);
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public Customer(int idCustomer, String phoneNum, String email) {
        this.idCustomer = idCustomer;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public abstract String getCustomerType();

    public abstract String getCustomerMemberUsername();

    public abstract String getCustomerMemberPassword();

    public abstract int getCustomerMemberId();

    public abstract void setCustomerMemberUsername(String username);

    public abstract void setCustomerMemberPassword(String password);

//    public abstract CustomerMember createCustomerMember();
//    
//    public abstract CustomerMember createCustomerMember(int idCustomerMember, String username,
//            String password, KcoinBank kcoinBank, Date createDate,
//            int idPerson, FullName fullName, Address address, int idCustomer,
//            String phoneNum, String email);
//
//    public abstract CustomerNotMember createCustomerNotMember();
}
