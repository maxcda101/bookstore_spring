/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.order;

import dao.SingleDBConnection;
import entity.order.ShippingInfor;
import entity.person.PersonAbstractFactory;
import entity.person.PersonFactoryProducer;
import entity.person.address.Address;
import entity.person.customer.Customer;
import entity.person.fullname.FullName;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author zOzDarKzOz
 */
public class ShippingInforDAO {

    private Connection conn = null;

    public ShippingInforDAO() {
        this.conn = SingleDBConnection.getMyDBConnection().getConnection();
    }

    public ShippingInfor addMbShippingInfor(String number, String lane,
            String street, String ward, String district, String city,
            String country, Customer cus) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlInsert = "INSERT INTO "
                + "tblShippingInfor(number, lane, street, ward, district, city, country, idCustomer) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            ps = conn.prepareStatement(sqlInsert);
            ps.setString(1, number);
            ps.setString(2, lane);
            ps.setString(3, street);
            ps.setString(4, ward);
            ps.setString(5, district);
            ps.setString(6, city);
            ps.setString(7, country);
            ps.setInt(8, cus.getIdCustomer());

            int num = ps.executeUpdate();
            if (num == 1) {
                ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
                rs = ps.executeQuery();
                if (rs.next()) {
                    return new ShippingInfor(rs.getInt(1), number, lane, street, ward, district, city, country, cus);
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

    public ShippingInfor addShippingInfor(String number, String lane,
            String street, String ward, String district, String city,
            String country) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlInsert = "INSERT INTO "
                + "tblShippingInfor(number, lane, street, ward, district, city, country) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?);";
        try {
            ps = conn.prepareStatement(sqlInsert);
            ps.setString(1, number);
            ps.setString(2, lane);
            ps.setString(3, street);
            ps.setString(4, ward);
            ps.setString(5, district);
            ps.setString(6, city);
            ps.setString(7, country);

            int num = ps.executeUpdate();
            if (num == 1) {
                ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
                rs = ps.executeQuery();
                if (rs.next()) {
                    return new ShippingInfor(rs.getInt(1), number, lane, street, ward, district, city, country);
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

    public ArrayList<ShippingInfor> getListShippingInforByMb(Customer cus) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ShippingInfor> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement("SELECT "
                    + "tblShippingInfor.idShippingInfor, "
                    + "tblShippingInfor.number, "
                    + "tblShippingInfor.lane, "
                    + "tblShippingInfor.street, "
                    + "tblShippingInfor.ward, "
                    + "tblShippingInfor.district, "
                    + "tblShippingInfor.city, "
                    + "tblShippingInfor.country "
                    + "FROM tblShippingInfor JOIN tblCustomerMember "
                    + "ON tblShippingInfor.idCustomer = tblCustomerMember.idCustomer "
                    + "WHERE tblCustomerMember.idCustomer = ?;");
            ps.setInt(1, cus.getIdCustomer());
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ShippingInfor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), cus));
            }
            return list;
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

    public ArrayList<ShippingInfor> getAllShippingInfor() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ShippingInfor> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement("SELECT "
                    + "tblShippingInfor.idShippingInfor, "
                    + "tblShippingInfor.number, "
                    + "tblShippingInfor.lane, "
                    + "tblShippingInfor.street, "
                    + "tblShippingInfor.ward, "
                    + "tblShippingInfor.district, "
                    + "tblShippingInfor.city, "
                    + "tblShippingInfor.country, "
                    + "tblShippingInfor.idCustomer, "
                    + "tblAddress.idAddress, "
                    + "tblAddress.num, "
                    + "tblAddress.lane, "
                    + "tblAddress.street, "
                    + "tblAddress.ward, "
                    + "tblAddress.district, "
                    + "tblAddress.city, "
                    + "tblAddress.country, "
                    + "tblCustomer.phoneNum, "
                    + "tblCustomer.email, "
                    + "tblCustomer.idPerson, "
                    + "tblFullName.idFullName, "
                    + "tblFullName.firstName, "
                    + "tblFullName.middleName, "
                    + "tblFullName.lastName "
                    + "FROM tblShippingInfor JOIN tblCustomer ON "
                    + "tblShippingInfor.idCustomer = tblCustomer.idCustomer "
                    + "JOIN tblPerson ON tblCustomer.idPerson = tblPerson.idPerson "
                    + "JOIN tblAddress ON tblPerson.idAddress = tblAddress.idAddress "
                    + "JOIN tblFullName ON tblPerson.idFullName = tblFullName.idFullName "
                    //                    + "WHERE tblCustomer.idPerson NOT IN (1) "
                    + "ORDER BY tblShippingInfor.idShippingInfor DESC;");
            rs = ps.executeQuery();
            while (rs.next()) {
                Address add = new Address(rs.getInt(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17));
                FullName fullName = new FullName(rs.getInt(21), rs.getString(22), rs.getString(23), rs.getString(24));
                PersonAbstractFactory customerFactory = PersonFactoryProducer.getFactory("customer");
                Customer mb = customerFactory.getCustomer("customerNotMember");
                mb.setAddress(add);
                mb.setEmail(rs.getString(19));
                mb.setIdCustomer(rs.getInt(9));
                mb.setPhoneNum(rs.getString(18));
                mb.setIdPerson(rs.getInt(20));
                mb.setFullName(fullName);
                list.add(new ShippingInfor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), mb));
            }
            return list;
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

//    public ShippingInfor getShippingInforById(int id) {
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            ps = conn.prepareStatement("SELECT "
//                    + "tblShippingInfor.number, "
//                    + "tblShippingInfor.ward, "
//                    + "tblShippingInfor.distric, "
//                    + "tblShippingInfor.city, "
//                    + "tblShippingInfor.idCustomer, "
//                    + "tblCustomer.phoneNumber, "
//                    + "tblCustomer.email, "
//                    + "tblCustomer.fullName, "
//                    + "tblCustomer.idAddress, "
//                    + "tblAddress.number, "
//                    + "tblAddress.ward, "
//                    + "tblAddress.distric, "
//                    + "tblAddress.city "
//                    + "FROM tblShippingInfor JOIN tblCustomer ON "
//                    + "tblShippingInfor.idCustomer = tblCustomer.idCustomer "
//                    + "JOIN tblAddress ON tblCustomer.idAddress = tblAddress.id "
//                    + "WHERE tblShippingInfor.id = ?;");
//            ps.setInt(1, id);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                Address add = new Address(rs.getInt(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13));
//                Customer cus = new Customer(rs.getInt(5), rs.getString(6), rs.getString(7));
//                cus.setFullName(rs.getString(8));
//                cus.setAddress(add);
//                return new ShippingInfor(id, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), cus);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (ps != null) {
//                    ps.close();
//                }
//                if (rs != null) {
//                    rs.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
}
