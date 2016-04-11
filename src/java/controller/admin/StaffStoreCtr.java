/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import controller.LoadDefaultData;
import dao.book.BookDAO;
import dao.book.DealsDAO;
import entity.book.Book;
import entity.book.BookSet;
import entity.book.Category;
import entity.book.Deals;
import entity.person.employee.Employee;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tool.MyTool;

/**
 *
 * @author zOzDarKzOz
 */
@Controller
@RequestMapping(value = "staffstore")
public class StaffStoreCtr {

    @RequestMapping(value = "/alldeals.html")
    public ModelAndView allDeals(ModelAndView model,
            HttpSession session, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        Employee ee = (Employee) session.getAttribute("emLogged");
        if (ee == null || !ee.geteType().equals("staffStore")) {
            response.sendRedirect(request.getContextPath() + "/home.html");
        } else {
            model = new ModelAndView("/back/staffStore");
            model.addObject("title", "Staff Store");
            ArrayList<Deals> allDeals = (ArrayList<Deals>) session.getAttribute("ssAllDeals");
            if (allDeals == null) {
                try {
                    allDeals = new DealsDAO().getAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (allDeals != null && allDeals.size() > 0) {
                model.addObject("allDeals", allDeals);
                int pg = allDeals.size() / 10;
                if ((pg * 10) < allDeals.size()) {
                    pg += 1;
                }
                if (pg > 1) {
                    model.addObject("pageCount", pg);
                }
            }
            return model;
        }
        return null;
    }

    @RequestMapping(value = "/deals/{crId}-{crCode}.html")
    public ModelAndView dealsDetails(ModelAndView model,
            HttpSession session, HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars) throws IOException {
        Employee ee = (Employee) session.getAttribute("emLogged");
        if (ee == null || !ee.geteType().equals("staffStore")) {
            response.sendRedirect(request.getContextPath() + "/home.html");
        } else {
            model = new ModelAndView("/back/deals");
            model.addObject("title", "Staff Store");

            String crId = pathVars.get("crId");
            String crCode = pathVars.get("crCode");
            if (crCode != null && crId != null) {
                int id = Integer.parseInt(crId);
                Deals dls = null;
                ArrayList<Deals> allDeals = (ArrayList<Deals>) session.getAttribute("ssAllDeals");
                if (allDeals != null) {
                    for (Deals deals : allDeals) {
                        if (deals.getIdDeals() == id) {
                            dls = deals;
                        }
                    }
                }
                if (dls == null) {
                    try {
                        dls = new DealsDAO().getDealsById(id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (dls != null && dls.getCode().equals(crCode)) {
                    model.addObject("crDeals", dls);
                    try {
                        ArrayList<Book> allBookByDeals = new BookDAO().getListBookByIdDeals(id);
                        if (allBookByDeals != null) {
                            model.addObject("allBookByDeals", allBookByDeals);
                            int pg = allBookByDeals.size() / 5;
                            if ((pg * 5) < allBookByDeals.size()) {
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
                return model;
            }
        }
        return null;
    }

    @RequestMapping(value = "/deals/{crId}-{crCode}/applydeals.html")
    public ModelAndView applyDeals(ModelAndView model,
            HttpSession session, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(name = "bs", required = false) String bs,
            @RequestParam(name = "ct", required = false) String ct,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(value = "op", required = false) String option,
            @RequestParam(value = "vl", required = false) String value,
            @PathVariable Map<String, String> pathVars) throws IOException {
        Employee ee = (Employee) session.getAttribute("emLogged");
        if (ee == null || !ee.geteType().equals("staffStore")) {
            response.sendRedirect(request.getContextPath() + "/home.html");
        } else {
            model = new ModelAndView("/back/applyDeals");
            model.addObject("title", "Staff Store");

            String crId = pathVars.get("crId");
            String crCode = pathVars.get("crCode");
            if (crCode != null && crId != null) {
                int id = Integer.parseInt(crId);
                Deals dls = null;
                ArrayList<Deals> allDeals = (ArrayList<Deals>) session.getAttribute("ssAllDeals");
                if (allDeals != null) {
                    for (Deals deals : allDeals) {
                        if (deals.getIdDeals() == id) {
                            dls = deals;
                        }
                    }
                }
                if (dls == null) {
                    try {
                        dls = new DealsDAO().getDealsById(id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (dls != null && dls.getCode().equals(crCode)) {
                    model.addObject("crDeals", dls);
                    LoadDefaultData.getAndSetCookieAndScopeOfCategoryAndBookSet(request, response, model);
                    doSearch(bs, ct, name, model, request, id, option, value);
                }
                return model;
            }
        }
        return null;
    }

    private void doSearch(String bs, String ct, String name, ModelAndView model,
            HttpServletRequest request, int id, String option, String value) {
        if (bs != null && bs.length() >= 1 && name != null && name.length() >= 1) {
            doSearchByBookSet(bs, name, model, request, id);
        } else if (ct != null && ct.length() >= 1 && name != null && name.length() >= 1) {
            doSearchByCategory(ct, name, model, request, id);
        } else if (option != null && value != null) {
            if (option.matches("n1")) {
                searchBookByNameInDB(model, value, id);
                model.addObject("vl", value);
                model.addObject("op", option);
            } else if (option.matches("ct([0-9]){1,}")) {
                searchBookByNameAndCategoryInDB(model, option, value, request, id);
                model.addObject("op", option);
            } else if (option.matches("bs([0-9]){1,}")) {
                searchBookByNameAndBookSetInDB(model, option, value, request, id);
                model.addObject("op", option);
            }
        }
    }

    private void searchBookByNameAndBookSetInDB(
            ModelAndView model, String option, String value,
            HttpServletRequest request, int id) {
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
                ArrayList<Book> allBookBySearch = new ArrayList<>();
                try {
                    allBookBySearch = new BookDAO().getListOfBookByNameAndIdBookSetAndIdDeals(name, idBs, id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (allBookBySearch != null) {
                    model.addObject("allBookBySearch", allBookBySearch);
                    int pg = allBookBySearch.size() / 5;
                    if ((pg * 5) < allBookBySearch.size()) {
                        pg += 1;
                    }
                    if (pg > 1) {
                        model.addObject("pageCount", pg);
                    }
                }
                model.addObject("vl", name);
            }
        } catch (NumberFormatException | JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    private void searchBookByNameAndCategoryInDB(
            ModelAndView model, String option, String value,
            HttpServletRequest request, int id) {
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
                ArrayList<Book> allBookBySearch = new ArrayList<>();
                try {
                    allBookBySearch = new BookDAO().getListOfBookByNameAndIdCategoryAndIdDeals(name, idCt, id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (allBookBySearch != null) {
                    model.addObject("allBookBySearch", allBookBySearch);
                    int pg = allBookBySearch.size() / 5;
                    if ((pg * 5) < allBookBySearch.size()) {
                        pg += 1;
                    }
                    if (pg > 1) {
                        model.addObject("pageCount", pg);
                    }
                }
                model.addObject("vl", name);
            }
        } catch (NumberFormatException | JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    private void searchBookByNameInDB(ModelAndView model, String value, int id) {
        try {
            String name = MyTool.handleInputString(value);
            ArrayList<Book> allBookBySearch = new ArrayList<>();
            try {
                allBookBySearch = new BookDAO().getListOfBookByNameAndIdDeals(name, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (allBookBySearch != null) {
                model.addObject("allBookBySearch", allBookBySearch);
                int pg = allBookBySearch.size() / 5;
                if ((pg * 5) < allBookBySearch.size()) {
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

    private void doSearchByBookSet(String bs, String name,
            ModelAndView model, HttpServletRequest request, int id) {
        try {
            int bsIndex = Integer.parseInt(bs);
            Gson gson = new GsonBuilder().create();
            String jsonBs = MyTool.getCookieByName(request, "ckBookSet");
            jsonBs = MyTool.handleCookieString(jsonBs);
            Type bsType = new TypeToken<ArrayList<BookSet>>() {
            }.getType();
            ArrayList<BookSet> listBookSet = (ArrayList<BookSet>) gson.fromJson(jsonBs, bsType);
            if (listBookSet.get(bsIndex).getSortLink().equals(name)) {
                int idBs = listBookSet.get(bsIndex).getIdBookSet();
                ArrayList<Book> allBookBySearch = new BookDAO().getListOfBookByIdBookSetAndIdDeals(idBs, id);
                if (allBookBySearch != null) {
                    int pg = allBookBySearch.size() / 5;
                    if ((pg * 5) < allBookBySearch.size()) {
                        pg += 1;
                    }
                    if (pg > 1) {
                        model.addObject("pageCount", pg);
                        model.addObject("bs", bs);
                        model.addObject("name", name);
                    }
                    model.addObject("allBookBySearch", allBookBySearch);
                }
            }
        } catch (NumberFormatException | JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    private void doSearchByCategory(String ct, String name,
            ModelAndView model, HttpServletRequest request, int id) {
        try {
            int ctIndex = Integer.parseInt(ct);
            Gson gson = new GsonBuilder().create();
            String jsonCt = MyTool.getCookieByName(request, "ckCategory");
            jsonCt = MyTool.handleCookieString(jsonCt);
            Type ctType = new TypeToken<ArrayList<Category>>() {
            }.getType();
            ArrayList<Category> listCategory = (ArrayList<Category>) gson.fromJson(jsonCt, ctType);
            if (listCategory.get(ctIndex).getSortLink().equals(name)) {
                int idCt = listCategory.get(ctIndex).getIdCategory();
                ArrayList<Book> allBookBySearch = new BookDAO().getListOfBookByIdCategoryAndIdDeals(idCt, id);
                if (allBookBySearch != null) {
                    int pg = allBookBySearch.size() / 5;
                    if ((pg * 5) < allBookBySearch.size()) {
                        pg += 1;
                    }
                    if (pg > 1) {
                        model.addObject("pageCount", pg);
                        model.addObject("ct", ct);
                        model.addObject("name", name);
                    }
                    model.addObject("allBookBySearch", allBookBySearch);
                }
            }
        } catch (NumberFormatException | JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    private void doSearchBySearchForm() {

    }

    @RequestMapping(value = "/addDeals")
    public @ResponseBody
    String addDeals(HttpSession session,
            @RequestParam("code") String code,
            @RequestParam("description") String description,
            @RequestParam("discount") String discount,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        String res = "Fail";
        if (code != null && code.length() > 5 && code.length() < 20
                && description != null
                && discount != null
                && startDate != null && startDate.matches("[2-9]([0-9]){3,}-[0-1][0-9]-[0-3][0-9]")
                && endDate != null && endDate.matches("[2-9]([0-9]){3,}-[0-1][0-9]-[0-3][0-9]")) {
            try {
                int disc = Integer.parseInt(discount);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date sdate = sdf.parse(startDate);
                Date edate = sdf.parse(endDate);
                Deals deals = new Deals(code, description, disc, new java.sql.Date(sdate.getTime()), new java.sql.Date(edate.getTime()), 1);
                deals = new DealsDAO().addDeals(deals);

                ArrayList<Deals> allDeals = (ArrayList<Deals>) session.getAttribute("allDeals");
                if (deals != null) {
                    res = "OK";
                    if (allDeals == null) {
                        allDeals = new ArrayList<>();
                    }
                    allDeals.add(deals);
                }
            } catch (NumberFormatException | ParseException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    @RequestMapping(value = "/editDeals")
    public @ResponseBody
    String editDeals(HttpSession session,
            @RequestParam("idD") String idD,
            @RequestParam("code") String code,
            @RequestParam("description") String description,
            @RequestParam("discount") String discount,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("status") String status,
            HttpServletRequest request
    ) {
        String res = "Fail";
        if (code != null && code.length() > 5 && code.length() < 20
                && description != null
                && discount != null
                && startDate != null && startDate.matches("[2-9]([0-9]){3,}-[0-1][0-9]-[0-3][0-9]")
                && endDate != null && endDate.matches("[2-9]([0-9]){3,}-[0-1][0-9]-[0-3][0-9]")
                && idD != null) {
            try {
                int id = Integer.parseInt(idD);
                int disc = Integer.parseInt(discount);
                int st = Integer.parseInt(status);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date sdate = sdf.parse(startDate);
                Date edate = sdf.parse(endDate);
                Deals deals = new Deals(id, code, description, disc, new java.sql.Date(sdate.getTime()), new java.sql.Date(edate.getTime()), st);
                deals = new DealsDAO().updateDeals(deals);

                if (deals != null) {
                    res = request.getContextPath() + "/staffstore/deals/" + deals.getIdDeals() + "-" + deals.getCode() + ".html";
                }
            } catch (NumberFormatException | ParseException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    @RequestMapping(value = "/deleteDeals", method = RequestMethod.POST)
    public @ResponseBody
    String deleteDeals(
            @RequestParam("crId") String crId
    ) {
        String res = "Fail";
        if (crId != null) {
            try {
                int id = Integer.parseInt(crId);
                boolean isDeleted = new DealsDAO().deleteDeals(id);
                if (isDeleted) {
                    res = "OK";
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    @RequestMapping(value = "/deals/applydeals", method = RequestMethod.POST)
    public @ResponseBody
    String applyDeals(HttpSession session,
            @RequestParam("idB") String idB,
            @RequestParam("idD") String idD,
            @RequestParam("code") String code
    ) {
        String res = "canntApply";
        if (idB != null && idD != null && code != null) {
            try {
                int idBook = Integer.parseInt(idB);
                int idDeals = Integer.parseInt(idD);
                Deals dls = null;
                ArrayList<Deals> allDeals = (ArrayList<Deals>) session.getAttribute("ssAllDeals");
                if (allDeals != null) {
                    for (Deals deals : allDeals) {
                        if (deals.getIdDeals() == idDeals) {
                            dls = deals;
                        }
                    }
                }
                if (dls == null) {
                    try {
                        dls = new DealsDAO().getDealsById(idDeals);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (dls != null && dls.getCode().equals(code)) {
                    boolean isApplied = new DealsDAO().applyDeals(idBook, idDeals);
                    if (isApplied) {
                        res = "OK";
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    @RequestMapping(value = "/deals/canceldeals", method = RequestMethod.POST)
    public @ResponseBody
    String cancelDeals(HttpSession session,
            @RequestParam("idB") String idB,
            @RequestParam("idD") String idD,
            @RequestParam("code") String code
    ) {
        String res = "Fail";
        if (idB != null && idD != null && code != null) {
            try {
                int idBook = Integer.parseInt(idB);
                int idDeals = Integer.parseInt(idD);
                Deals dls = null;
                ArrayList<Deals> allDeals = (ArrayList<Deals>) session.getAttribute("ssAllDeals");
                if (allDeals != null) {
                    for (Deals deals : allDeals) {
                        if (deals.getIdDeals() == idDeals) {
                            dls = deals;
                        }
                    }
                }
                if (dls == null) {
                    try {
                        dls = new DealsDAO().getDealsById(idDeals);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (dls != null && dls.getCode().equals(code)) {
                    boolean isApplied = new DealsDAO().cancelDeals(idBook, idDeals);
                    if (isApplied) {
                        res = "OK";
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

}
