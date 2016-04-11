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
import entity.book.Category;
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
@RequestMapping(value = "category")
public class CategoryCtr {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView category(ModelAndView model,
            HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        model = new ModelAndView("/front/category");
            LoadDefaultData.getAndSetCookieAndScopeOfCategoryAndBookSet(request, response, model);
        return model;
    }

    @RequestMapping(value = "/{ctStr}.html", method = RequestMethod.GET)
    public ModelAndView bookByCategory(ModelAndView model,
            @PathVariable Map<String, String> pathVars, HttpSession session,
            HttpServletRequest request, HttpServletResponse response) {
        model = new ModelAndView("/front/bookByCategory");
        LoadDefaultData.getAndSetCookieAndScopeOfCategoryAndBookSet(request, response, model);
        String ctStr = pathVars.get("ctStr");
        if (ctStr != null && ctStr.matches("([0-9]){1,}-([0-9a-zA-Z-]){2,}")) {
            try {
                String indexCtStr = ctStr.split("-")[0];
                String ctSortLink = ctStr.substring(indexCtStr.length() + 1);
                int indexCt = Integer.parseInt(indexCtStr);

                String jsonCt = MyTool.getCookieByName(request, "ckCategory");
                Gson gson = new GsonBuilder().create();
                Type type = new TypeToken<ArrayList<Category>>() {
                }.getType();
                jsonCt = MyTool.handleCookieString(jsonCt);
                ArrayList<Category> listCategory = (ArrayList<Category>) gson.fromJson(jsonCt, type);

                if ((indexCt >= 0) && (listCategory != null)
                        && (indexCt < listCategory.size())
                        && (listCategory.get(indexCt).getSortLink().equals(ctSortLink))) {
                    int idCt = listCategory.get(indexCt).getIdCategory();
                    ArrayList<Book> listBookByCategory = (ArrayList<Book>) session.getAttribute("ssListBookByCategory-" + idCt);
                    if (listBookByCategory == null) {
                        getAndSetSessionAndModelListOfBookByIdCategoryInDb(session, listBookByCategory, idCt, model);
                    } else {
                        model.addObject("listBookByCategory", listBookByCategory);
                    }
                    Cart cart = (Cart) session.getAttribute("cart");
                    if (cart != null && cart.getListBook().size() > 0) {
                        model.addObject("listBookInCart", cart.getListBook());
                    }
                    Customer cus = (Customer) session.getAttribute("ssLogged");
                    if (cus != null && cus.getCustomerType().equals("customerMember")) {
                        model.addObject("crMbUserName", cus.getCustomerMemberUsername());
                    }
                    model.addObject("crCategory", listCategory.get(indexCt).getName().toUpperCase());
                    return model;
                }
            } catch (NumberFormatException | JsonSyntaxException e) {
                e.printStackTrace();
            }
        }
        model.addObject("ctNotExist", true);
        return model;
    }

    private void getAndSetSessionAndModelListOfBookByIdCategoryInDb(
            HttpSession session, ArrayList<Book> listBookByCategory, int idCt,
            ModelAndView model
    ) {
        try {
            listBookByCategory = new BookDAO().getListOfBookByIdCategory(idCt);
            if (listBookByCategory == null || listBookByCategory.size() <= 0) {
                model.addObject("emptyBookByCategory", true);
            } else {
                MyTool.setAndUpdateSessionBook(session, "ssListBook", listBookByCategory, null);
                session.setAttribute("ssListBookByCategory-" + idCt, listBookByCategory);
                model.addObject("listBookByCategory", listBookByCategory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
