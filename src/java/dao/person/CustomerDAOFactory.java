/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.person;

import dao.person.employee.EmployeeDAO;
import dao.person.customer.CustomerMemberDAO;
import dao.person.customer.CustomerDAO;
import dao.person.customer.CustomerNotMemberDAO;
import java.io.Serializable;

/**
 *
 * @author zOzDarKzOz
 */
public class CustomerDAOFactory extends PersonDAOAbstractFactory implements Serializable {

    @Override
    public CustomerDAO getCustomerDAO(String cType) {
        try {
            if (cType == null) {
                return null;
            }
            if (cType.equals("customerMemberDAO")) {
                return new CustomerMemberDAO();
            }
            if (cType.equals("customerNotMemberDAO")) {
                return new CustomerNotMemberDAO();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public EmployeeDAO getEmployeeDAO(String eType) {
        return null;
    }

}
