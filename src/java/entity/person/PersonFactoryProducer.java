/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.person;

import java.io.Serializable;

/**
 *
 * @author zOzDarKzOz
 */
public class PersonFactoryProducer implements Serializable{

    public static PersonAbstractFactory getFactory(String type) {
        try {
            if (type == null) {
                return null;
            }
            if (type.equals("customer")) {
                return new CustomerFactory();
            }
            if (type.equals("employee")) {
                return new EmployeeFactory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
