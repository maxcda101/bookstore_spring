/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.person;

import dao.person.employee.EmployeeDAO;
import dao.person.customer.CustomerDAO;
import java.io.Serializable;

/**
 *
 * @author zOzDarKzOz
 */
public abstract class PersonDAOAbstractFactory implements Serializable {
    public abstract CustomerDAO getCustomerDAO(String cType);
    public abstract EmployeeDAO getEmployeeDAO(String eType);
}
