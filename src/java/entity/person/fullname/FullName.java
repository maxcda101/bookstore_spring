/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.person.fullname;

import java.io.Serializable;

/**
 *
 * @author zOzDarKzOz
 */
public class FullName implements Serializable, FullNameItf {

    private int idFullName;
    private String firstName;
    private String middleName;
    private String lastName;

    public FullName(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public FullName() {
    }

    public FullName(
            int idFullName, String firstName, String middleName, String lastName
    ) {
        this.idFullName = idFullName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public int getIdFullName() {
        return idFullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setIdFullName(int idFullName) {
        this.idFullName = idFullName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getFullName(
            String firstName, String middleName, String lastName
    ) {
        FullNameItf fnItf = new FullNameAdapter(firstName, middleName, lastName);
        return fnItf.getFullName(firstName, middleName, lastName);
    }
}
