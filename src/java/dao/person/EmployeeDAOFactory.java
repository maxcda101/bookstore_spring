/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.person;

import dao.person.employee.EmployeeDAO;
import dao.person.customer.CustomerDAO;
import dao.person.employee.SellerDAO;
import dao.person.employee.StaffManagerDAO;
import dao.person.employee.StaffStoreDAO;
import java.io.Serializable;

/**
 *
 * @author zOzDarKzOz
 */
public class EmployeeDAOFactory extends PersonDAOAbstractFactory implements Serializable {

    @Override
    public CustomerDAO getCustomerDAO(String cType) {
        return null;
    }

    @Override
    public EmployeeDAO getEmployeeDAO(String eType) {
        try {
            if (eType == null) {
                return null;
            }
            if (eType.equals("staffManagerDAO")) {
                return new StaffManagerDAO();
            }
            if (eType.equals("staffStoreDAO")) {
                return new StaffStoreDAO();
            }
            if (eType.equals("sellerDAO")) {
                return new SellerDAO();
            }
            if (eType.equals("employeeDAO")) {
                return new EmployeeDAO() {
                    @Override
                    public String getEmployeeDAOType() {
                        return "employeeDAO";
                    }
                };
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
