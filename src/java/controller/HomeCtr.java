/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.book.BookDAO;
import entity.book.Book;
import entity.order.Cart;
import entity.person.customer.Customer;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tool.MyTool;

/**
 *
 * @author zOzDarKzOz
 */
@Controller
public class HomeCtr {

    @RequestMapping(value = {"", "/", "home.html"}, method = RequestMethod.GET)
    public ModelAndView home(ModelAndView model, HttpSession session,
            HttpServletRequest request, HttpServletResponse response) {
        model = new ModelAndView("/front/home");
        LoadDefaultData.getAndSetCookieAndScopeOfCategoryAndBookSet(request, response, model);
        ArrayList<Book> listFiveBook = (ArrayList<Book>) session.getAttribute("ssListFiveBook");
        if (listFiveBook == null || listFiveBook.size() < 5) {
            try {
                listFiveBook = getFiveNewBookInDb();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (listFiveBook != null) {
            getAndSetModelListBookInDb(model, session);
            MyTool.setAndUpdateSessionBook(session, "ssListBook", listFiveBook, null);
            session.setAttribute("ssListFiveBook", listFiveBook);
            model.addObject("listFiveBook", listFiveBook);
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart != null && cart.getListBook().size() > 0) {
                model.addObject("listBookInCart", cart.getListBook());
            }
            Customer cus = (Customer) session.getAttribute("ssLogged");
            if (cus != null && cus.getCustomerType().equals("customerMember")) {
                model.addObject("crMbUserName", cus.getCustomerMemberUsername());
            }
        }
        return model;
    }

    private ArrayList<Book> getFiveNewBookInDb() {
        ArrayList<Book> listFiveBook = new ArrayList<>();
        try {
            listFiveBook = new BookDAO().getFiveNewBooks();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listFiveBook;
    }

    private void getAndSetModelListBookInDb(ModelAndView model,
            HttpSession session) {
        ArrayList<Book> listBook = (ArrayList<Book>) session.getAttribute("ssListBookByWeek");
        try {
            if (listBook == null) {
                listBook = new BookDAO().getListBooksByWeek();
                if (listBook != null) {
                    model.addObject("listBookByWeek", listBook);
                    MyTool.setAndUpdateSessionBook(session, "ssListBook", listBook, null);
                }
            } else {
                model.addObject("listBookByWeek", listBook);
            }
            listBook = (ArrayList<Book>) session.getAttribute("ssListBookByMonth");
            if (listBook == null) {
                listBook = new BookDAO().getListBooksByMonth();
                if (listBook != null) {
                    model.addObject("listBookByMonth", listBook);
                    MyTool.setAndUpdateSessionBook(session, "ssListBook", listBook, null);
                }
            } else {
                model.addObject("listBookByMonth", listBook);
            }
            listBook = (ArrayList<Book>) session.getAttribute("ssListBookByYear");
            if (listBook == null) {
                listBook = new BookDAO().getListBooksByYear();
                if (listBook != null) {
                    model.addObject("listBookByYear", listBook);
                    MyTool.setAndUpdateSessionBook(session, "ssListBook", listBook, null);
                }
            } else {
                model.addObject("listBookByYear", listBook);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "contactus.html")
    public ModelAndView contactUs(ModelAndView model,
            HttpServletRequest request, HttpServletResponse response) {
        model = new ModelAndView("/front/contactUs");
        LoadDefaultData.getAndSetCookieAndScopeOfCategoryAndBookSet(request, response, model);
        return model;
    }

    @RequestMapping(value = "aboutus.html")
    public ModelAndView aboutUs(ModelAndView model,
            HttpServletRequest request, HttpServletResponse response) {
        model = new ModelAndView("/front/aboutUs");
        LoadDefaultData.getAndSetCookieAndScopeOfCategoryAndBookSet(request, response, model);
        return model;
    }

}
