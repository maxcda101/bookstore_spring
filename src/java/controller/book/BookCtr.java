/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.book;

import controller.LoadDefaultData;
import dao.book.BookDAO;
import entity.book.Book;
import entity.order.BookOrder;
import entity.order.Cart;
import entity.person.customer.Customer;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tool.MyTool;

/**
 *
 * @author zOzDarKzOz
 */
@Controller
@RequestMapping(value = "book/")
public class BookCtr {

    @RequestMapping(value = "{crBook}.html")
    public ModelAndView viewBook(ModelAndView model,
            @PathVariable Map<String, String> pathVars,
            HttpSession session, HttpServletRequest request,
            HttpServletResponse response) {
        model = new ModelAndView("/front/bookDetails");
        LoadDefaultData.getAndSetCookieAndScopeOfCategoryAndBookSet(request, response, model);
        String strCrBook = pathVars.get("crBook");
        if (strCrBook != null && strCrBook.matches("([0-9]){1,}-([0-9a-zA-Z-]){2,}")) {
            try {
                String strIdBook = strCrBook.split("-")[0];
                String bookSortLink = strCrBook.substring(strIdBook.length() + 1);
                int idBook = Integer.parseInt(strIdBook);
                if (idBook >= 1) {
                    Book book = findBookInSession(session, idBook);
                    if (book != null && book.getSortLink().equals(bookSortLink)) {
                        checkAndSetModelBookInCartAndMember(model, book, session);
                        try {
                            getAndSetModelListInvolveBook(model, session, book);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return model;
                    }
                    if (book == null) {
                        try {
                            book = new BookDAO().getBookById(idBook);
                            if (book != null && book.getSortLink().equals(bookSortLink)) {
                                checkAndSetModelBookInCartAndMember(model, book, session);
                                getAndSetModelListInvolveBook(model, session, book);
                                MyTool.setAndUpdateSessionBook(session, "ssListBook", null, book);
                            } else {
                                model.addObject("bookNotFound", true);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return model;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        model.addObject("bookNotExist", true);
        return model;
    }

    private Book findBookInSession(HttpSession session, int idBook) {
        ArrayList<Book> currentListBook = (ArrayList<Book>) session.getAttribute("ssListBook");
        if (currentListBook != null) {
            int size = currentListBook.size();
            for (int i = 0; i < size; i++) {
                if (currentListBook.get(i).getIdBook() == idBook) {
                    return currentListBook.get(i);
                }
            }
        }
        return null;
    }

    private void checkAndSetModelBookInCartAndMember(ModelAndView model, Book book,
            HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null && cart.getListBook().size() > 0) {
            ArrayList<BookOrder> listBookOrder = cart.getListBook();
            if (listBookOrder != null && listBookOrder.size() > 0) {
                for (int i = 0; i < listBookOrder.size(); i++) {
                    if (listBookOrder.get(i).getBook().getIdBook() == book.getIdBook()) {
                        model.addObject("isIncart", true);
                        break;
                    }
                }
            }
        }
        model.addObject("crBook", book);
        Customer cus = (Customer) session.getAttribute("ssLogged");
        if (cus != null && cus.getCustomerType().equals("customerMember")) {
            model.addObject("crMbUserName", cus.getCustomerMemberUsername());
        }
    }

    private void getAndSetModelListInvolveBook(ModelAndView model, HttpSession session, Book book) {
        ArrayList<Book> listInvolveBook = new BookDAO().getListInvolveBook(book);
        if (listInvolveBook != null && listInvolveBook.size() > 0) {
            MyTool.setAndUpdateSessionBook(session, "ssListBook", listInvolveBook, null);
            model.addObject("listInvolveBook", listInvolveBook);
        }
    }
}
