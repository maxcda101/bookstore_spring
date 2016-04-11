/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import dao.book.BookSetDAO;
import dao.book.CategoryDAO;
import entity.book.BookSet;
import entity.book.Category;
import java.lang.reflect.Type;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import tool.MyTool;

/**
 *
 * @author zOzDarKzOz
 */
public class LoadDefaultData {

    public static void getAndSetCookieAndScopeOfCategoryAndBookSet(
            HttpServletRequest request, HttpServletResponse response,
            ModelAndView model) {
        Gson gson = new GsonBuilder().create();

        String jsonCt = MyTool.getCookieByName(request, "ckCategory");
        String jsonBs = MyTool.getCookieByName(request, "ckBookSet");
        ArrayList<Category> listCategory = new ArrayList<>();
        ArrayList<BookSet> listBookSet = new ArrayList<>();
        try {
            if (jsonCt == null || jsonBs == null) {
                if (jsonCt == null) {
                    listCategory = new CategoryDAO().getAll();
                    jsonCt = gson.toJson(listCategory);
                    MyTool.setCookie(response, "ckCategory", jsonCt);
                }
                if (jsonBs == null) {
                    listBookSet = new BookSetDAO().getAll();
                    jsonBs = gson.toJson(listBookSet);
                    MyTool.setCookie(response, "ckBookSet", jsonBs);
                }
            } else {
                jsonCt = MyTool.handleCookieString(jsonCt);
                jsonBs = MyTool.handleCookieString(jsonBs);
                Type ctType = new TypeToken<ArrayList<Category>>() {
                }.getType();
                listCategory = (ArrayList<Category>) gson.fromJson(jsonCt, ctType);
                Type bsType = new TypeToken<ArrayList<BookSet>>() {
                }.getType();
                listBookSet = (ArrayList<BookSet>) gson.fromJson(jsonBs, bsType);
            }
            model.addObject("scCategory", listCategory);
            model.addObject("scBookSet", listBookSet);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }
}
