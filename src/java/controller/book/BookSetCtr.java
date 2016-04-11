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
import entity.order.Cart;
import entity.person.customer.Customer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tool.MyTool;

/**
 *
 * @author zOzDarKzOz
 */
@Controller
@RequestMapping(value = "bookset")
public class BookSetCtr {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView bookset(ModelAndView model,
            HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        model = new ModelAndView("/front/bookset");
            LoadDefaultData.getAndSetCookieAndScopeOfCategoryAndBookSet(request, response, model);
        return model;
    }

    @RequestMapping(value = "/{bsStr}.html", method = RequestMethod.GET)
    public ModelAndView bookByBookSet(ModelAndView model,
            @PathVariable Map<String, String> pathVars, HttpSession session,
            HttpServletRequest request, HttpServletResponse response) {
        model = new ModelAndView("/front/bookByBookSet");
        LoadDefaultData.getAndSetCookieAndScopeOfCategoryAndBookSet(request, response, model);
        String bsStr = pathVars.get("bsStr");
        if (bsStr != null && bsStr.matches("([0-9]){1,}-([0-9a-zA-Z-]){2,}")) {
            try {
                String indexBsStr = bsStr.split("-")[0];
                String bsSortLink = bsStr.substring(indexBsStr.length() + 1);
                int indexBs = Integer.parseInt(indexBsStr);

                String jsonBs = MyTool.getCookieByName(request, "ckBookSet");
                Gson gson = new GsonBuilder().create();
                Type type = new TypeToken<ArrayList<BookSet>>() {
                }.getType();
                jsonBs = MyTool.handleCookieString(jsonBs);
                ArrayList<BookSet> listBookSet = (ArrayList<BookSet>) gson.fromJson(jsonBs, type);

                if ((indexBs >= 0) && (listBookSet != null)
                        && (indexBs < listBookSet.size())
                        && (listBookSet.get(indexBs).getSortLink().equals(bsSortLink))) {
                    int idBs = listBookSet.get(indexBs).getIdBookSet();
                    ArrayList<Book> listBookByBookSet = (ArrayList<Book>) session.getAttribute("ssListBookByBookSet-" + idBs);
                    if (listBookByBookSet == null) {
                        getAndSetSessionAndModelListOfBookByIdBookSetInDb(session, listBookByBookSet, idBs, model);
                    } else {
                        model.addObject("listBookByBookSet", listBookByBookSet);
                    }
                    Cart cart = (Cart) session.getAttribute("cart");
                    if (cart != null && cart.getListBook().size() > 0) {
                        model.addObject("listBookInCart", cart.getListBook());
                    }
                    Customer cus = (Customer) session.getAttribute("ssLogged");
                    if (cus != null && cus.getCustomerType().equals("customerMember")) {
                        model.addObject("crMbUserName", cus.getCustomerMemberUsername());
                    }
                    model.addObject("crBookSet", listBookSet.get(indexBs).getName().toUpperCase());
                    return model;
                }
            } catch (NumberFormatException | JsonSyntaxException e) {
                e.printStackTrace();
            }
        }
        model.addObject("bsNotExist", true);
        return model;
    }

    private void getAndSetSessionAndModelListOfBookByIdBookSetInDb(
            HttpSession session, ArrayList<Book> listBookByBookSet, int idBs,
            ModelAndView model
    ) {
        try {
            listBookByBookSet = new BookDAO().getListOfBookByIdBookSet(idBs);
            if (listBookByBookSet == null || listBookByBookSet.size() <= 0) {
                model.addObject("emptyBookByBookSet", true);
            } else {
                MyTool.setAndUpdateSessionBook(session, "ssListBook", listBookByBookSet, null);
                session.setAttribute("ssListBookByBookSet-" + idBs, listBookByBookSet);
                model.addObject("listBookByBookSet", listBookByBookSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
