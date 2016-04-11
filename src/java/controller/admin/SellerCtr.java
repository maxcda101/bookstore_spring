/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dao.order.OrderDAO;
import entity.order.Order;
import entity.person.employee.Employee;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
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
@RequestMapping(value = "seller/")
public class SellerCtr {

    @RequestMapping(value = "updateOrder", method = RequestMethod.POST)
    public @ResponseBody
    String updateOrder(
            @RequestParam("orderData") String orderData,
            @RequestParam("crUrl") String crUrl
    ) {
        String res = "cancelUpdateOrder";
        try {
            String[] s = orderData.split("#");
            int idOrder = Integer.parseInt(s[0]);
            OrderWithState ows = new OrderWithState();
            Order order = new Order();
            order.setId(idOrder);
            ows.setOrder(order);
            boolean isupdated = false;
            switch (s[1]) {
                case "Đã nhận":
                    ReceivedState receivedState = new ReceivedState();
                    isupdated = receivedState.updateState(ows);
                    break;
                case "Đang xử lý":
                    ProcessingState processedState = new ProcessingState();
                    isupdated = processedState.updateState(ows);
                    break;
                case "Đang giao hàng":
                    ShippingState shippedState = new ShippingState();
                    isupdated = shippedState.updateState(ows);
                    break;
                case "Đã huỷ":
                    CancelState cancelState = new CancelState();
                    isupdated = cancelState.updateState(ows);
                    break;
                default:
                    break;
            }
            if (isupdated) {
                res = crUrl;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping(value = "allorder.html")
    public ModelAndView seller(ModelAndView model,
            HttpSession session, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        Employee ee = (Employee) session.getAttribute("emLogged");
        if (ee == null || !ee.geteType().equals("seller")) {
            response.sendRedirect(request.getContextPath() + "/home.html");
        } else {
            model = new ModelAndView("/back/seller");
            model.addObject("title", "Seller");
            try {
                ArrayList<Order> allOrder = new OrderDAO().getAllOrder();
                model.addObject("allOrder", allOrder);
                int pg = allOrder.size() / 3;
                if ((pg * 3) < allOrder.size()) {
                    pg += 1;
                }
                if (pg > 1) {
                    model.addObject("pageCount", pg);
                }
                session.setAttribute("crAllOrder", allOrder);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return model;
        }
        return null;
    }

    @RequestMapping(value = "order.html")
    public ModelAndView filter(HttpSession session, ModelAndView model,
            @RequestParam("date") String date,
            HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        ArrayList<Order> allOrder = (ArrayList<Order>) session.getAttribute("crAllOrder");
        if (!date.matches("[2-9]([0-9]){3,}-[0-1][0-9]-[0-3][0-9]")
                || allOrder == null || allOrder.size() <= 0) {
            response.sendRedirect(request.getContextPath() + "/seller/allorder.html");
        } else {
            model = new ModelAndView("/back/orderByDate");
            int allOrderSize = allOrder.size();
            ArrayList<Order> orderByDate = new ArrayList<>();
            for (int i = 0; i < allOrderSize; i++) {
                if (allOrder.get(i).getDate().toString().equals(date)) {
                    orderByDate.add(allOrder.get(i));
                }
            }
            if (orderByDate == null || orderByDate.size() <= 0) {
                model.addObject("emptyOrderByDate", true);
            } else {
                model.addObject("orderByDate", orderByDate);
                model.addObject("date", date);
            }
            return model;
        }
        return null;
    }

    class OrderWithState {

        private State currentState;
        private Order order;

        public OrderWithState() {
            this.currentState = new SentState();
            this.order = null;
        }

        public State getCurrentState() {
            return currentState;
        }

        public Order getOrder() {
            return order;
        }

        public void setCurrentState(State currentState) {
            this.currentState = currentState;
        }

        public void setOrder(Order order) {
            this.order = order;
        }
    }

    interface State {

        public boolean updateState(OrderWithState orderWithState);
    }

    class SentState implements State {

        @Override
        public boolean updateState(OrderWithState orderWithState) {
            orderWithState.setCurrentState(this);
            return new OrderDAO().updateState(orderWithState.getOrder().getId(), "Đã gửi");
        }

    }

    class ReceivedState implements State {

        @Override
        public boolean updateState(OrderWithState orderWithState) {
            orderWithState.setCurrentState(this);
            return new OrderDAO().updateState(orderWithState.getOrder().getId(), "Đã nhận");
        }

    }

    class ProcessingState implements State {

        @Override
        public boolean updateState(OrderWithState orderWithState) {
            orderWithState.setCurrentState(this);
            return new OrderDAO().updateState(orderWithState.getOrder().getId(), "Đang xử lý");
        }

    }

    class ShippingState implements State {

        @Override
        public boolean updateState(OrderWithState orderWithState) {
            orderWithState.setCurrentState(this);
            return new OrderDAO().updateState(orderWithState.getOrder().getId(), "Đang giao hàng");
        }

    }

    class CancelState implements State {

        @Override
        public boolean updateState(OrderWithState orderWithState) {
            orderWithState.setCurrentState(this);
            return new OrderDAO().updateState(orderWithState.getOrder().getId(), "Đã huỷ");
        }

    }
}
