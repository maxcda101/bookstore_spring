/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.order;

import controller.LoadDefaultData;
import dao.book.BookDAO;
import dao.order.CartDAO;
import dao.order.OrderDAO;
import dao.order.PaymentDAO;
import dao.order.ShippingInforDAO;
import dao.person.PersonDAOAbstractFactory;
import dao.person.PersonDAOFactoryProducer;
import entity.book.Book;
import entity.order.BookOrder;
import entity.order.Cart;
import entity.order.Order;
import entity.order.Payment;
import entity.order.ShippingInfor;
import entity.person.PersonAbstractFactory;
import entity.person.PersonFactoryProducer;
import entity.person.customer.Customer;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

/**
 *
 * @author zOzDarKzOz
 */
@Controller
public class OrderCtr {

    @RequestMapping(value = "member/memberorder", method = RequestMethod.POST)
    public @ResponseBody
    String mbAddressShipping(@RequestParam("idCartSave") String idCartSave,
            HttpSession session) {
        String res = "cancelOrder";
        try {
            int idCart = Integer.parseInt(idCartSave);
            Cart cart = new CartDAO().getCarSavedtById(idCart);
            Customer cusMb = (Customer) session.getAttribute("ssLogged");
            if (cart != null && cusMb != null) {
                session.setAttribute("crCartOrder", cart);
                res = cusMb.getCustomerMemberUsername();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping(value = "member/{member}/order.html", method = RequestMethod.POST)
    public void mbOrder(
            @RequestParam("mbsnumber") String mbsnumber,
            @RequestParam("mbslane") String mbslane,
            @RequestParam("mbsstreet") String mbsstreet,
            @RequestParam("mbsward") String mbsward,
            @RequestParam("mbsdistrict") String mbsdistrict,
            @RequestParam("mbscity") String mbscity,
            @RequestParam("mbscountry") String mbscountry,
            HttpSession session, HttpServletResponse response,
            @PathVariable Map<String, String> pathVars,
            HttpServletRequest request) throws IOException {

        String userName = pathVars.get("member");
        Cart cart = (Cart) session.getAttribute("crCartOrder");
        Customer cus = (Customer) session.getAttribute("ssLogged");
        if (cus == null || !cus.getCustomerType().equals("customerMember")
                || !userName.equals(cus.getCustomerMemberUsername())) {
            response.sendRedirect(request.getContextPath() + "/home.html");
        } else if (cart == null) {
            response.sendRedirect(request.getContextPath() + "/user/" + cus.getCustomerMemberUsername() + "/cartsaved.html");
        } else {
            String strRefirect = "/home.html";
            try {
                PersonAbstractFactory customerFactory = PersonFactoryProducer.getFactory("customer");
                Customer mb = customerFactory.getCustomer("customerMember");
                mb.setIdCustomer(cus.getIdCustomer());
                ShippingInfor shippingAdded = new ShippingInforDAO().addMbShippingInfor(mbsnumber, mbslane, mbsstreet, mbsward, mbsdistrict, mbscity, mbscountry, mb);
                if (shippingAdded != null) {
                    Payment paymentAdded = new PaymentDAO().addPayment(cart, true);
                    if (paymentAdded != null) {
                        try {
                            Calendar c = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String crDate = sdf.format(c.getTime());
                            Date date = new Date(sdf.parse(crDate).getTime());
                            Order order = new Order(paymentAdded, shippingAdded, date, "Đã gửi");
                            boolean orderAdded = new OrderDAO().addOrder(order);
                            if (orderAdded) {
                                boolean deletedCartSave = new CartDAO().deleteCartSave(cart.getIdCart());
                                if (deletedCartSave) {
                                    strRefirect = "/member/" + cus.getCustomerMemberUsername() + "/ordersaved.html";
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    strRefirect = "/member/" + cus.getCustomerMemberUsername() + "/cartsaved.html";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect(request.getContextPath() + strRefirect);
        }
    }

    @RequestMapping(value = "crbookorder", method = RequestMethod.POST)
    public @ResponseBody
    String getCurrentBookOrder(HttpSession session,
            @RequestParam("it") String it) {
        String res = "Fail";
        if (it != null) {
            try {
                int id = Integer.parseInt(it);
                if (id >= 1) {
                    Book book = findBookInSession(session, id);
                    if (book == null) {
                        try {
                            book = new BookDAO().getBookById(id);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (book != null) {
                        res = "OK";
                        session.setAttribute("currentBookOrder", book);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    @RequestMapping(value = "bookorder.html", method = RequestMethod.GET)
    public ModelAndView bookOrderView(HttpSession session, ModelAndView model,
            HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        Book book = (Book) session.getAttribute("currentBookOrder");
        if (book != null) {
            model = new ModelAndView("/front/bookOrder");
            model.addObject("currentBookOrder", book);
            LoadDefaultData.getAndSetCookieAndScopeOfCategoryAndBookSet(request, response, model);
            return model;
        }
        response.sendRedirect(request.getContextPath() + "/home.html");
        return null;
    }

    @RequestMapping(value = "ordersuccessful.html", method = RequestMethod.GET)
    public ModelAndView orderSuccesful(HttpSession session, ModelAndView model,
            HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        Book book = (Book) session.getAttribute("currentBookOrder");
        Cart cart = (Cart) session.getAttribute("currentCartOrder");
        if (book != null || cart != null) {
            model = new ModelAndView("/front/ordersuccess");
            model.addObject("currentBookOrder", null);
            LoadDefaultData.getAndSetCookieAndScopeOfCategoryAndBookSet(request, response, model);
            return model;
        }
        response.sendRedirect(request.getContextPath() + "/home.html");
        return null;
    }

    //////////////////////////////////////
    @RequestMapping(value = "cartorder", method = RequestMethod.POST)
    public @ResponseBody
    String bookOrder(HttpSession session,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("number") String number,
            @RequestParam("lane") String lane,
            @RequestParam("street") String street,
            @RequestParam("ward") String ward,
            @RequestParam("district") String district,
            @RequestParam("city") String city,
            @RequestParam("country") String country
    ) {
        String res = "fail";
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            return res;
        }
        if (number != null && number.length() >= 1 && number.length() <= 10
                && email != null && email.length() <= 30
                && phoneNumber != null && phoneNumber.length() >= 2 && phoneNumber.length() <= 12
                && lane != null && lane.length() >= 1 && lane.length() <= 10
                && street != null && street.length() >= 6 && street.length() <= 20
                && ward != null && ward.length() >= 6 && ward.length() <= 20
                && district != null && district.length() >= 6 && district.length() <= 20
                && city != null && city.length() >= 6 && city.length() <= 20) {
            PersonDAOAbstractFactory customerDAOFactory = PersonDAOFactoryProducer.getDAOFactory("customerDAO");
            try {
                Customer cus = customerDAOFactory.getCustomerDAO("customerNotMemberDAO").addAndGetCustomer(phoneNumber, email);
                ShippingInfor shippingAdded = new ShippingInforDAO().addMbShippingInfor(number, lane, street, ward, district, city, country, cus);
                if (shippingAdded != null) {
                    int idCart = new CartDAO().addCart(cart);
                    cart.setIdCart(idCart);
                    Payment paymentAdded = new PaymentDAO().addPayment(cart, false);
                    if (paymentAdded != null) {
                        try {
                            Calendar c = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String crDate = sdf.format(c.getTime());
                            Date date = new Date(sdf.parse(crDate).getTime());
                            Order order = new Order(paymentAdded, shippingAdded, date, "Đã gửi");
                            boolean orderAdded = new OrderDAO().addOrder(order);
                            if (orderAdded) {
                                res = "OK";
                                session.setAttribute("currentCartOrder", cart);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    //////////////////////////////////////
    @RequestMapping(value = "bookorder", method = RequestMethod.POST)
    public @ResponseBody
    String bookOrder(HttpSession session,
            @RequestParam("q") String q,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("number") String number,
            @RequestParam("lane") String lane,
            @RequestParam("street") String street,
            @RequestParam("ward") String ward,
            @RequestParam("district") String district,
            @RequestParam("city") String city,
            @RequestParam("country") String country
    ) {
        String res = "fail";
        Book book = (Book) session.getAttribute("currentBookOrder");
        if (book == null) {
            return res;
        }
        if (number != null && number.length() >= 1 && number.length() <= 10
                && email != null && email.length() <= 30
                && phoneNumber != null && phoneNumber.length() >= 2 && phoneNumber.length() <= 12
                && lane != null && lane.length() >= 1 && lane.length() <= 10
                && street != null && street.length() >= 6 && street.length() <= 20
                && ward != null && ward.length() >= 6 && ward.length() <= 20
                && district != null && district.length() >= 6 && district.length() <= 20
                && city != null && city.length() >= 6 && city.length() <= 20) {
            PersonDAOAbstractFactory customerDAOFactory = PersonDAOFactoryProducer.getDAOFactory("customerDAO");
            try {
                Customer cus = customerDAOFactory.getCustomerDAO("customerNotMemberDAO").addAndGetCustomer(phoneNumber, email);
                int qtty = Integer.parseInt(q);
                float totalPr = (float) qtty * Float.parseFloat(book.getSalePrice());
                BookOrder bod = new BookOrder(qtty, totalPr, book);
                ArrayList<BookOrder> listBod = new ArrayList<>();
                listBod.add(bod);
                Cart cart = new Cart(listBod, totalPr);
                ShippingInfor shippingAdded = new ShippingInforDAO().addMbShippingInfor(number, lane, street, ward, district, city, country, cus);
                if (shippingAdded != null) {
                    int idCart = new CartDAO().addCart(cart);
                    cart.setIdCart(idCart);
                    Payment paymentAdded = new PaymentDAO().addPayment(cart, false);
                    if (paymentAdded != null) {
                        try {
                            Calendar c = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String crDate = sdf.format(c.getTime());
                            Date date = new Date(sdf.parse(crDate).getTime());
                            Order order = new Order(paymentAdded, shippingAdded, date, "Đã gửi");
                            boolean orderAdded = new OrderDAO().addOrder(order);
                            if (orderAdded) {
                                res = "OK";
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    @RequestMapping(value = "member/{member}/bookorder.html", method = RequestMethod.GET)
    public ModelAndView mbBookOrderView(HttpSession session, ModelAndView model,
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable Map<String, String> pathVars
    ) throws IOException {
        String username = pathVars.get("member");
        Customer crCusMb = (Customer) session.getAttribute("ssLogged");
        if (crCusMb == null
                || !crCusMb.getCustomerType().equals("customerMember")
                || !username.equals(crCusMb.getCustomerMemberUsername())) {
            response.sendRedirect(request.getContextPath() + "/home.html");
        } else {
            model = new ModelAndView("/front/memberBookOrder");
            Book book = (Book) session.getAttribute("currentBookOrder");
            if (book != null) {
                model.addObject("currentBookOrder", book);
                model.addObject("customerMemberUsername", crCusMb.getCustomerMemberUsername());
            } else {
                model.addObject("bookNotFound", true);
            }
            LoadDefaultData.getAndSetCookieAndScopeOfCategoryAndBookSet(request, response, model);
            return model;
        }
        return null;
    }

    @RequestMapping(value = "member/{member}/bookorder", method = RequestMethod.POST)
    public @ResponseBody
    String mbBookOrder(HttpSession session,
            @RequestParam("q") String q,
            @RequestParam("number") String number,
            @RequestParam("lane") String lane,
            @RequestParam("street") String street,
            @RequestParam("ward") String ward,
            @RequestParam("district") String district,
            @RequestParam("city") String city,
            @RequestParam("country") String country,
            @PathVariable Map<String, String> pathVars
    ) {
        String res = "fail";
        String username = pathVars.get("member");
        Customer crCusMb = (Customer) session.getAttribute("ssLogged");
        Book book = (Book) session.getAttribute("currentBookOrder");
        if (crCusMb == null
                || !crCusMb.getCustomerType().equals("customerMember")
                || !username.equals(crCusMb.getCustomerMemberUsername())
                || book == null) {
            return res;
        }
        if (number != null && number.length() >= 1 && number.length() <= 10
                && lane != null && lane.length() >= 1 && lane.length() <= 10
                && street != null && street.length() >= 6 && street.length() <= 20
                && ward != null && ward.length() >= 6 && ward.length() <= 20
                && district != null && district.length() >= 6 && district.length() <= 20
                && city != null && city.length() >= 6 && city.length() <= 20) {
            try {
                PersonAbstractFactory customerFactory = PersonFactoryProducer.getFactory("customer");
                Customer mb = customerFactory.getCustomer("customerMember");
                mb.setIdCustomer(crCusMb.getIdCustomer());
                int qtty = Integer.parseInt(q);
                float totalPr = (float) qtty * Float.parseFloat(book.getSalePrice());
                BookOrder bod = new BookOrder(qtty, totalPr, book);
                ArrayList<BookOrder> listBod = new ArrayList<>();
                listBod.add(bod);
                Cart cart = new Cart(listBod, totalPr);
                ShippingInfor shippingAdded = new ShippingInforDAO().addMbShippingInfor(number, lane, street, ward, district, city, country, mb);
                if (shippingAdded != null) {
                    int idCart = new CartDAO().saveCartAndGetCartId(cart, crCusMb.getIdCustomer());
                    cart.setIdCart(idCart);
                    Payment paymentAdded = new PaymentDAO().addPayment(cart, true);
                    if (paymentAdded != null) {
                        try {
                            Calendar c = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String crDate = sdf.format(c.getTime());
                            Date date = new Date(sdf.parse(crDate).getTime());
                            Order order = new Order(paymentAdded, shippingAdded, date, "Đã gửi");
                            boolean orderAdded = new OrderDAO().addOrder(order);
                            if (orderAdded) {
                                boolean deletedCartSave = new CartDAO().deleteCartSave(cart.getIdCart());
                                if (deletedCartSave) {
                                    res = "OK";
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return res;
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
}
