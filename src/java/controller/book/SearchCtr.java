/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.book;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import controller.LoadDefaultData;
import dao.book.BookDAO;
import entity.book.Book;
import entity.book.BookSet;
import entity.book.Category;
import entity.order.Cart;
import entity.person.customer.Customer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tool.MyTool;

/**
 *
 * @author zOzDarKzOz
 */
@Controller
@RequestMapping(value = "/search.html")
public class SearchCtr {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView search(
            ModelAndView model, HttpServletRequest request,
            HttpSession session, HttpServletResponse response,
            @RequestParam(value = "op", required = false) String option,
            @RequestParam(value = "vl", required = false) String value
    ) throws IOException {
        if (option == null || value == null || option.equals("") || value.equals("")) {
            response.sendRedirect(request.getContextPath() + "/home.html");
        } else {
            model = new ModelAndView("/front/searchResult");
            LoadDefaultData.getAndSetCookieAndScopeOfCategoryAndBookSet(request, response, model);

            if (option.matches("n1")) {
                searchBookByNameInDB(model, value, session);
                getAndSetModelOfMemberAndBookInCart(model, session);
                model.addObject("vl", value);
                model.addObject("op", option);
            } else if (option.matches("ct([0-9]){1,}")) {
                searchBookByNameAndCategoryInDB(model, option, value, request, session);
                getAndSetModelOfMemberAndBookInCart(model, session);
                model.addObject("op", option);
            } else if (option.matches("bs([0-9]){1,}")) {
                searchBookByNameAndBookSetInDB(model, option, value, request, session);
                getAndSetModelOfMemberAndBookInCart(model, session);
                model.addObject("op", option);
            } else {
                model.addObject("noResult", true);
            }
            return model;
        }
        return null;
    }

    private void getAndSetModelOfMemberAndBookInCart(ModelAndView model,
            HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null && cart.getListBook().size() > 0) {
            model.addObject("listBookInCart", cart.getListBook());
        }
        Customer cus = (Customer) session.getAttribute("ssLogged");
        if (cus != null && cus.getCustomerType().equals("customerMember")) {
            model.addObject("crMbUserName", cus.getCustomerMemberUsername());
        }
    }

    private void searchBookByNameInDB(ModelAndView model, String value,
            HttpSession session) {
        try {
            String name = MyTool.handleInputString(value);
            ArrayList<Book> listBook = new ArrayList<>();
            try {
                listBook = new BookDAO().getListOfBookByName(name);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (listBook == null || listBook.size() <= 0) {
                model.addObject("emptyResult", true);
            } else {
                MyTool.setAndUpdateSessionBook(session, "ssListBook", listBook, null);
                model.addObject("listBook", listBook);
                int pg = listBook.size() / 12;
                if ((pg * 12) < listBook.size()) {
                    pg += 1;
                }
                if (pg > 1) {
                    model.addObject("pageCount", pg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchBookByNameAndCategoryInDB(
            ModelAndView model, String option, String value,
            HttpServletRequest request, HttpSession session) {
        try {
            int indexCt = Integer.parseInt(option.substring(2));
            String jsonCt = MyTool.getCookieByName(request, "ckCategory");

            Gson gson = new GsonBuilder().create();
            Type type = new TypeToken<ArrayList<Category>>() {
            }.getType();
            jsonCt = MyTool.handleCookieString(jsonCt);
            ArrayList<Category> listCategory = gson.fromJson(jsonCt, type);

            if (listCategory != null && indexCt < listCategory.size()) {
                int idCt = listCategory.get(indexCt).getIdCategory();
                String name = MyTool.handleInputString(value);
                ArrayList<Book> listBook = new ArrayList<>();
                try {
                    listBook = new BookDAO().getListOfBookByNameAndIdCategory(name, idCt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (listBook == null || listBook.size() <= 0) {
                    model.addObject("emptyResult", true);
                } else {
                    MyTool.setAndUpdateSessionBook(session, "ssListBook", listBook, null);
                    model.addObject("listBook", listBook);
                    int pg = listBook.size() / 12;
                    if ((pg * 12) < listBook.size()) {
                        pg += 1;
                    }
                    if (pg > 1) {
                        model.addObject("pageCount", pg);
                    }
                }
                model.addObject("vl", name);
            } else {
                model.addObject("emptyResult", true);
            }
        } catch (NumberFormatException | JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    private void searchBookByNameAndBookSetInDB(
            ModelAndView model, String option, String value,
            HttpServletRequest request, HttpSession session) {
        try {
            int indexBs = Integer.parseInt(option.substring(2));
            String jsonBs = MyTool.getCookieByName(request, "ckBookSet");

            Gson gson = new GsonBuilder().create();
            Type type = new TypeToken<ArrayList<BookSet>>() {
            }.getType();
            jsonBs = MyTool.handleCookieString(jsonBs);
            ArrayList<BookSet> listBookSet = gson.fromJson(jsonBs, type);

            if (listBookSet != null && indexBs < listBookSet.size()) {
                int idBs = listBookSet.get(indexBs).getIdBookSet();
                String name = MyTool.handleInputString(value);
                ArrayList<Book> listBook = new ArrayList<>();
                try {
                    listBook = new BookDAO().getListOfBookByNameAndIdBookSet(name, idBs);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (listBook == null || listBook.size() <= 0) {
                    model.addObject("emptyResult", true);
                } else {
                    MyTool.setAndUpdateSessionBook(session, "ssListBook", listBook, null);
                    model.addObject("listBook", listBook);
                    int pg = listBook.size() / 12;
                    if ((pg * 12) < listBook.size()) {
                        pg += 1;
                    }
                    if (pg > 1) {
                        model.addObject("pageCount", pg);
                    }
                }
                model.addObject("vl", name);
            } else {
                model.addObject("emptyResult", true);
            }
        } catch (NumberFormatException | JsonSyntaxException e) {
            e.printStackTrace();
        }
    }
}
