/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.person;

import controller.LoadDefaultData;
import dao.order.CartDAO;
import dao.order.OrderDAO;
import dao.person.PersonDAOAbstractFactory;
import dao.person.PersonDAOFactoryProducer;
import entity.order.Cart;
import entity.order.Order;
import entity.person.PersonAbstractFactory;
import entity.person.PersonFactoryProducer;
import entity.person.customer.Customer;
import entity.person.employee.Employee;
import java.io.IOException;
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
@RequestMapping(value = "member/")
public class MemberCtr {

    @RequestMapping(method = RequestMethod.GET)
    public void indexMb(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/home.html");
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public @ResponseBody
    String redirectLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("crUrl") String crUrl,
            HttpSession session
    ) {
        if (username != null && username.length() >= 6
                && password != null && password.length() >= 6
                && crUrl != null && crUrl.length() > 0) {
            username = MyTool.handleInputString(username);
            password = password.trim();
            PersonDAOAbstractFactory customerDAOFactory = PersonDAOFactoryProducer.getDAOFactory("customerDAO");
            PersonDAOAbstractFactory employeeDAOFactory = PersonDAOFactoryProducer.getDAOFactory("employeeDAO");
            try {
                Customer cus = customerDAOFactory.getCustomerDAO("customerMemberDAO").getMemberByUsernameAndPassword(username, password);
                if (cus != null) {
                    session.setAttribute("ssLogged", cus);
                    return crUrl + "###" + "MemberLogged";
                } else {
                    Employee em = employeeDAOFactory.getEmployeeDAO("employeeDAO").getEmployeeByUsernameAndPassword(username, password);
                    if (em != null) {
                        session.setAttribute("emLogged", em);
                        return crUrl + "###" + "EmployeeLogged";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return crUrl + "###" + "LoginFail";
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public @ResponseBody
    String redirectLogout(
            @RequestParam("crUrl") String crUrl,
            HttpSession session
    ) {
        if (crUrl != null && crUrl.length() > 0) {
            session.setAttribute("ssLogged", null);
            return crUrl;
        }
        return "Fail";
    }

    @RequestMapping(value = "{member}/infor.html")
    public ModelAndView infor(HttpSession session, ModelAndView model,
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable Map<String, String> pathVars
    ) throws IOException {
        String userName = pathVars.get("member");
        Customer cusMb = (Customer) session.getAttribute("ssLogged");
        if (cusMb != null
                && cusMb.getCustomerType().equals("customerMember")
                && cusMb.getCustomerType().equals("customerMember")
                && userName.equals(cusMb.getCustomerMemberUsername())) {
            model = new ModelAndView("/front/memberInfor");
            try {
                model.addObject("crMb", cusMb);
                model.addObject("crMbFullName", cusMb.getFullName().getFullName(cusMb.getFullName().getFirstName(), cusMb.getFullName().getMiddleName(), cusMb.getFullName().getLastName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            LoadDefaultData.getAndSetCookieAndScopeOfCategoryAndBookSet(request, response, model);
            return model;
        } else {
            response.sendRedirect(request.getContextPath() + "/home.html");
        }
        return null;
    }
    @RequestMapping(value = "{member}/cartsaved.html")
    public ModelAndView cartSaved(HttpSession session, ModelAndView model,
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable Map<String, String> pathVars
    ) throws IOException {
        String userName = pathVars.get("member");
        Customer cusMb = (Customer) session.getAttribute("ssLogged");
        if (cusMb != null
                && cusMb.getCustomerType().equals("customerMember")
                && cusMb.getCustomerType().equals("customerMember")
                && userName.equals(cusMb.getCustomerMemberUsername())) {
            model = new ModelAndView("/front/cartSaved");
            try {
                ArrayList<Cart> listCart = new CartDAO().getListCartByMbId(cusMb.getCustomerMemberId());
                model.addObject("currentListCart", listCart);
            } catch (Exception e) {
                e.printStackTrace();
            }
            LoadDefaultData.getAndSetCookieAndScopeOfCategoryAndBookSet(request, response, model);
            return model;
        } else {
            response.sendRedirect(request.getContextPath() + "/home.html");
        }
        return null;
    }

    @RequestMapping(value = "deleteCsv", method = RequestMethod.POST)
    public @ResponseBody
    String deleteCartSaved(@RequestParam("idCartSave") String idCartSave, HttpSession session) {
        String res = "cancelDelete";
        try {
            int idCart = Integer.parseInt(idCartSave);
            boolean deletedCartSave = new CartDAO().deleteCartSave(idCart);
            Customer cusMb = (Customer) session.getAttribute("ssLogged");
            if (deletedCartSave && cusMb != null) {
                res = cusMb.getCustomerMemberUsername();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping(value = "{member}/addressshipping.html")
    public ModelAndView addressShipping(HttpSession session, ModelAndView model,
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable Map<String, String> pathVars
    ) throws IOException {
        String userName = pathVars.get("member");
        Cart crCart = (Cart) session.getAttribute("crCartOrder");
        Customer mb = (Customer) session.getAttribute("ssLogged");
        if (mb == null || !mb.getCustomerType().equals("customerMember")
                || !userName.equals(mb.getCustomerMemberUsername())) {
            response.sendRedirect(request.getContextPath() + "/home.html");
        } else if (crCart == null) {
            response.sendRedirect(request.getContextPath() + "/user/" + mb.getCustomerMemberUsername() + "/cartsaved.html");
        } else {
            model = new ModelAndView("/front/addressShipping");
            model.addObject("customerMemberUsername", mb.getCustomerMemberUsername());
            LoadDefaultData.getAndSetCookieAndScopeOfCategoryAndBookSet(request, response, model);
            return model;
        }
        return null;
    }

    @RequestMapping(value = "{member}/ordersaved.html", method = RequestMethod.GET)
    public ModelAndView order(HttpSession session, ModelAndView model,
            @PathVariable Map<String, String> pathVars,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        String username = pathVars.get("member");
        Customer crCusMb = (Customer) session.getAttribute("ssLogged");
        if (crCusMb == null
                || !crCusMb.getCustomerType().equals("customerMember")
                || !username.equals(crCusMb.getCustomerMemberUsername())) {
            response.sendRedirect(request.getContextPath() + "/home.html");
        } else {
            model = new ModelAndView("/front/orderSaved");
            try {
                Customer cusMb = (Customer) session.getAttribute("ssLogged");
                if (cusMb != null && username.equals(cusMb.getCustomerMemberUsername())) {
                    PersonAbstractFactory customerFactory = PersonFactoryProducer.getFactory("customer");
                    Customer mb = customerFactory.getCustomer("customerMember");
                    mb.setIdCustomer(cusMb.getIdCustomer());
                    ArrayList<Order> listOrder = new OrderDAO().getListOrderByMbId(mb);
                    model.addObject("crListOrder", listOrder);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            LoadDefaultData.getAndSetCookieAndScopeOfCategoryAndBookSet(request, response, model);
            return model;
        }
        return null;
    }
}
