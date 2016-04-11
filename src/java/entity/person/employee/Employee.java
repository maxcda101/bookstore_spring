/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.person.employee;

import entity.person.Person;
import entity.person.address.Address;
import entity.person.fullname.FullName;
import java.io.Serializable;

/**
 *
 * @author zOzDarKzOz
 */
public abstract class Employee extends Person implements Serializable {

    protected int idEmployee;
    protected String username;
    protected String password;
    protected String phoneNum;
    protected String email;
    protected String eType;

    public Employee(
            int idEmployee, String username, String password,
            String phoneNum, String email, String eType
    ) {
        this.idEmployee = idEmployee;
        this.username = username;
        this.password = password;
        this.phoneNum = phoneNum;
        this.email = email;
        this.eType = eType;
    }

    public Employee(
            int idEmployee, String username, String password, String phoneNum,
            String email, int idPerson, FullName fullName, Address address,
            String eType
    ) {
        super(idPerson, fullName, address);
        this.idEmployee = idEmployee;
        this.username = username;
        this.password = password;
        this.phoneNum = phoneNum;
        this.email = email;
        this.eType = eType;
    }

    public Employee(
            int idEmployee, String username, String password, String phoneNum,
            String email, FullName fullName, Address address, String eType
    ) {
        super(fullName, address);
        this.idEmployee = idEmployee;
        this.username = username;
        this.password = password;
        this.phoneNum = phoneNum;
        this.email = email;
        this.eType = eType;
    }

    public Employee(
            String username, String password, String phoneNum,
            String email, String eType
    ) {
        this.username = username;
        this.password = password;
        this.phoneNum = phoneNum;
        this.email = email;
        this.eType = eType;
    }

    public Employee() {
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public String geteType() {
        return eType;
    }

    public void seteType(String eType) {
        this.eType = eType;
    }
    

//    public abstract Seller createSeller();
//
//    public abstract StaffManager createStaffManager();
//
//    public abstract StaffStore createStaffStore();

    public abstract String getEmployeeType();
}
