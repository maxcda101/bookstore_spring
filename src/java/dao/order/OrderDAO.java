/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.order;

import dao.SingleDBConnection;
import entity.order.Order;
import entity.order.Payment;
import entity.order.ShippingInfor;
import entity.person.customer.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author zOzDarKzOz
 */
public class OrderDAO {

    private Connection conn = null;

    public OrderDAO() {
        this.conn = SingleDBConnection.getMyDBConnection().getConnection();
    }

    public boolean updateState(int id, String state) {
        PreparedStatement ps = null;
        String sqlUpdate = "UPDATE tblOrder SET tblOrder.state = ? WHERE tblOrder.idOrder = ?;";
        try {
            ps = conn.prepareStatement(sqlUpdate);
            ps.setString(1, state);
            ps.setInt(2, id);
            int rs = ps.executeUpdate();
            return (rs == 1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    public boolean addOrder(Order order) throws ParseException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO tblOrder(idPayment, idShippingInfor, createDate, state) "
                    + "VALUES(?, ?, ?, ?);");
            ps.setInt(1, order.getPayment().getId());
            ps.setInt(2, order.getSpif().getIdShippingInfor());
            ps.setDate(3, order.getDate());
            ps.setString(4, order.getState());
            int rs = ps.executeUpdate();
            return (rs == 1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public ArrayList<Order> getListOrderByMbId(Customer cus) {
        PreparedStatement ps = null;
        ResultSet rsOrder = null;
        ArrayList<Order> list = new ArrayList<>();
        try {
            ArrayList<ShippingInfor> listShippingInfor = new ShippingInforDAO().getListShippingInforByMb(cus);
            if (listShippingInfor != null) {
                int listSpIfSize = listShippingInfor.size();
                for (int i = 0; i < listSpIfSize; i++) {
                    ps = conn.prepareStatement("SELECT "
                            + "tblOrder.idOrder, "
                            + "tblOrder.idPayment, "
                            + "tblOrder.createDate, "
                            + "tblOrder.state "
                            + "FROM tblOrder WHERE tblOrder.idShippingInfor = ? "
                            + "ORDER BY tblOrder.createDate DESC;");
                    ps.setInt(1, listShippingInfor.get(i).getIdShippingInfor());
                    rsOrder = ps.executeQuery();
                    if (rsOrder.next()) {
                        int idPm = rsOrder.getInt(2);
                        Payment pm = new PaymentDAO().getPaymentById(idPm);
                        Order order = new Order(rsOrder.getInt(1), pm, listShippingInfor.get(i), rsOrder.getDate(3), rsOrder.getString(4));
                        list.add(order);
                    }
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rsOrder != null) {
                    rsOrder.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<Order> getAllOrder() {
        PreparedStatement ps = null;
        ResultSet rsOrder = null;
        ArrayList<Order> list = new ArrayList<>();
        try {
            ArrayList<ShippingInfor> listShippingInfor = new ShippingInforDAO().getAllShippingInfor();
            if (listShippingInfor != null) {
                int listSpIfSize = listShippingInfor.size();
                for (int i = 0; i < listSpIfSize; i++) {
                    ps = conn.prepareStatement("SELECT "
                            + "tblOrder.idOrder, "
                            + "tblOrder.idPayment, "
                            + "tblOrder.createDate, "
                            + "tblOrder.state "
                            + "FROM tblOrder WHERE tblOrder.idShippingInfor = ?;");
                    ps.setInt(1, listShippingInfor.get(i).getIdShippingInfor());
                    rsOrder = ps.executeQuery();
                    if (rsOrder.next()) {
                        int idPm = rsOrder.getInt(2);
                        Payment pm = new PaymentDAO().getPaymentById(idPm);
                        Order order = new Order(rsOrder.getInt(1), pm, listShippingInfor.get(i), rsOrder.getDate(3), rsOrder.getString(4));
                        list.add(order);
                    }
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rsOrder != null) {
                    rsOrder.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

//    public Order getOrderById(int odId) {
//        PreparedStatement ps = null;
//        ResultSet rsOrder = null;
//        try {
//            ps = conn.prepareStatement("SELECT "
//                    + "tblOrder.idPayment, "
//                    + "tblOrder.idShippingInfor, "
//                    + "tblOrder.createDate, "
//                    + "tblOrder.state "
//                    + "FROM tblOrder WHERE tblOrder.id = ?;");
//            ps.setInt(1, odId);
//            rsOrder = ps.executeQuery();
//            if (rsOrder.next()) {
//                Payment pm = new PaymentDAO(conn).getPaymentById(rsOrder.getInt(1));
//                ShippingInfor spif = new ShippingInforDAO(conn).getShippingInforById(rsOrder.getInt(2));
//                return new Order(odId, pm, spif, rsOrder.getDate(3), rsOrder.getString(4));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (ps != null) {
//                    ps.close();
//                }
//                if (rsOrder != null) {
//                    rsOrder.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
}
