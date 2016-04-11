/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.book;

import dao.SingleDBConnection;
import entity.book.Book;
import entity.book.BookSet;
import entity.book.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import tool.MyTool;

/**
 *
 * @author zOzDarKzOz
 */
public class BookDAO {

    private Connection conn = null;

    public BookDAO() {
        this.conn = SingleDBConnection.getMyDBConnection().getConnection();
    }

    public ArrayList<Book> getListOfBookByNameAndIdCategory(String name, int id) {
        ArrayList<Book> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlSelect = "SELECT DISTINCT "
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
                + "WHERE tblBook.idCategory = ? AND tblBook.title LIKE N'%" + name + "%';";
        try {
            ps = conn.prepareStatement(sqlSelect);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                String sortLink = MyTool.convertString(rs.getString(14));
                String bsName = rs.getString(14).substring(8, 9).toUpperCase()
                        + rs.getString(14).substring(9);
                BookSet bs = new BookSet(rs.getInt(11), bsName, rs.getString(15), sortLink);
                sortLink = MyTool.convertString(rs.getString(16));
                Category ct = new Category(id, rs.getString(16), rs.getString(17), sortLink);
                sortLink = MyTool.convertString(rs.getString(3));
                Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), ct, bs, sortLink, rs.getInt(12), rs.getFloat(13));
                list.add(book);
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

    public ArrayList<Book> getListOfBookByNameAndIdCategoryAndIdDeals(
            String name, int id, int idDeals) {
        ArrayList<Book> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlSelect = "SELECT DISTINCT "
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
                + "WHERE tblBook.idCategory = ? AND tblBook.title "
                + "LIKE N'%" + name + "%' AND "
                + "tblBook.idBook NOT IN("
                + "SELECT tblBookDeals.idBook FROM tblBookDeals WHERE "
                + "tblBookDeals.idDeals = ?);";
        try {
            ps = conn.prepareStatement(sqlSelect);
            ps.setInt(1, id);
            ps.setInt(2, idDeals);

            rs = ps.executeQuery();
            while (rs.next()) {
                String sortLink = MyTool.convertString(rs.getString(14));
                String bsName = rs.getString(14).substring(8, 9).toUpperCase()
                        + rs.getString(14).substring(9);
                BookSet bs = new BookSet(rs.getInt(11), bsName, rs.getString(15), sortLink);
                sortLink = MyTool.convertString(rs.getString(16));
                Category ct = new Category(id, rs.getString(16), rs.getString(17), sortLink);
                sortLink = MyTool.convertString(rs.getString(3));
                Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), ct, bs, sortLink, rs.getInt(12), rs.getFloat(13));
                list.add(book);
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

    public ArrayList<Book> getListOfBookByNameAndIdBookSet(String name, int id) {
        ArrayList<Book> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlSelect = "SELECT DISTINCT "
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
                + "tblBook.votes, "
                + "tblBook.ratePoint, "
                + "tblBookSet.name, "
                + "tblBookSet.description, "
                + "tblCategory.name, "
                + "tblCategory.description "
                + "FROM tblBook "
                + "JOIN tblCategory ON tblBook.idCategory = tblCategory.idCategory "
                + "JOIN tblBookSet ON tblBook.idBookSet = tblBookSet.idBookSet "
                + "WHERE tblBook.idBookSet = ? AND tblBook.title LIKE N'%" + name + "%';";
        try {
            ps = conn.prepareStatement(sqlSelect);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                String sortLink = MyTool.convertString(rs.getString(14));
                String bsName = rs.getString(14).substring(8, 9).toUpperCase()
                        + rs.getString(14).substring(9);
                BookSet bs = new BookSet(id, bsName, rs.getString(15), sortLink);
                sortLink = MyTool.convertString(rs.getString(16));
                Category ct = new Category(rs.getInt(11), rs.getString(16), rs.getString(17), sortLink);
                sortLink = MyTool.convertString(rs.getString(3));
                Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), ct, bs, sortLink, rs.getInt(12), rs.getFloat(13));
                list.add(book);
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

    public ArrayList<Book> getListOfBookByNameAndIdBookSetAndIdDeals(
            String name, int id, int idDeals) {
        ArrayList<Book> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlSelect = "SELECT DISTINCT "
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
                + "tblBook.votes, "
                + "tblBook.ratePoint, "
                + "tblBookSet.name, "
                + "tblBookSet.description, "
                + "tblCategory.name, "
                + "tblCategory.description "
                + "FROM tblBook "
                + "JOIN tblCategory ON tblBook.idCategory = tblCategory.idCategory "
                + "JOIN tblBookSet ON tblBook.idBookSet = tblBookSet.idBookSet "
                + "WHERE tblBook.idBookSet = ? AND tblBook.title LIKE "
                + "N'%" + name + "%' AND "
                + "tblBook.idBook NOT IN("
                + "SELECT tblBookDeals.idBook FROM tblBookDeals WHERE "
                + "tblBookDeals.idDeals = ?);";
        try {
            ps = conn.prepareStatement(sqlSelect);
            ps.setInt(1, id);
            ps.setInt(2, idDeals);

            rs = ps.executeQuery();
            while (rs.next()) {
                String sortLink = MyTool.convertString(rs.getString(14));
                String bsName = rs.getString(14).substring(8, 9).toUpperCase()
                        + rs.getString(14).substring(9);
                BookSet bs = new BookSet(id, bsName, rs.getString(15), sortLink);
                sortLink = MyTool.convertString(rs.getString(16));
                Category ct = new Category(rs.getInt(11), rs.getString(16), rs.getString(17), sortLink);
                sortLink = MyTool.convertString(rs.getString(3));
                Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), ct, bs, sortLink, rs.getInt(12), rs.getFloat(13));
                list.add(book);
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

    public ArrayList<Book> getListOfBookByName(String name) {
        ArrayList<Book> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlSelect = "SELECT DISTINCT "
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
                + "WHERE tblBook.title LIKE N'%" + name + "%';";
        try {
            ps = conn.prepareStatement(sqlSelect);
            rs = ps.executeQuery();
            while (rs.next()) {
                String sortLink = MyTool.convertString(rs.getString(15));
                String bsName = rs.getString(15).substring(8, 9).toUpperCase()
                        + rs.getString(15).substring(9);
                BookSet bs = new BookSet(rs.getInt(12), bsName, rs.getString(16), sortLink);
                sortLink = MyTool.convertString(rs.getString(17));
                Category ct = new Category(rs.getInt(11), rs.getString(17), rs.getString(18), sortLink);
                sortLink = MyTool.convertString(rs.getString(3));
                Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), ct, bs, sortLink, rs.getInt(13), rs.getFloat(14));
                list.add(book);
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

    public ArrayList<Book> getListOfBookByNameAndIdDeals(String name, int idDeals) {
        ArrayList<Book> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlSelect = "SELECT DISTINCT "
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
                + "WHERE tblBook.title LIKE N'%" + name + "%' AND "
                + "tblBook.idBook NOT IN("
                + "SELECT tblBookDeals.idBook FROM tblBookDeals WHERE "
                + "tblBookDeals.idDeals = ?);";
        try {
            ps = conn.prepareStatement(sqlSelect);
            ps.setInt(1, idDeals);
            rs = ps.executeQuery();
            while (rs.next()) {
                String sortLink = MyTool.convertString(rs.getString(15));
                String bsName = rs.getString(15).substring(8, 9).toUpperCase()
                        + rs.getString(15).substring(9);
                BookSet bs = new BookSet(rs.getInt(12), bsName, rs.getString(16), sortLink);
                sortLink = MyTool.convertString(rs.getString(17));
                Category ct = new Category(rs.getInt(11), rs.getString(17), rs.getString(18), sortLink);
                sortLink = MyTool.convertString(rs.getString(3));
                Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), ct, bs, sortLink, rs.getInt(13), rs.getFloat(14));
                list.add(book);
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

    public ArrayList<Book> getListOfBookByIdCategory(int id) {
        ArrayList<Book> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlSelect = "SELECT DISTINCT "
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
                + "WHERE tblBook.idCategory = ?;";
        try {
            ps = conn.prepareStatement(sqlSelect);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String sortLink = MyTool.convertString(rs.getString(15));
                String bsName = rs.getString(15).substring(8, 9).toUpperCase()
                        + rs.getString(15).substring(9);
                BookSet bs = new BookSet(rs.getInt(12), bsName, rs.getString(16), sortLink);
                sortLink = MyTool.convertString(rs.getString(17));
                Category ct = new Category(rs.getInt(11), rs.getString(17), rs.getString(18), sortLink);
                sortLink = MyTool.convertString(rs.getString(3));
                Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), ct, bs, sortLink, rs.getInt(13), rs.getFloat(14));
                list.add(book);
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

    public ArrayList<Book> getListOfBookByIdCategoryAndIdDeals(int id, int idDeals) {
        ArrayList<Book> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlSelect = "SELECT DISTINCT "
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
                + "WHERE tblBook.idCategory = ? AND "
                + "tblBook.idBook NOT IN("
                + "SELECT tblBookDeals.idBook FROM tblBookDeals WHERE "
                + "tblBookDeals.idDeals = ?);";
        try {
            ps = conn.prepareStatement(sqlSelect);
            ps.setInt(1, id);
            ps.setInt(2, idDeals);
            rs = ps.executeQuery();
            while (rs.next()) {
                String sortLink = MyTool.convertString(rs.getString(15));
                String bsName = rs.getString(15).substring(8, 9).toUpperCase()
                        + rs.getString(15).substring(9);
                BookSet bs = new BookSet(rs.getInt(12), bsName, rs.getString(16), sortLink);
                sortLink = MyTool.convertString(rs.getString(17));
                Category ct = new Category(rs.getInt(11), rs.getString(17), rs.getString(18), sortLink);
                sortLink = MyTool.convertString(rs.getString(3));
                Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), ct, bs, sortLink, rs.getInt(13), rs.getFloat(14));
                list.add(book);
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

    public ArrayList<Book> getListOfBookByIdBookSet(int id) {
        ArrayList<Book> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlSelect = "SELECT DISTINCT "
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
                + "WHERE tblBook.idBookSet = ?;";
        try {
            ps = conn.prepareStatement(sqlSelect);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String sortLink = MyTool.convertString(rs.getString(15));
                String bsName = rs.getString(15).substring(8, 9).toUpperCase()
                        + rs.getString(15).substring(9);
                BookSet bs = new BookSet(rs.getInt(12), bsName, rs.getString(16), sortLink);
                sortLink = MyTool.convertString(rs.getString(17));
                Category ct = new Category(rs.getInt(11), rs.getString(17), rs.getString(18), sortLink);
                sortLink = MyTool.convertString(rs.getString(3));
                Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), ct, bs, sortLink, rs.getInt(13), rs.getFloat(14));
                list.add(book);
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

    public ArrayList<Book> getListOfBookByIdBookSetAndIdDeals(int id, int idDeals) {
        ArrayList<Book> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlSelect = "SELECT DISTINCT "
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
                + "WHERE tblBook.idBookSet = ? AND "
                + "tblBook.idBook NOT IN("
                + "SELECT tblBookDeals.idBook FROM tblBookDeals WHERE "
                + "tblBookDeals.idDeals = ?);";
        try {
            ps = conn.prepareStatement(sqlSelect);
            ps.setInt(1, id);
            ps.setInt(2, idDeals);
            rs = ps.executeQuery();
            while (rs.next()) {
                String sortLink = MyTool.convertString(rs.getString(15));
                String bsName = rs.getString(15).substring(8, 9).toUpperCase()
                        + rs.getString(15).substring(9);
                BookSet bs = new BookSet(rs.getInt(12), bsName, rs.getString(16), sortLink);
                sortLink = MyTool.convertString(rs.getString(17));
                Category ct = new Category(rs.getInt(11), rs.getString(17), rs.getString(18), sortLink);
                sortLink = MyTool.convertString(rs.getString(3));
                Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), ct, bs, sortLink, rs.getInt(13), rs.getFloat(14));
                list.add(book);
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

    public ArrayList<Book> getListBooksByWeek() {
        ArrayList<Book> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlSelect = "SELECT DISTINCT "
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
                + "JOIN tblBookOrder ON tblBook.idBook = tblBookOrder.idBook "
                + "JOIN tblPayment ON tblBookOrder.idCart = tblPayment.idCart "
                + "JOIN tblOrder ON tblPayment.idPayment = tblOrder.idPayment "
                + "WHERE tblOrder.createDate >= (CURDATE() - 604800) "
                + "ORDER BY tblBook.idBook DESC LIMIT 10;";
        try {
            ps = conn.prepareStatement(sqlSelect);
            rs = ps.executeQuery();
            while (rs.next()) {
                String sortLink = MyTool.convertString(rs.getString(15));
                String bsName = rs.getString(15).substring(8, 9).toUpperCase()
                        + rs.getString(15).substring(9);
                BookSet bs = new BookSet(rs.getInt(12), bsName, rs.getString(16), sortLink);
                sortLink = MyTool.convertString(rs.getString(17));
                Category ct = new Category(rs.getInt(11), rs.getString(17), rs.getString(18), sortLink);
                sortLink = MyTool.convertString(rs.getString(3));
                Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), ct, bs, sortLink, rs.getInt(13), rs.getFloat(14));
                list.add(book);
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

    public ArrayList<Book> getListBooksByMonth() {
        ArrayList<Book> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlSelect = "SELECT DISTINCT "
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
                + "JOIN tblBookOrder ON tblBook.idBook = tblBookOrder.idBook "
                + "JOIN tblPayment ON tblBookOrder.idCart = tblPayment.idCart "
                + "JOIN tblOrder ON tblPayment.idPayment = tblOrder.idPayment "
                + "WHERE tblOrder.createDate >= (CURDATE() - 2419200) "
                + "ORDER BY tblBook.idBook DESC LIMIT 10;";
        try {
            ps = conn.prepareStatement(sqlSelect);
            rs = ps.executeQuery();
            while (rs.next()) {
                String sortLink = MyTool.convertString(rs.getString(15));
                String bsName = rs.getString(15).substring(8, 9).toUpperCase()
                        + rs.getString(15).substring(9);
                BookSet bs = new BookSet(rs.getInt(12), bsName, rs.getString(16), sortLink);
                sortLink = MyTool.convertString(rs.getString(17));
                Category ct = new Category(rs.getInt(11), rs.getString(17), rs.getString(18), sortLink);
                sortLink = MyTool.convertString(rs.getString(3));
                Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), ct, bs, sortLink, rs.getInt(13), rs.getFloat(14));
                list.add(book);
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

    public ArrayList<Book> getListBooksByYear() {
        ArrayList<Book> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlSelect = "SELECT DISTINCT "
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
                + "JOIN tblBookOrder ON tblBook.idBook = tblBookOrder.idBook "
                + "JOIN tblPayment ON tblBookOrder.idCart = tblPayment.idCart "
                + "JOIN tblOrder ON tblPayment.idPayment = tblOrder.idPayment "
                + "WHERE tblOrder.createDate >= (CURDATE() - 29030400) "
                + "ORDER BY tblBook.idBook DESC LIMIT 10;";
        try {
            ps = conn.prepareStatement(sqlSelect);
            rs = ps.executeQuery();
            while (rs.next()) {
                String sortLink = MyTool.convertString(rs.getString(15));
                String bsName = rs.getString(15).substring(8, 9).toUpperCase()
                        + rs.getString(15).substring(9);
                BookSet bs = new BookSet(rs.getInt(12), bsName, rs.getString(16), sortLink);
                sortLink = MyTool.convertString(rs.getString(17));
                Category ct = new Category(rs.getInt(11), rs.getString(17), rs.getString(18), sortLink);
                sortLink = MyTool.convertString(rs.getString(3));
                Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), ct, bs, sortLink, rs.getInt(13), rs.getFloat(14));
                list.add(book);
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

    public ArrayList<Book> getFiveNewBooks() {
        ArrayList<Book> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlSelect = "SELECT DISTINCT "
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
                + "ORDER BY tblBook.idBook DESC LIMIT 5;";
        try {
            ps = conn.prepareStatement(sqlSelect);
            rs = ps.executeQuery();
            while (rs.next()) {
                String sortLink = MyTool.convertString(rs.getString(15));
                String bsName = rs.getString(15).substring(8, 9).toUpperCase()
                        + rs.getString(15).substring(9);
                BookSet bs = new BookSet(rs.getInt(12), bsName, rs.getString(16), sortLink);
                sortLink = MyTool.convertString(rs.getString(17));
                Category ct = new Category(rs.getInt(11), rs.getString(17), rs.getString(18), sortLink);
                sortLink = MyTool.convertString(rs.getString(3));
                Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), ct, bs, sortLink, rs.getInt(13), rs.getFloat(14));
                list.add(book);
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

    public Book getBookById(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlSelect = "SELECT DISTINCT "
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
                + "WHERE tblBook.idBook = ?;";
        try {
            ps = conn.prepareStatement(sqlSelect);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String sortLink = MyTool.convertString(rs.getString(15));
                String bsName = rs.getString(15).substring(8, 9).toUpperCase()
                        + rs.getString(15).substring(9);
                BookSet bs = new BookSet(rs.getInt(12), bsName, rs.getString(16), sortLink);
                sortLink = MyTool.convertString(rs.getString(17));
                Category ct = new Category(rs.getInt(11), rs.getString(17), rs.getString(18), sortLink);
                sortLink = MyTool.convertString(rs.getString(3));
                Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), ct, bs, sortLink, rs.getInt(13), rs.getFloat(14));
                return book;
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

    public ArrayList<Book> getListInvolveBook(Book b) {
        ArrayList<Book> list = new ArrayList<>();
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        String s1 = "SELECT DISTINCT "
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
                + "JOIN tblBookSet ON tblBook.idBookSet = tblBookSet.idBookSet ";
        String s2 = "AND tblBook.idBook NOT IN(?) "
                + "ORDER BY tblBook.idBook DESC LIMIT 3;";
        String sqlSelect1 = s1 + "WHERE tblBook.idCategory = ? " + s2;
        String sqlSelect2 = s1 + "WHERE tblBook.idBookSet = ? " + s2;
        try {
            ps = conn.prepareStatement(sqlSelect1);
            ps.setInt(1, b.getCategory().getIdCategory());
            ps.setInt(2, b.getIdBook());
            rs = ps.executeQuery();
            while (rs.next()) {
                String sortLink = MyTool.convertString(rs.getString(15));
                String bsName = rs.getString(15).substring(8, 9).toUpperCase()
                        + rs.getString(15).substring(9);
                BookSet bs = new BookSet(rs.getInt(12), bsName, rs.getString(16), sortLink);
                sortLink = MyTool.convertString(rs.getString(17));
                Category ct = new Category(rs.getInt(11), rs.getString(17), rs.getString(18), sortLink);
                sortLink = MyTool.convertString(rs.getString(3));
                Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), ct, bs, sortLink, rs.getInt(13), rs.getFloat(14));
                list.add(book);
            }
            ps2 = conn.prepareStatement(sqlSelect2);
            ps2.setInt(1, b.getSet().getIdBookSet());
            ps2.setInt(2, b.getIdBook());
            rs2 = ps2.executeQuery();
            while (rs2.next()) {
                String sortLink = MyTool.convertString(rs2.getString(15));
                String bsName = rs2.getString(15).substring(8, 9).toUpperCase()
                        + rs2.getString(15).substring(9);
                BookSet bs = new BookSet(rs2.getInt(12), bsName, rs2.getString(16), sortLink);
                sortLink = MyTool.convertString(rs2.getString(17));
                Category ct = new Category(rs2.getInt(11), rs2.getString(17), rs2.getString(18), sortLink);
                sortLink = MyTool.convertString(rs2.getString(3));
                Book book = new Book(rs2.getInt(1), rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(5), rs2.getString(6), rs2.getString(7), rs2.getString(8), rs2.getString(9), rs2.getInt(10), ct, bs, sortLink, rs2.getInt(13), rs2.getFloat(14));
                list.add(book);
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
                if (ps2 != null) {
                    ps2.close();
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

    public ArrayList<Book> getListBookByIdDeals(int idDeals) {
        ArrayList<Book> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlSelect = "SELECT DISTINCT "
                + "tblBook.idBook, "
                + "tblBook.image, "
                + "tblBook.title "
                + "FROM tblBook JOIN tblBookDeals ON "
                + "tblBook.idBook = tblBookDeals.idBook "
                + "JOIN tblDeals ON tblBookDeals.idDeals = tblDeals.idDeals "
                + "WHERE tblDeals.idDeals = ?;";
        try {
            ps = this.conn.prepareStatement(sqlSelect);
            ps.setInt(1, idDeals);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), null, null, null, null, null, null, 0, null, null, null, 0, 0));
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

    public ArrayList<Book> getAllBook() {
        String sql = "SELECT * FROM tblBook "
                + "JOIN tblCategory ON tblBook.idCategory=tblCategory.idCategory "
                + "JOIN tblBookSet ON tblBook.idBookSet=tblBookSet.idBookSet";
        ArrayList<Book> listBook = new ArrayList<Book>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setIdBook(rs.getInt("idBook"));
                book.setImage(rs.getString("image"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublishYear(rs.getString("publishYear"));
                book.setDescription(rs.getString("description"));
                book.setOriginalPrice(rs.getString("originalPrice"));
                book.setSalePrice(rs.getString("salePrice"));
                book.setQuantity(rs.getInt("quantity"));
                book.setCategory(new Category(
                        rs.getString(16),
                        rs.getString(17),
                        MyTool.convertString(rs.getString(16))));
                book.setSet(new BookSet(
                        rs.getString(19),
                        rs.getString(20),
                        MyTool.convertString(rs.getString(19))));
                listBook.add(book);
            }
            return listBook;
        } catch (SQLException e) {
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

    public boolean addBook(Book book) {
        String sql = "INSERT INTO tblBook"
                + "(image, title, author, publisher, publishYear, "
                + "description, originalPrice, salePrice, quantity, idCategory, idBookSet)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, book.getImage());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getPublisher());
            ps.setString(5, book.getPublishYear());
            ps.setString(6, book.getDescription());
            ps.setString(7, book.getOriginalPrice());
            ps.setString(8, book.getSalePrice());
            ps.setInt(9, book.getQuantity());
            ps.setInt(10, book.getCategory().getIdCategory());
            ps.setInt(11, book.getSet().getIdBookSet());
            int n = ps.executeUpdate();
            return (n == 1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean editBook(Book book) {
        String sql = "UPDATE tblBook SET image=?, title=?, "
                + "author=?, publisher=?, publishYear=?, description=?, "
                + "originalPrice=?, salePrice=?, quantity=?, idCategory=?, idBookSet=? "
                + "WHERE idBook=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, book.getImage());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getPublisher());
            ps.setString(5, book.getPublishYear());
            ps.setString(6, book.getDescription());
            ps.setString(7, book.getOriginalPrice());
            ps.setString(8, book.getSalePrice());
            ps.setInt(9, book.getQuantity());
            ps.setInt(10, book.getCategory().getIdCategory());
            ps.setInt(11, book.getSet().getIdBookSet());
            ps.setInt(12, book.getIdBook());
            int n = ps.executeUpdate();
            return (n == 1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean delBook(Book book) {
        String sql = "DELETE FROM tblBook WHERE idBook='" + book.getIdBook() + "'";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            int n = ps.executeUpdate();
            return (n == 1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
