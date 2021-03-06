/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.book;

import dao.SingleDBConnection;
import entity.book.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import tool.MyTool;

/**
 *
 * @author txtd1
 */
public class CategoryDAO {

    private Connection conn = null;

    public CategoryDAO() {
        this.conn = SingleDBConnection.getMyDBConnection().getConnection();
    }

    public ArrayList<Category> getAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Category> list = new ArrayList<>();
        String sqlSelect = "SELECT idCategory, name, description FROM tblCategory;";
        try {
            ps = conn.prepareStatement(sqlSelect);
            rs = ps.executeQuery();
            while (rs.next()) {
                String sortLink = MyTool.convertString(rs.getString(2));
                list.add(new Category(rs.getInt(1), rs.getString(2), rs.getString(3), sortLink));
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
}
