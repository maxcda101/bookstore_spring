/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

//import dao.DBConnection;
import entity.person.employee.Employee;
import java.io.IOException;
//import java.sql.Connection;
//import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author zOzDarKzOz
 */
@Controller
@RequestMapping(value = "admin")
public class AdminHomeCtr {
    
    @RequestMapping(method = RequestMethod.GET)
    public void redirect(HttpServletRequest request,
            HttpServletResponse response, HttpSession session) throws IOException {
        Employee em = (Employee) session.getAttribute("emLogged");
        if (em == null || em.geteType().equals("customerMember")) {
            response.sendRedirect(request.getContextPath() + "/home.html");
        } else {
            String eType = em.geteType();
            switch (eType) {
                case "staffManager":
                    response.sendRedirect(request.getContextPath() + "/staffmanager");
                    break;
                case "staffStore":
                    response.sendRedirect(request.getContextPath() + "/staffstore/alldeals.html");
                    break;
                case "seller":
                    response.sendRedirect(request.getContextPath() + "/seller/allorder.html");
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/home.html");
                    break;
            }
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public @ResponseBody
    String sellerLogout(HttpServletRequest request,
            HttpServletResponse response, HttpSession session) {
        String res = "Fail";
        Employee em = (Employee) session.getAttribute("emLogged");
        if (em != null) {
            session.setAttribute("emLogged", null);
            res = request.getContextPath() + "/home.html";
        }
        return res;
    }
}
