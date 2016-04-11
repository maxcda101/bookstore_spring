/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.order;

import dao.SingleDBConnection;
import entity.book.Book;
import entity.book.BookSet;
import entity.book.Category;
import entity.order.BookOrder;
import entity.order.Cart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import tool.MyTool;

/**
 *
 * @author zOzDarKzOz
 */
public class CartDAO {

    private Connection conn = null;

    public CartDAO() {
        this.conn = SingleDBConnection.getMyDBConnection().getConnection();
    }

    public boolean saveCart(Cart cart, int idCus) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlInsertCart = "INSERT INTO tblCart VALUES()";
        try {
            ps = conn.prepareStatement(sqlInsertCart);
            int rs1 = ps.executeUpdate();
            if (rs1 == 1) {
                ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
                rs = ps.executeQuery();
                if (rs.next()) {
                    rs1 = rs.getInt(1);
                    int size = cart.getListBook().size();
                    BookOrderDAO bodDAO = new BookOrderDAO();
                    for (int i = 0; i < size; i++) {
                        bodDAO.insertBookOrder(cart.getListBook().get(i), rs1);
                    }
                    ps = conn.prepareStatement("INSERT INTO tblCartSave(idCart, idCustomerMember) VALUES(?, ?);");
                    ps.setInt(1, rs1);
                    ps.setInt(2, idCus);
                    rs1 = ps.executeUpdate();
                    return (rs1 == 1);
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
        return false;
    }

    public int saveCartAndGetCartId(Cart cart, int idCus) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlInsertCart = "INSERT INTO tblCart VALUES()";
        try {
            ps = conn.prepareStatement(sqlInsertCart);
            int rs1 = ps.executeUpdate();
            if (rs1 == 1) {
                ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
                rs = ps.executeQuery();
                if (rs.next()) {
                    int idCart = rs.getInt(1);
                    int size = cart.getListBook().size();
                    BookOrderDAO bodDAO = new BookOrderDAO();
                    for (int i = 0; i < size; i++) {
                        bodDAO.insertBookOrder(cart.getListBook().get(i), idCart);
                    }
                    ps = conn.prepareStatement("INSERT INTO tblCartSave(idCart, idCustomerMember) VALUES(?, ?);");
                    ps.setInt(1, idCart);
                    ps.setInt(2, idCus);
                    rs1 = ps.executeUpdate();
                    if (rs1 == 1) {
                        return idCart;
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public boolean deleteCartSave(int idCart) {
        PreparedStatement ps = null;
        String sqlDelete = "DELETE FROM tblCartSave WHERE tblCartSave.idCart = ?;";
        try {
            ps = conn.prepareStatement(sqlDelete);
            ps.setInt(1, idCart);
            int rs1 = ps.executeUpdate();
            return (rs1 == 1);
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

    public int addCart(Cart cart) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlInsertCart = "INSERT INTO tblCart VALUES()";
        try {
            ps = conn.prepareStatement(sqlInsertCart);
            int rs1 = ps.executeUpdate();
            if (rs1 == 1) {
                ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
                rs = ps.executeQuery();
                if (rs.next()) {
                    rs1 = rs.getInt(1);
                    int size = cart.getListBook().size();
                    BookOrderDAO bodDAO = new BookOrderDAO();
                    for (int i = 0; i < size; i++) {
                        bodDAO.insertBookOrder(cart.getListBook().get(i), rs1);
                    }
                    return rs1;
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
        return -1;
    }

    public ArrayList<Cart> getListCartByMbId(int mbId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ArrayList<Cart> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement("SELECT tblCartSave.idCart FROM tblCartSave WHERE tblCartSave.idCustomerMember = ?;");
            ps.setInt(1, mbId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idCart = rs.getInt(1);
                ArrayList<BookOrder> listBookOrder = new ArrayList<>();
                float totalPrice = 0;
                ps = conn.prepareStatement("SELECT "
                        + "tblBookOrder.idBookOrder, "
                        + "tblBookOrder.quantity, "
                        + "tblBookOrder.totalPrice, "
                        + "tblBookOrder.idBook "
                        + "FROM tblBookOrder WHERE tblBookOrder.idCart = ?;");
                ps.setInt(1, idCart);
                rs2 = ps.executeQuery();
                while (rs2.next()) {
                    int idBook = rs2.getInt(4);
                    ps = conn.prepareStatement("SELECT DISTINCT "
                            + "tblBook.idBook, "
                            + "tblBook.image, "
                            + "tblBook.title, "
                            + "tblBook.author, "
                            + "tblBook.publisher, "
                            + "tblBook.publishYear, "
                            + "tblBook.description, "
                            + "tblBook.originalPrice, "
                            + "tblBook.salePrice, "
                            + "tblBook.quantity, "
                            + "tblBook.idCategory, "
                            + "tblBook.idBookSet, "
                            + "tblBook.votes, "
                            + "tblBook.ratePoint, "
                            + "tblBookSet.name, "
                            + "tblBookSet.description, "
                            + "tblCategory.name, "
                            + "tblCategory.description "
                            + "FROM tblBook "
                            + "JOIN tblCategory ON tblBook.idCategory = tblCategory.idCategory "
                            + "JOIN tblBookSet ON tblBook.idBookSet = tblBookSet.idBookSet "
                            + "WHERE tblBook.idBook = ?;");
                    ps.setInt(1, idBook);
                    rs1 = ps.executeQuery();
                    if (rs1.next()) {
                        String sortLink = MyTool.convertString(rs1.getString(15));
                        String bsName = rs1.getString(15).substring(8, 9).toUpperCase()
                                + rs1.getString(15).substring(9);
                        BookSet bs = new BookSet(rs1.getInt(12), bsName, rs1.getString(16), sortLink);
                        sortLink = MyTool.convertString(rs1.getString(17));
                        Category ct = new Category(rs1.getInt(11), rs1.getString(17), rs1.getString(18), sortLink);
                        sortLink = MyTool.convertString(rs1.getString(3));
                        Book book = new Book(rs1.getInt(1), rs1.getString(2), rs1.getString(3), rs1.getString(4), rs1.getString(5), rs1.getString(6), rs1.getString(7), rs1.getString(8), rs1.getString(9), rs1.getInt(10), ct, bs, sortLink, rs1.getInt(13), rs1.getFloat(14));
                        listBookOrder.add(new BookOrder(rs2.getInt(1), rs2.getInt(2), rs2.getFloat(3), book));
                        totalPrice += rs2.getFloat(3);
                    }
                }
                Cart cart = new Cart(idCart, listBookOrder, totalPrice);
                list.add(cart);
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

    public Cart getCarSavedtById(int idCart) {
        PreparedStatement ps = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        try {
            ArrayList<BookOrder> listBookOrder = new ArrayList<>();
            float totalPrice = 0;
            ps = conn.prepareStatement("SELECT "
                    + "tblBookOrder.idBookOrder, "
                    + "tblBookOrder.quantity, "
                    + "tblBookOrder.totalPrice, "
                    + "tblBookOrder.idBook "
                    + "FROM tblBookOrder WHERE tblBookOrder.idCart = ?;");
            ps.setInt(1, idCart);
            rs2 = ps.executeQuery();
            while (rs2.next()) {
                int idBook = rs2.getInt(4);
                ps = conn.prepareStatement("SELECT DISTINCT "
                        + "tblBook.idBook, "
                        + "tblBook.image, "
                        + "tblBook.title, "
                        + "tblBook.author, "
                        + "tblBook.publisher, "
                        + "tblBook.publishYear, "
                        + "tblBook.description, "
                        + "tblBook.originalPrice, "
                        + "tblBook.salePrice, "
                        + "tblBook.quantity, "
                        + "tblBook.idCategory, "
                        + "tblBook.idBookSet, "
                        + "tblBook.votes, "
                        + "tblBook.ratePoint, "
                        + "tblBookSet.name, "
                        + "tblBookSet.description, "
                        + "tblCategory.name, "
                        + "tblCategory.description "
                        + "FROM tblBook "
                        + "JOIN tblCategory ON tblBook.idCategory = tblCategory.idCategory "
                        + "JOIN tblBookSet ON tblBook.idBookSet = tblBookSet.idBookSet "
                        + "WHERE tblBook.idBook = ?;");
                ps.setInt(1, idBook);
                rs1 = ps.executeQuery();
                if (rs1.next()) {
                    String sortLink = MyTool.convertString(rs1.getString(15));
                    String bsName = rs1.getString(15).substring(8, 9).toUpperCase()
                            + rs1.getString(15).substring(9);
                    BookSet bs = new BookSet(rs1.getInt(12), bsName, rs1.getString(16), sortLink);
                    sortLink = MyTool.convertString(rs1.getString(17));
                    Category ct = new Category(rs1.getInt(11), rs1.getString(17), rs1.getString(18), sortLink);
                    sortLink = MyTool.convertString(rs1.getString(3));
                    Book book = new Book(rs1.getInt(1), rs1.getString(2), rs1.getString(3), rs1.getString(4), rs1.getString(5), rs1.getString(6), rs1.getString(7), rs1.getString(8), rs1.getString(9), rs1.getInt(10), ct, bs, sortLink, rs1.getInt(13), rs1.getFloat(14));
                    listBookOrder.add(new BookOrder(rs2.getInt(1), rs2.getInt(2), rs2.getFloat(3), book));
                    totalPrice += rs2.getFloat(3);
                }
            }
            if (listBookOrder != null && totalPrice != 0) {
                Cart cart = new Cart(idCart, listBookOrder, totalPrice);
                return cart;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
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
}
