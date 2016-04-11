/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.book;

import dao.SingleDBConnection;
import entity.book.Deals;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author zOzDarKzOz
 */
public class DealsDAO {

    private Connection conn = null;

    public DealsDAO() {
        this.conn = SingleDBConnection.getMyDBConnection().getConnection();
    }

    public ArrayList<Deals> getAll() {
        ArrayList<Deals> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlSelect = "SELECT "
                + "tblDeals.idDeals, "
                + "tblDeals.code, "
                + "tblDeals.description, "
                + "tblDeals.discount, "
                + "tblDeals.startDate, "
                + "tblDeals.endDate, "
                + "tblDeals.status "
                + "FROM tblDeals "
                + "ORDER BY tblDeals.startDate;";
        try {
            ps = this.conn.prepareStatement(sqlSelect);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Deals(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5), rs.getDate(6), rs.getInt(7)));
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

    public Deals addDeals(Deals deals) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlInsert = "INSERT INTO "
                + "tblDeals(code, description, discount, startDate, "
                + "endDate, status) VALUES(?, ?, ?, ?, ?, ?);";
        try {
            ps = this.conn.prepareStatement(sqlInsert);
            ps.setString(1, deals.getCode());
            ps.setString(2, deals.getDescription());
            ps.setInt(3, deals.getDiscount());
            ps.setDate(4, deals.getStartDate());
            ps.setDate(5, deals.getEndDate());
            ps.setInt(6, deals.getStatus());

            int num = ps.executeUpdate();
            if (num == 1) {
                ps = this.conn.prepareStatement("SELECT LAST_INSERT_ID()");
                rs = ps.executeQuery();
                if (rs.next()) {
                    int idDeals = rs.getInt(1);
                    deals.setIdDeals(idDeals);
                    return deals;
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

    public Deals updateDeals(Deals deals) {
        PreparedStatement ps = null;
        String sqlUpdate = "UPDATE tblDeals SET "
                + "tblDeals.code = ?, "
                + "tblDeals.description = ?, "
                + "tblDeals.discount = ?, "
                + "tblDeals.startDate = ?, "
                + "tblDeals.endDate = ?, "
                + "tblDeals.status = ? "
                + "WHERE tblDeals.idDeals = ?;";
        try {
            ps = this.conn.prepareStatement(sqlUpdate);
            ps.setString(1, deals.getCode());
            ps.setString(2, deals.getDescription());
            ps.setInt(3, deals.getDiscount());
            ps.setDate(4, deals.getStartDate());
            ps.setDate(5, deals.getEndDate());
            ps.setInt(6, deals.getStatus());
            ps.setInt(7, deals.getIdDeals());

            int num = ps.executeUpdate();
            if (num == 1) {
                return deals;
            }
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
        return null;
    }

    public boolean deleteDeals(int idDeals) {
        PreparedStatement ps = null;
        String sqlDelete = "DELETE FROM tblDeals WHERE tblDeals.idDeals = ?;";
        try {
            ps = this.conn.prepareStatement(sqlDelete);
            ps.setInt(1, idDeals);

            int num = ps.executeUpdate();
            return (num == 1);
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

    public Deals getDealsById(int idDeals) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlSelect = "SELECT "
                + "tblDeals.code, "
                + "tblDeals.description, "
                + "tblDeals.discount, "
                + "tblDeals.startDate, "
                + "tblDeals.endDate, "
                + "tblDeals.status "
                + "FROM tblDeals "
                + "WHERE tblDeals.idDeals = ?;";
        try {
            ps = this.conn.prepareStatement(sqlSelect);
            ps.setInt(1, idDeals);

            rs = ps.executeQuery();
            if (rs.next()) {
                return new Deals(idDeals, rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDate(4), rs.getDate(5), rs.getInt(6));
            }
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
        return null;
    }

    public boolean applyDeals(int idBook, int idDeals) {
        PreparedStatement ps = null;
        String sqlInsert = "INSERT INTO tblBookDeals(idBook, idDeals) "
                + "VALUES(?, ?);";
        try {
            ps = this.conn.prepareStatement(sqlInsert);
            ps.setInt(1, idBook);
            ps.setInt(2, idDeals);

            int num = ps.executeUpdate();
            return (num == 1);
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

    public boolean cancelDeals(int idBook, int idDeals) {
        PreparedStatement ps = null;
        String sqlInsert = "DELETE FROM tblBookDeals "
                + "WHERE tblBookDeals.idBook = ? AND tblBookDeals.idDeals = ?;";
        try {
            ps = this.conn.prepareStatement(sqlInsert);
            ps.setInt(1, idBook);
            ps.setInt(2, idDeals);

            int num = ps.executeUpdate();
            return (num == 1);
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
}
