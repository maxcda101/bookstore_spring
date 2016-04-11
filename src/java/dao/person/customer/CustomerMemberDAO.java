/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.person.customer;

import entity.bank.KcoinBank;
import entity.person.address.Address;
import entity.person.customer.Customer;
import entity.person.customer.CustomerMember;
import entity.person.fullname.FullName;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author zOzDarKzOz
 */
public class CustomerMemberDAO extends CustomerDAO {

    public CustomerMemberDAO() {
        super();
    }

    @Override
    public CustomerMember getMemberByUsernameAndPassword(String username, String password) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlSelect = "SELECT DISTINCT "
                + "tblCustomerMember.idCustomerMember, "
                + "tblCustomerMember.userName, "
                + "tblCustomerMember.password, "
                + "tblCustomerMember.idKcoinBank, "
                + "tblCustomerMember.createDate, "
                + "tblKcoinBank.idBank, "
                + "tblBank.blance, "
                + "tblCustomer.idCustomer, "
                + "tblCustomer.phoneNum, "
                + "tblCustomer.email, "
                + "tblCustomer.idPerson, "
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
                + "FROM tblCustomerMember, tblKcoinBank, tblBank, tblCustomer, "
                + "tblPerson, tblAddress, tblFullName "
                + "WHERE "
                + "tblCustomerMember.idKcoinBank = tblKcoinBank.idKcoinBank "
                + "AND "
                + "tblKcoinBank.idBank = tblBank.idBank "
                + "AND "
                + "tblCustomerMember.idCustomer = tblCustomer.idCustomer "
                + "AND "
                + "tblCustomer.idPerson = tblPerson.idPerson "
                + "AND "
                + "tblPerson.idFullName = tblFullName.idFullName "
                + "AND "
                + "tblPerson.idAddress = tblAddress.idAddress "
                + "AND "
                + "tblCustomerMember.userName = ? "
                + "AND "
                + "tblCustomerMember.password = ?;";
        try {
            ps = this.conn.prepareStatement(sqlSelect);
            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();
            if (rs.next()) {
                KcoinBank coinBank = new KcoinBank(rs.getInt(4), rs.getInt(6), rs.getFloat(7));
                FullName fullName = new FullName(rs.getInt(12), rs.getString(21), rs.getString(22), rs.getString(23));
                Address address = new Address(rs.getInt(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20));
                CustomerMember cus = new CustomerMember(rs.getInt(1), rs.getString(2), rs.getString(3), coinBank, rs.getDate(5), rs.getInt(11), fullName, address, rs.getInt(8), rs.getString(9), rs.getString(10));
                return cus;
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

    @Override
    public String getCustomerDAOType() {
        return "customerMemberDAO";
    }

    @Override
    public boolean addCustomerMember(Customer cus) {
        PreparedStatement psAddress = null;
        PreparedStatement psFullName = null;
        PreparedStatement psPerson = null;
        PreparedStatement psCustomer = null;
        PreparedStatement psCustomerMember = null;
        ResultSet rsAddress = null;
        ResultSet rsFullName = null;
        ResultSet rsPerson = null;
        ResultSet rsCustomer = null;
        try {
            psAddress = this.conn.prepareStatement("INSERT INTO "
                    + "tblAddress(num, lane, street, ward, district, city, country) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?);");
            psAddress.setString(1, cus.getAddress().getNum());
            psAddress.setString(2, cus.getAddress().getLane());
            psAddress.setString(3, cus.getAddress().getStreet());
            psAddress.setString(4, cus.getAddress().getWard());
            psAddress.setString(5, cus.getAddress().getDistric());
            psAddress.setString(6, cus.getAddress().getCity());
            psAddress.setString(7, cus.getAddress().getCountry());

            int numAddress = psAddress.executeUpdate();
            if (numAddress == 1) {
                psAddress = conn.prepareStatement("SELECT LAST_INSERT_ID()");
                rsAddress = psAddress.executeQuery();
                if (rsAddress.next()) {
                    int idAddress = rsAddress.getInt(1);
                    psFullName = this.conn.prepareStatement("INSERT INTO "
                            + "tblFullName(firstName, middleName, lastName) "
                            + "VALUES(?, ?, ?);");
                    psFullName.setString(1, cus.getFullName().getFirstName());
                    psFullName.setString(2, cus.getFullName().getMiddleName());
                    psFullName.setString(3, cus.getFullName().getLastName());

                    int numFullName = psFullName.executeUpdate();
                    if (numFullName == 1) {
                        psPerson = conn.prepareStatement("SELECT LAST_INSERT_ID()");
                        rsFullName = psPerson.executeQuery();
                        if (rsFullName.next()) {
                            int idFullName = rsFullName.getInt(1);
                            psPerson = this.conn.prepareStatement("INSERT INTO "
                                    + "tblPerson(idFullName, idAddress) "
                                    + "VALUES(?, ?);");
                            psPerson.setInt(1, idFullName);
                            psPerson.setInt(2, idAddress);
                            int numPerson = psPerson.executeUpdate();
                            if (numPerson == 1) {
                                psCustomer = conn.prepareStatement("SELECT LAST_INSERT_ID()");
                                rsPerson = psCustomer.executeQuery();
                                if (rsPerson.next()) {
                                    int idPerson = rsPerson.getInt(1);
                                    psCustomer = this.conn.prepareStatement("INSERT INTO "
                                            + "tblCustomer(phoneNum, email, idPerson) "
                                            + "VALUES(?, ?, ?);");
                                    psCustomer.setString(1, cus.getPhoneNum());
                                    psCustomer.setString(2, cus.getEmail());
                                    psCustomer.setInt(3, idPerson);

                                    int numCustomer = psCustomer.executeUpdate();
                                    if (numCustomer == 1) {
                                        psCustomerMember = conn.prepareStatement("SELECT LAST_INSERT_ID()");
                                        rsCustomer = psCustomerMember.executeQuery();
                                        if (rsCustomer.next()) {
                                            int idCustomer = rsCustomer.getInt(1);
                                            Calendar c = Calendar.getInstance();
                                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                            java.util.Date date = sdf.parse(sdf.format(c.getTime()));
                                            psCustomerMember = this.conn.prepareStatement("INSERT INTO "
                                                    + "tblCustomerMember(username, password, idKcoinBank, createDate, idCustomer)"
                                                    + "VALUES(?, ?, ?, ?, ?);");
                                            psCustomerMember.setString(1, cus.getCustomerMemberUsername());
                                            psCustomerMember.setString(2, cus.getCustomerMemberPassword());
                                            psCustomerMember.setInt(3, 1);
                                            psCustomerMember.setDate(4, new java.sql.Date(date.getTime()));
                                            psCustomerMember.setInt(5, idCustomer);
                                            int numCustomerMember = psCustomerMember.executeUpdate();
                                            if (numCustomerMember == 1) {
                                                return true;
                                            } else {
                                                psAddress = this.conn.prepareStatement("DELETE FROM tblAddress "
                                                        + "WHERE tblAddress.idAddress = ?;");
                                                psAddress.setInt(1, idAddress);
                                                psAddress.executeUpdate();
                                                psFullName = this.conn.prepareStatement("DELETE FROM tblFullName "
                                                        + "WHERE tblFullName.idFullName = ?;");
                                                psFullName.setInt(1, idFullName);
                                                psFullName.executeUpdate();
                                                psPerson = this.conn.prepareStatement("DELETE FROM tblPerson "
                                                        + "WHERE tblPerson.idPerson = ?;");
                                                psPerson.setInt(1, idPerson);
                                                psPerson.executeUpdate();
                                                psCustomer = this.conn.prepareStatement("DELETE FROM tblCustomer "
                                                        + "WHERE tblCustomer.idCustomer = ?;");
                                                psCustomer.setInt(1, idCustomer);
                                                psCustomer.executeUpdate();
                                            }
                                        }
                                    } else {
                                        psAddress = this.conn.prepareStatement("DELETE FROM tblAddress "
                                                + "WHERE tblAddress.idAddress = ?;");
                                        psAddress.setInt(1, idAddress);
                                        psAddress.executeUpdate();
                                        psFullName = this.conn.prepareStatement("DELETE FROM tblFullName "
                                                + "WHERE tblFullName.idFullName = ?;");
                                        psFullName.setInt(1, idFullName);
                                        psFullName.executeUpdate();
                                        psPerson = this.conn.prepareStatement("DELETE FROM tblPerson "
                                                + "WHERE tblPerson.idPerson = ?;");
                                        psPerson.setInt(1, idPerson);
                                        psPerson.executeUpdate();
                                    }
                                }
                            } else {
                                psAddress = this.conn.prepareStatement("DELETE FROM tblAddress "
                                        + "WHERE tblAddress.idAddress = ?;");
                                psAddress.setInt(1, idAddress);
                                psAddress.executeUpdate();
                                psFullName = this.conn.prepareStatement("DELETE FROM tblFullName "
                                        + "WHERE tblFullName.idFullName = ?;");
                                psFullName.setInt(1, idFullName);
                                psFullName.executeUpdate();
                            }
                        }
                    } else {
                        psAddress = this.conn.prepareStatement("DELETE FROM tblAddress "
                                + "WHERE tblAddress.idAddress = ?;");
                        psAddress.setInt(1, idAddress);
                        psAddress.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (psAddress != null) {
                    psAddress.close();
                }
                if (psFullName != null) {
                    psFullName.close();
                }
                if (psPerson != null) {
                    psPerson.close();
                }
                if (psCustomer != null) {
                    psCustomer.close();
                }
                if (psCustomerMember != null) {
                    psCustomerMember.close();
                }
                if (rsAddress != null) {
                    rsAddress.close();
                }
                if (rsFullName != null) {
                    rsFullName.close();
                }
                if (rsPerson != null) {
                    rsPerson.close();
                }
                if (rsCustomer != null) {
                    rsCustomer.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Customer addAndGetCustomer(String phoneNum, String email) {
        return null;
    }

}
