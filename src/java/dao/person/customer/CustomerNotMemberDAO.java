/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.person.customer;

import entity.person.customer.Customer;
import entity.person.customer.CustomerMember;
import entity.person.customer.CustomerNotMember;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author zOzDarKzOz
 */
public class CustomerNotMemberDAO extends CustomerDAO {

    public CustomerNotMemberDAO() {
        super();
    }

    @Override
    public String getCustomerDAOType() {
        return "customerNotMemberDAO";
    }

    @Override
    public CustomerMember getMemberByUsernameAndPassword(String username, String password) {
        return null;
    }

    @Override
    public boolean addCustomerMember(Customer cus) {
        return false;
    }

    @Override
    public Customer addAndGetCustomer(String phoneNum, String email) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlInsert = "INSERT INTO tblCustomer(phoneNum, email, idPerson) VALUES(?, ?, ?);";
        try {
            ps = this.conn.prepareStatement(sqlInsert);
            ps.setString(1, phoneNum);
            ps.setString(2, email);
            ps.setInt(3, 1);

            int num = ps.executeUpdate();
            if (num == 1) {
                ps = this.conn.prepareStatement("SELECT LAST_INSERT_ID()");
                rs = ps.executeQuery();
                if (rs.next()) {
                    CustomerNotMember cusNb = new CustomerNotMember();
                    cusNb.setPhoneNum(phoneNum);
                    cusNb.setIdCustomer(rs.getInt(1));
                    cusNb.setEmail(email);
                    return cusNb;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
