/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.person;

import java.io.Serializable;

/**
 *
 * @author zOzDarKzOz
 */
public class PersonDAOFactoryProducer implements Serializable {

    public static PersonDAOAbstractFactory getDAOFactory(String type) {
        try {
            if (type == null) {
                return null;
            }
            if (type.equals("customerDAO")) {
                return new CustomerDAOFactory();
            }
            if (type.equals("employeeDAO")) {
                return new EmployeeDAOFactory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
