/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.order;

import dao.SingleDBConnection;
import entity.bank.Bank;
import entity.bank.BankFactory;
import entity.order.Cart;
import entity.order.Payment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author zOzDarKzOz
 */
public class PaymentDAO {

    private Connection conn = null;

    public PaymentDAO() {
        this.conn = SingleDBConnection.getMyDBConnection().getConnection();
    }

    public Payment addPayment(Cart cart, boolean isMb) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        try {
            if (isMb) {
                ps = conn.prepareStatement("INSERT INTO tblBank(blance) VALUES(?);");
                ps.setFloat(1, 50000000);
                int num = ps.executeUpdate();
                if (num == 1) {
                    ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        int idBank = rs.getInt(1);
                        BankFactory bankFactory = new BankFactory();
                        Bank bank = bankFactory.createBank("kcoinBank");
                        ps = conn.prepareStatement("INSERT INTO tblKcoinBank(idBank) VALUES(?);");
                        ps.setInt(1, idBank);
                        num = ps.executeUpdate();
                        if (num == 1) {
                            ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
                            rs1 = ps.executeQuery();
                            if (rs1.next()) {
                                bank.setIdBankOfKcoinBank(idBank);
                                bank.setIdOfKcoinBank(rs1.getInt(1));
                            }
                            ps = conn.prepareStatement("INSERT INTO tblPayment(idBank, idCart) VALUES(?, ?);");
                            ps.setInt(1, idBank);
                            ps.setInt(2, cart.getIdCart());
                            num = ps.executeUpdate();
                            if (num == 1) {
                                ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
                                rs2 = ps.executeQuery();
                                if (rs2.next()) {
                                    return new Payment(rs2.getInt(1), cart, bank);
                                }
                            }
                        }
                    }
                }
            } else {
                ps = conn.prepareStatement("INSERT INTO tblPayment(idCart) VALUES(?);");
                ps.setInt(1, cart.getIdCart());
                int num = ps.executeUpdate();
                if (num == 1) {
                    ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        return new Payment(rs.getInt(1), cart, null);
                    }
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
                if (rs1 != null) {
                    rs1.close();
                }
                if (rs2 != null) {
                    rs2.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Payment getPaymentById(int idPm) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT "
                    + "tblPayment.idBank, "
                    + "tblPayment.idCart "
                    + "FROM tblPayment "
                    + "WHERE tblPayment.idPayment = ?;");
            ps.setInt(1, idPm);
            rs = ps.executeQuery();
            if (rs.next()) {
                BankFactory bankFactory = new BankFactory();
                Bank bank = bankFactory.createBank("kcoinBank");
                bank.setIdBankOfKcoinBank(rs.getInt(1));
                Cart cart = new CartDAO().getCarSavedtById(rs.getInt(2));
                return new Payment(idPm, cart, bank);
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
