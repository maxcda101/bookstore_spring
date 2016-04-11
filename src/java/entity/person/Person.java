/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.person;

import entity.person.address.Address;
import entity.person.fullname.FullName;
import java.io.Serializable;

/**
 *
 * @author zOzDarKzOz
 */
public class Person implements Serializable {

    private int idPerson;
    private FullName fullName;
    private Address address;

    public Person() {
    }

    public Person(int idPerson, FullName fullName, Address address) {
        this.idPerson = idPerson;
        this.fullName = fullName;
        this.address = address;
    }

    public Person(FullName fullName, Address address) {
        this.fullName = fullName;
        this.address = address;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public FullName getFullName() {
        return fullName;
    }

    public Address getAddress() {
        return address;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
}
