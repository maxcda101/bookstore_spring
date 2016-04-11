/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import entity.book.Book;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zOzDarKzOz
 */
public class MyTool {

//    static final String STR = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static final String STR = "0123456789";

    public static String randomString(int len) {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(STR.charAt(rnd.nextInt(STR.length())));
        }
        return sb.toString();
    }

    public static int handleString(String str, String regex) {
        str = str.trim();
        str = str.replaceAll("\\-+", "-");
        if (str.matches("-\\d+" + regex + ".{0,}")) {
            str = str.substring(1, str.length() - 1);
        }
        if (str.matches("\\d+" + regex + "[a-zA-Z0-9]{4,}.{0,}")) {
            String[] s = str.split(regex);
            try {
                int id = Integer.parseInt(s[0]);
                return id;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static String handleInputString(String str) {
        str = str.trim();
        str = str.replaceAll("\\s+", " ");
        str = str.replaceAll("\\`|\\~|\\!|\\@|\\#|\\$|\\%|\\^|\\&|\\*|\\(|\\-|"
                + "\\)|\\_|\\=|\\+|\\{|\\[|\\}|\\]|\\<|\\,|\\>|\\.|\\?|\\/", "");
        return str;
    }

    public static String convertString(String str) {
        try {
            str = str.trim();
            str = str.replaceAll("\\s+", "-");
            str = str.replaceAll("\\-+", "-");
            str = str.replaceAll("(à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ)", "a");
            str = str.replaceAll("(è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ)", "e");
            str = str.replaceAll("(ì|í|ị|ỉ|ĩ)", "i");
            str = str.replaceAll("(ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ)", "o");
            str = str.replaceAll("(ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ)", "u");
            str = str.replaceAll("(ỳ|ý|ỵ|ỷ|ỹ)", "y");
            str = str.replaceAll("(đ)", "d");
            str = str.replaceAll("ç", "c");
            str = str.replaceAll("Ç", "C");
            str = str.replaceAll("\\`|\\~|\\!|\\@|\\#|\\$|\\%|\\^|\\&|\\*|\\(|"
                    + "\\)|\\_|\\=|\\+|\\{|\\[|\\}|\\]|\\<|\\,|\\>|\\.|\\?|"
                    + "\\/|\\;|\\:", "");
            str = str.replaceAll("(À|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ)", "A");
            str = str.replaceAll("(È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ)", "E");
            str = str.replaceAll("(Ì|Í|Ị|Ỉ|Ĩ)", "I");
            str = str.replaceAll("(Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ)", "O");
            str = str.replaceAll("(Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ)", "U");
            str = str.replaceAll("(Ỳ|Ý|Ỵ|Ỷ|Ỹ)", "Y");
            str = str.replaceAll("(Đ)", "D");
            str = str.replaceAll("'", "");
            str = str.toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getCurrentTimeInYYYYMMDDHH24mmssFormat() {
        String res = "Ha Noi, ";
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            DateFormat df = new SimpleDateFormat("EEEE");
            res += df.format(c.getTime()) + ", " + sdf.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String getCurrentTimeSQLFormat(java.util.Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String handleParameterString(String str) {
        str = str.replaceAll("\\&lang=vi", "");
        str = str.replaceAll("\\&lang=en", "");
        str = str.replaceAll("lang=en", "");
        str = str.replaceAll("lang=vi", "");
        str += "&";
        return str;
    }

    public static String handleCookieString(String str) {
        str = str.trim();
        str = str.replaceAll("%5B", "[");
        str = str.replaceAll("%7B", "{");
        str = str.replaceAll("%22", "\"");
        str = str.replaceAll("%3A", ":");
        str = str.replaceAll("%7D", "}");
        str = str.replaceAll("%2C", ",");
        str = str.replaceAll("%5D", "]");
        str = str.replaceAll("%22%C3%A0", "à");
        str = str.replaceAll("%C3%A1", "á");
        str = str.replaceAll("%E1%BA%A1", "ạ");
        str = str.replaceAll("%E1%BA%A3", "ả");
        str = str.replaceAll("%C3%A3", "ã");
        str = str.replaceAll("%C3%A2", "â");
        str = str.replaceAll("%E1%BA%A7", "ầ");
        str = str.replaceAll("%E1%BA%A5", "ấ");
        str = str.replaceAll("%E1%BA%AD", "ậ");
        str = str.replaceAll("%E1%BA%A9", "ẩ");
        str = str.replaceAll("%E1%BA%AB", "ẫ");
        str = str.replaceAll("%C4%83", "ă");
        str = str.replaceAll("%E1%BA%B1", "ằ");
        str = str.replaceAll("%E1%BA%AF", "ắ");
        str = str.replaceAll("%E1%BA%B7", "ặ");
        str = str.replaceAll("%E1%BA%B3", "ẳ");
        str = str.replaceAll("%E1%BA%B5", "ẵ");
        str = str.replaceAll("%C3%A8", "è");
        str = str.replaceAll("%C3%A9", "é");
        str = str.replaceAll("%E1%BA%B9", "ẹ");
        str = str.replaceAll("%E1%BA%BB", "ẻ");
        str = str.replaceAll("%E1%BA%BD", "ẽ");
        str = str.replaceAll("%C3%AA", "ê");
        str = str.replaceAll("%E1%BB%81", "ề");
        str = str.replaceAll("%E1%BA%BF", "ế");
        str = str.replaceAll("%E1%BB%87", "ệ");
        str = str.replaceAll("%E1%BB%83", "ể");
        str = str.replaceAll("%E1%BB%85", "ễ");
        str = str.replaceAll("%C3%AC", "ì");
        str = str.replaceAll("%C3%AD", "í");
        str = str.replaceAll("%E1%BB%8B", "ị");
        str = str.replaceAll("%E1%BB%89", "ỉ");
        str = str.replaceAll("%C4%A9", "ĩ");
        str = str.replaceAll("%C3%B2", "ò");
        str = str.replaceAll("%C3%B3", "ó");
        str = str.replaceAll("%E1%BB%8D", "ọ");
        str = str.replaceAll("%E1%BB%8F", "ỏ");
        str = str.replaceAll("%C3%B5", "õ");
        str = str.replaceAll("%C3%B4", "ô");
        str = str.replaceAll("%E1%BB%93", "ồ");
        str = str.replaceAll("%E1%BB%91", "ố");
        str = str.replaceAll("%E1%BB%99", "ộ");
        str = str.replaceAll("%E1%BB%95", "ổ");
        str = str.replaceAll("%E1%BB%97", "ỗ");
        str = str.replaceAll("%C6%A1", "ơ");
        str = str.replaceAll("%E1%BB%9D", "ờ");
        str = str.replaceAll("%E1%BB%9B", "ớ");
        str = str.replaceAll("%E1%BB%A3", "ợ");
        str = str.replaceAll("%E1%BB%9F", "ở");
        str = str.replaceAll("%E1%BB%A1", "ỡ");
        str = str.replaceAll("%C3%B9", "ù");
        str = str.replaceAll("%C3%BA", "ú");
        str = str.replaceAll("%E1%BB%A5", "ụ");
        str = str.replaceAll("%E1%BB%A7", "ủ");
        str = str.replaceAll("%C5%A9", "ũ");
        str = str.replaceAll("%C6%B0", "ư");
        str = str.replaceAll("%E1%BB%AB", "ừ");
        str = str.replaceAll("%E1%BB%A9", "ứ");
        str = str.replaceAll("%E1%BB%B1", "ự");
        str = str.replaceAll("%E1%BB%AD", "ử");
        str = str.replaceAll("%E1%BB%AF", "ữ");
        str = str.replaceAll("%E1%BB%B3", "ỳ");
        str = str.replaceAll("%C3%BD", "ý");
        str = str.replaceAll("%E1%BB%B5", "ỵ");
        str = str.replaceAll("%E1%BB%B7", "ỷ");
        str = str.replaceAll("%E1%BB%B9", "ỹ");
        str = str.replaceAll("%C4%91", "đ");
        str = str.replaceAll("%C3%80", "À");
        str = str.replaceAll("%C3%81", "Á");
        str = str.replaceAll("%E1%BA%A0", "Ạ");
        str = str.replaceAll("%E1%BA%A2", "Ả");
        str = str.replaceAll("%C3%83", "Ã");
        str = str.replaceAll("%C3%82", "Â");
        str = str.replaceAll("%E1%BA%A6", "Ầ");
        str = str.replaceAll("%E1%BA%A4", "Ấ");
        str = str.replaceAll("%E1%BA%AC", "Ậ");
        str = str.replaceAll("%E1%BA%A8", "Ẩ");
        str = str.replaceAll("%E1%BA%AA", "Ẫ");
        str = str.replaceAll("%C4%82", "Ă");
        str = str.replaceAll("%E1%BA%B0", "Ằ");
        str = str.replaceAll("%E1%BA%AE", "Ắ");
        str = str.replaceAll("%E1%BA%B6", "Ặ");
        str = str.replaceAll("%E1%BA%B2", "Ẳ");
        str = str.replaceAll("%E1%BA%B4", "Ẵ");
        str = str.replaceAll("%C3%88", "È");
        str = str.replaceAll("%C3%89", "É");
        str = str.replaceAll("%E1%BA%B8", "Ẹ");
        str = str.replaceAll("%E1%BA%BA", "Ẻ");
        str = str.replaceAll("%E1%BA%BC", "Ẽ");
        str = str.replaceAll("%C3%8A", "Ê");
        str = str.replaceAll("%E1%BB%80", "Ề");
        str = str.replaceAll("%E1%BA%BE", "Ế");
        str = str.replaceAll("%E1%BB%86", "Ệ");
        str = str.replaceAll("%E1%BB%82", "Ể");
        str = str.replaceAll("%E1%BB%84", "Ễ");
        str = str.replaceAll("%C3%8C", "Ì");
        str = str.replaceAll("%C3%8D", "Í");
        str = str.replaceAll("%E1%BB%8A", "Ị");
        str = str.replaceAll("%E1%BB%88", "Ỉ");
        str = str.replaceAll("%C4%A8", "Ĩ");
        str = str.replaceAll("%C3%92", "Ò");
        str = str.replaceAll("%C3%93", "Ó");
        str = str.replaceAll("%E1%BB%8C", "Ọ");
        str = str.replaceAll("%E1%BB%8E", "Ỏ");
        str = str.replaceAll("%C3%95", "Õ");
        str = str.replaceAll("%C3%94", "Ô");
        str = str.replaceAll("%E1%BB%92", "Ồ");
        str = str.replaceAll("%E1%BB%90", "Ố");
        str = str.replaceAll("%E1%BB%98", "Ộ");
        str = str.replaceAll("%E1%BB%94", "Ổ");
        str = str.replaceAll("%E1%BB%96", "Ỗ");
        str = str.replaceAll("%C6%A0", "Ơ");
        str = str.replaceAll("%E1%BB%9C", "Ờ");
        str = str.replaceAll("%E1%BB%9A", "Ớ");
        str = str.replaceAll("%E1%BB%A2", "Ợ");
        str = str.replaceAll("%E1%BB%9E", "Ở");
        str = str.replaceAll("%E1%BB%A0", "Ỡ");
        str = str.replaceAll("%C3%99", "Ù");
        str = str.replaceAll("%C3%9A", "Ú");
        str = str.replaceAll("%E1%BB%A4", "Ụ");
        str = str.replaceAll("%E1%BB%A6", "Ủ");
        str = str.replaceAll("%C5%A8", "Ũ");
        str = str.replaceAll("%C6%AF", "Ư");
        str = str.replaceAll("%E1%BB%AA", "Ừ");
        str = str.replaceAll("%E1%BB%A8", "Ứ");
        str = str.replaceAll("%E1%BB%B0", "Ự");
        str = str.replaceAll("%E1%BB%AC", "Ử");
        str = str.replaceAll("%E1%BB%AE", "Ữ");
        str = str.replaceAll("%E1%BB%B2", "Ỳ");
        str = str.replaceAll("%C3%9D", "Ý");
        str = str.replaceAll("%E1%BB%B4", "Ỵ");
        str = str.replaceAll("%E1%BB%B6", "Ỷ");
        str = str.replaceAll("%E1%BB%B8", "Ỹ");
        str = str.replaceAll("%C4%90%22", "Đ");
        str = str.replaceAll("%2B", " ");
        str = str.replaceAll("\\+", " ");
        return str;
    }

    public static String getCookieByName(HttpServletRequest request, String cookieName) {
        try {
            Cookie arrCookie[] = request.getCookies();
            if (arrCookie != null) {
                int arrCookieSize = arrCookie.length;
                Cookie cookie = null;
                for (int i = 0; i < arrCookieSize; i++) {
                    if (arrCookie[i].getName().equals(cookieName)) {
                        cookie = arrCookie[i];
                        break;
                    }
                }
                if (cookie != null && cookie.getValue().length() > 0) {
                    return cookie.getValue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setCookie(
            HttpServletResponse response, String cookieName, String data
    ) {
        try {
            Cookie cookie = new Cookie(cookieName, URLEncoder.encode(data, "UTF-8"));
            cookie.setMaxAge(86400000);
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setAndUpdateSessionBook(HttpSession session, String sessionName, ArrayList<Book> listBook, Book book) {

        ArrayList<Book> currentListBook = (ArrayList<Book>) session.getAttribute(sessionName);
        if (currentListBook != null) {
            if (listBook != null && listBook.size() > 0) {
                int size = listBook.size();
                for (int i = 0; i < size; i++) {
                    if (!currentListBook.contains(listBook.get(i))) {
                        currentListBook.add(listBook.get(i));
                    }
                }
            }
            if (book != null && !currentListBook.contains(book)) {
                currentListBook.add(book);
            }
            session.setAttribute(sessionName, currentListBook);
        } else {
            session.setAttribute(sessionName, listBook);
        }
    }

    /* Xử lý phân trang */
    public ArrayList<Integer> getPages(int pageAvailabel, int currentPage, int numProduct) {
        ArrayList<Integer> listI = new ArrayList<>();
        int n = pageAvailabel;
        int nAdd;
        int m = currentPage;
        if (n > numProduct) {
            nAdd = m - n / 2;
            for (int i = 1; i <= numProduct; i++) {
                if (nAdd > 0) {
                    listI.add(i + nAdd);
                    if (i + nAdd >= n) {
                        break;
                    }
                } else {
                    listI.add(i);
                }
            }
        } else {
            for (int i = 1; i <= n; i++) {
                listI.add(i);
            }
        }
        return listI;
    }
}
