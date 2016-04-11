<%-- 
    Document   : topHeader
    Created on : Mar 15, 2016, 10:07:33 AM
    Author     : zOzDarKzOz
--%>
<%@page import="java.text.DecimalFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/resources/image/thumb" var="thumb" />
<spring:url value="/resources/image/background" var="background" />
<section class="banner clearfix">
    <section class="insidefix topHeader">
        <section class="logo">
            <a href="<%=request.getContextPath()%>/home.html">
                <img src="${thumb}/logo.jpg" alt="Logo">
            </a>
        </section>
        <section class="cart">
            <%
                DecimalFormat formatter = new DecimalFormat("###,###,###");
                String amount = "0", totalPrice = "0";
                double number = 0;
                String cartStr = (String) session.getAttribute("cartStr");
                if (cartStr != null) {
                    String[] s = cartStr.split("#");
                    amount = s[0];
                    totalPrice = s[1];
                    number = Double.parseDouble(totalPrice);
                }
            %>
            <a href="<%=request.getContextPath()%>/cart.html">
                <section class="amount">
                    <img src="${icon}/cart.png" alt="cart">
                    <span id="amount">(<%=amount%>)</span>
                </section>
            </a>
            <section class="totalPrice">
                <p>VNĐ</p>
                <span id="totalPrice">
                    <%=formatter.format(number)%>
                </span>
            </section>
        </section>
    </section>
    <img id="banner" src="${background}/book-pic-banner.jpg" alt="Banner">
</section>
