/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.person.employee;

import dao.SingleDBConnection;
import entity.person.address.Address;
import entity.person.employee.Employee;
import entity.person.fullname.FullName;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author zOzDarKzOz
 */
public abstract class EmployeeDAO implements Serializable {

    protected Connection conn = null;

    public EmployeeDAO() {
        this.conn = SingleDBConnection.getMyDBConnection().getConnection();
    }

//    public abstract Employee getEmployeeByUsernameAndPassword(String username, String password);
    
    public Employee getEmployeeByUsernameAndPassword(String username, String password) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlSelect = "SELECT DISTINCT "
                + "tblEmployee.idEmployee, "
                + "tblEmployee.username, "
                + "tblEmployee.password, "
                + "tblEmployee.phoneNum, "
                + "tblEmployee.email, "
                + "tblEmployee.eType, "
                + "tblEmployee.idPerson, "
                + "tblPerson.idFullName, "
                + "tblPerson.idAddress, "
                + "tblAddress.num, "
                + "tblAddress.lane, "
                + "tblAddress.street, "
                + "tblAddress.ward, "
                + "tblAddress.district, "
                + "tblAddress.city, "
                + "tblAddress.country, "
                + "tblFullName.firstName, "
                + "tblFullName.middleName, "
                + "tblFullName.lastName "
                + "FROM "
                + "tblEmployee, tblPerson, tblAddress, tblFullName "
                + "WHERE "
                + "tblEmployee.idPerson = tblPerson.idPerson "
                + "AND "
                + "tblPerson.idFullName = tblFullName.idFullName "
                + "AND "
                + "tblPerson.idAddress = tblAddress.idAddress "
                + "AND "
                + "tblEmployee.username = ? "
                + "AND "
                + "tblEmployee.password = ?;";
        try {
            ps = this.conn.prepareStatement(sqlSelect);
            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();
            if (rs.next()) {
                FullName fullName = new FullName(rs.getInt(8), rs.getString(17), rs.getString(18), rs.getString(19));
                Address address = new Address(rs.getInt(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16));
                return new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(7), fullName, address, rs.getString(6)) {
                    @Override
                    public String getEmployeeType() {
                        return "employee";
                    }
                };
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

    public abstract String getEmployeeDAOType();
}
