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
public class ViFullName implements AdvancedFullNameItf, Serializable {

    @Override
    public String getViFullName(String firstName, String middleName, String lastName) {
        return firstName.trim() + " " + middleName.trim() + " " + lastName.trim();
    }

    @Override
    public String getEnFullName(String firstName, String middleName, String lastName) {
        return null;
    }

}
