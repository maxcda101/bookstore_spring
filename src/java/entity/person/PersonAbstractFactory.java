/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.person;

import entity.person.employee.Employee;
import entity.person.customer.Customer;
import java.io.Serializable;

/**
 *
 * @author zOzDarKzOz
 */
public abstract class PersonAbstractFactory implements Serializable{
    public abstract Customer getCustomer(String cType);
    public abstract Employee getEmployee(String eType);
}
