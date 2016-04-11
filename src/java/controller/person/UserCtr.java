/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.person;

import controller.LoadDefaultData;
import dao.person.PersonDAOAbstractFactory;
import dao.person.PersonDAOFactoryProducer;
import entity.person.PersonAbstractFactory;
import entity.person.PersonFactoryProducer;
import entity.person.address.Address;
import entity.person.customer.Customer;
import entity.person.fullname.FullName;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author zOzDarKzOz
 */
@Controller
@RequestMapping(value = "user/")
public class UserCtr {

    @RequestMapping(method = RequestMethod.GET)
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/home.html");
    }

    @RequestMapping(value = "signup.html", method = RequestMethod.GET)
    public ModelAndView signUp(ModelAndView model, HttpSession session,
            HttpServletRequest request, HttpServletResponse response) {
        model = new ModelAndView("/front/signup");
        LoadDefaultData.getAndSetCookieAndScopeOfCategoryAndBookSet(request, response, model);
        String crUrl = (String) session.getAttribute("crUrl");
        if (crUrl != null) {
            model.addObject("crUrl", crUrl);
        }
        return model;
    }

    @RequestMapping(value = "signup.html", method = RequestMethod.POST)
    public ModelAndView signup(ModelAndView model,
            @RequestParam("firstName") String firstName,
            @RequestParam("middleName") String middleName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("rePassword") String rePassword,
            @RequestParam("number") String number,
            @RequestParam("lane") String lane,
            @RequestParam("street") String street,
            @RequestParam("ward") String ward,
            @RequestParam("district") String district,
            @RequestParam("city") String city,
            @RequestParam("country") String country,
            HttpServletRequest request, HttpServletResponse response
    ) {
        model = new ModelAndView("/front/signup");
        LoadDefaultData.getAndSetCookieAndScopeOfCategoryAndBookSet(request, response, model);
        if (firstName != null && firstName.length() >= 2 && firstName.length() <= 10
                && middleName != null && middleName.length() >= 2 && middleName.length() <= 10
                && lastName != null && lastName.length() >= 2 && lastName.length() <= 10
                && email != null && email.length() <= 30
                && phoneNumber != null && phoneNumber.length() >= 2 && phoneNumber.length() <= 12
                && username != null && username.length() >= 6 && username.length() <= 30
                && password != null && password.length() >= 6 && password.length() <= 30
                && rePassword.equals(password)
                && number != null && number.length() >= 1 && number.length() <= 10
                && lane != null && lane.length() >= 1 && lane.length() <= 10
                && street != null && street.length() >= 6 && street.length() <= 20
                && ward != null && ward.length() >= 6 && ward.length() <= 20
                && district != null && district.length() >= 6 && district.length() <= 20
                && city != null && city.length() >= 6 && city.length() <= 20) {

            Address add = new Address(number, lane, street, ward, district, city, country);
            FullName fn = new FullName(firstName, middleName, lastName);
            PersonAbstractFactory customerFactory = PersonFactoryProducer.getFactory("customer");
            Customer cus = customerFactory.getCustomer("customerMember");
            cus.setAddress(add);
            cus.setEmail(email);
            cus.setFullName(fn);
            cus.setPhoneNum(phoneNumber);
            cus.setCustomerMemberUsername(username);
            cus.setCustomerMemberPassword(password);
            PersonDAOAbstractFactory customerDAOFactory = PersonDAOFactoryProducer.getDAOFactory("customerDAO");
            try {
                boolean isAddedMember = customerDAOFactory.getCustomerDAO("customerMemberDAO").addCustomerMember(cus);
                if (isAddedMember) {
                    model.addObject("registed", true);
                    return model;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        model.addObject("rgError", true);
        model.addObject("firstName", firstName);
        model.addObject("middleName", middleName);
        model.addObject("lastName", lastName);
        model.addObject("email", email);
        model.addObject("phoneNumber", phoneNumber);
        model.addObject("username", username);
        model.addObject("number", number);
        model.addObject("lane", lane);
        model.addObject("street", street);
        model.addObject("ward", ward);
        model.addObject("district", district);
        return model;
    }

}
