<%-- 
    Document   : navigation
    Created on : Mar 15, 2016, 10:12:35 AM
    Author     : zOzDarKzOz
--%>

<%@page import="java.lang.reflect.Type"%>
<%@page import="com.google.gson.reflect.TypeToken"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="tool.MyTool"%>
<%@page import="entity.book.BookSet"%>
<%@page import="entity.person.customer.Customer"%>
<%@page import="entity.book.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<spring:url value="/resources/image/icon" var="icon" />
<nav class="insidefix clearfix">
    <ul>
        <li>
            <a class="fc" href="<%=request.getContextPath()%>/home.html"><spring:message code="nav.home"/></a>
        </li>
        <li id="category">
            <a class="fc"><p><spring:message code="nav.category"/></p><span>&Delta;</span></a>
                    <c:if test="${!empty scCategory}">
                <ul class="category">
                    <% int rowsScIndex1 = 0;%>
                    <c:forEach items="${scCategory}" var="rowsScCt">
                        <li>
                            <a href="<%=request.getContextPath()%>/category/<%=rowsScIndex1%>-${rowsScCt.sortLink}.html">
                                &raquo; ${rowsScCt.name}
                            </a>
                        </li>
                        <% rowsScIndex1++;%>
                    </c:forEach>
                    <% rowsScIndex1 = 0;%>
                </ul>
            </c:if>
        </li>
        <li>
            <a class="fc" href="<%=request.getContextPath()%>/contactus.html"><spring:message code="nav.contact"/></a>
        </li>
        <li>
            <a class="fc" href="<%=request.getContextPath()%>/aboutus.html"><spring:message code="nav.storeinfor"/></a>
        </li>
    </ul>
    <section id="bgUsOp">
        <%
            Customer cus = (Customer) session.getAttribute("ssLogged");
            if (cus != null && cus.getCustomerType().equals("customerMember")) {
        %>
        <section class="userOption">
            <button id="cruser" data-bind="<%=cus.getCustomerMemberUsername()%>">&raquo; <%=cus.getCustomerMemberUsername()%></button>
            <button id="cartsv" data-bind="<%=cus.getCustomerMemberUsername()%>">&raquo; <spring:message code="user.cartsaved"/></button>
            <button id="ordersv" data-bind="<%=cus.getCustomerMemberUsername()%>">&raquo; <spring:message code="user.ordered"/></button>
            <button id="logout">&raquo; <spring:message code="user.logout"/></button>
        </section>
        <section class="userBtn">
            <img src="${icon}/user.png" alt="User" style="background: greenyellow;">
        </section>
        <%
        } else if (session.getAttribute("emLogged") != null) {
        %>
        <section class="userOption">
            <button id="rdadmin">&raquo; <spring:message code="user.manager"/></button>
            <button id="lobtn">&raquo; Đăng xuất</button>
        </section>
        <section class="userBtn">
            <img src="${icon}/user.png" alt="User" style="background: greenyellow;">
        </section>
        <%
        } else {
        %>
        <section class="userOption">
            <button id="signup">&raquo; <spring:message code="user.signup"/></button>
            <button id="login">&raquo; <spring:message code="user.login"/></button>
            <button id="loginviafacebook">&raquo; <spring:message code="user.loginviafacebook"/></button>
        </section>
        <section class="userBtn">
            <img src="${icon}/user.png" alt="User">
        </section>
        <%
            }
        %>
    </section>
    <span>&nbsp;</span>
    <section id="bgSearch">
        <section class="searchBtn">
            <img src="${icon}/search2.png" alt="searchBtn">
        </section>
        <form method="get" action="<%=request.getContextPath()%>/search.html" name="searchFrm" id="searchFrm">
            <select name="op" id="searchOption">
                <option class="op" value="n1" selected="">&raquo; <spring:message code="search.searchbyname"/></option>
                <option class="op" disabled=""><spring:message code="search.searchbycategory"/></option>
                <c:if test="${!empty scCategory}">
                    <% int rowsScIndex2 = 0;%>
                    <c:forEach items="${scCategory}" var="rowsScCt">
                        <option class="op" value="ct<%=rowsScIndex2%>">&raquo; ${rowsScCt.name}</option>
                        <% rowsScIndex2++;%>
                    </c:forEach>
                    <% rowsScIndex2 = 0;%>
                </c:if>
                <option class="op" disabled=""><spring:message code="search.searchbybookset"/></option>
                <c:if test="${!empty scBookSet}">
                    <% int rowsScIndex3 = 0;%>
                    <c:forEach items="${scBookSet}" var="rowsScBs">
                        <option class="op" value="bs<%=rowsScIndex3%>">&raquo; ${rowsScBs.name}</option>
                        <% rowsScIndex3++;%>
                    </c:forEach>
                    <% rowsScIndex3 = 0;%>
                </c:if>
            </select>
            <input type="text" name="vl" required="" id="searchVal">
            <input type="submit" value="" class="hide">
        </form>
    </section>
</nav>
