/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.order;

import dao.SingleDBConnection;
import entity.order.BookOrder;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author txtd1
 */
public class BookOrderDAO {

    private Connection conn = null;

    public BookOrderDAO() {
        this.conn = SingleDBConnection.getMyDBConnection().getConnection();
    }

    public void insertBookOrder(BookOrder bod, int idCart) {
        PreparedStatement ps = null;
        String sqlInsert = "INSERT INTO tblBookOrder(quantity, totalPrice, idBook, idCart) VALUES(?, ?, ?, ?);";
        try {
            ps = conn.prepareStatement(sqlInsert);
            ps.setInt(1, bod.getQuantity());
            ps.setFloat(2, bod.getTotalPrice());
            ps.setInt(3, bod.getBook().getIdBook());
            ps.setInt(4, idCart);
            ps.executeUpdate();
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
    }
}
