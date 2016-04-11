/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.order;

import controller.LoadDefaultData;
import dao.order.CartDAO;
import entity.book.Book;
import entity.order.BookOrder;
import entity.order.Cart;
import entity.person.customer.Customer;
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

/**
 *
 * @author zOzDarKzOz
 */
@Controller
public class CartCtr {

    @RequestMapping(value = "cart.html")
    public ModelAndView cart(ModelAndView model,
            HttpServletRequest request, HttpServletResponse response,
            HttpSession session) {
        Customer crCusMb = (Customer) session.getAttribute("ssLogged");
        if (crCusMb == null || !crCusMb.getCustomerType().equals("customerMember")) {
            model = new ModelAndView("/front/cart");
        } else {
            model = new ModelAndView("/front/memberCart");
        }
        LoadDefaultData.getAndSetCookieAndScopeOfCategoryAndBookSet(request, response, model);
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null && cart.getListBook().size() > 0) {
            model.addObject("crListBookOrder", cart.getListBook());
            model.addObject("crTotalPrice", cart.getTotalPrice());
        } else {
            model.addObject("emptyCart", true);
        }
        return model;
    }

    @RequestMapping(value = "cart/addToCart", method = RequestMethod.POST)
    public @ResponseBody
    String addToCart(@RequestParam("item") String item, HttpSession session,
            HttpServletRequest request) {
        String res = null;
        try {
            int id = Integer.parseInt(item);
            ArrayList<Book> listBook = (ArrayList<Book>) session.getAttribute("ssListBook");
            Book book = null;
            if (listBook != null && listBook.size() > 0) {
                int size = listBook.size();
                for (int i = 0; i < size; i++) {
                    if (listBook.get(i).getIdBook() == id) {
                        book = listBook.get(i);
                        break;
                    }
                }
            }
            float price = Float.parseFloat(book.getSalePrice());
            BookOrder bookOrder = new BookOrder(1, price, book);
            Cart cart = (Cart) session.getAttribute("cart");
            ArrayList<BookOrder> listBookOrder = new ArrayList<>();
            float oldTotalPrice = 0;
            if (cart != null) {
                listBookOrder = cart.getListBook();
                oldTotalPrice = cart.getTotalPrice();
            }
            listBookOrder.add(bookOrder);
            cart = new Cart(listBookOrder, oldTotalPrice + price);
            res = cart.getListBook().size() + "#" + cart.getTotalPrice();
            session.setAttribute("cart", cart);
            session.setAttribute("cartStr", res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping(value = "cart/update", method = RequestMethod.POST)
    public @ResponseBody
    String addToCart(
            @RequestParam("strCart") String strCart,
            @RequestParam("nTtPr") String nTtPr,
            HttpSession session) {
        String res = null;
        try {
            float ttpr = Float.parseFloat(nTtPr);
            String[] s = strCart.split("#");
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart != null) {
                ArrayList<BookOrder> listBookOrder = cart.getListBook();
                for (int i = s.length - 1; i >= 0; i--) {
                    String[] str = s[i].split("@");
                    int index = Integer.parseInt(str[0]);
                    int qtt = Integer.parseInt(str[1]);
                    BookOrder bod = listBookOrder.get(index);
                    float pr = Float.parseFloat(bod.getBook().getSalePrice()) * qtt;
                    bod = new BookOrder(qtt, pr, bod.getBook());
                    listBookOrder.set(index, bod);
                }
                cart.setListBook(listBookOrder);
                cart.setTotalPrice(ttpr);
                res = cart.getListBook().size() + "#" + cart.getTotalPrice();
                session.setAttribute("cart", cart);
                session.setAttribute("cartStr", res);
                res = "updated";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    @RequestMapping(value = "cart/delete/{index}")
    public void addToCart(@PathVariable Map<String, String> pathVars, HttpSession session,
            HttpServletResponse response, HttpServletRequest request) throws IOException {
        String sIndex = pathVars.get("index");
        try {
            int index = Integer.parseInt(sIndex);
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart != null) {
                ArrayList<BookOrder> listBookOrder = cart.getListBook();
                float oldTtpr = listBookOrder.get(index).getTotalPrice();
                listBookOrder.remove(index);
                cart.setListBook(listBookOrder);
                cart.setTotalPrice(cart.getTotalPrice() - oldTtpr);
                String res = cart.getListBook().size() + "#" + cart.getTotalPrice();
                session.setAttribute("cart", cart);
                session.setAttribute("cartStr", res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/cart.html");
    }

    @RequestMapping(value = "cart/save", method = RequestMethod.POST)
    public @ResponseBody
    String save(HttpSession session) {
        String res = "";
        try {
            Cart cart = (Cart) session.getAttribute("cart");
            Customer cusMb = (Customer) session.getAttribute("ssLogged");
            if (cusMb != null && cusMb.getCustomerType().equals("customerMember")) {
                try {
                    boolean saved = new CartDAO().saveCart(cart, cusMb.getCustomerMemberId());
                    if (saved) {
                        res = cusMb.getCustomerMemberUsername();
                    } else {
                        res = "unsaved";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
