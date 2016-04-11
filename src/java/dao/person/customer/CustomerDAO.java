/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.person.customer;

import dao.SingleDBConnection;
import entity.person.customer.Customer;
import java.io.Serializable;
import java.sql.Connection;

/**
 *
 * @author zOzDarKzOz
 */
public abstract class CustomerDAO implements Serializable {

    protected Connection conn = null;

    public CustomerDAO() {
        this.conn = SingleDBConnection.getMyDBConnection().getConnection();
    }

    public abstract Customer getMemberByUsernameAndPassword(String username, String password);

    public abstract Customer addAndGetCustomer(String phoneNum, String email);

    public abstract boolean addCustomerMember(Customer cus);

    public abstract String getCustomerDAOType();

}
