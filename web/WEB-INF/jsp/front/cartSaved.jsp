
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/resources/image/thumb" var="image" />
<%@include file="../template/header.jsp" %>
<%@include file="inc/topHeader.jsp" %>
<%@include file="inc/navigation.jsp" %>

<section class="inside clearfix mg">
    <section class="content">
        <fieldset id="fscartsv">
            <legend><spring:message code="cart.saved"/></legend>
            <h2 id="deleteerror" class="hide"><spring:message code="cart.deleteerror"/></h2>
            <h2 id="ordererror" class="hide"><spring:message code="order.ordererror"/></h2>
            <h2><c:if test="${empty currentListCart}"><spring:message code="cart.saveempty"/></c:if></h2>
            <c:if test="${!empty currentListCart}">
                <c:forEach items="${currentListCart}" var="listCart">
                    <table class="outtable">
                        <tr>
                            <th><spring:message code="cart.book"/></th>
                            <th><spring:message code="cart.coverimage"/></th>
                            <th><spring:message code="cart.author"/></th>
                            <th><spring:message code="cart.price"/></th>
                            <th><spring:message code="cart.saleprice"/></th>
                            <th><spring:message code="cart.quantity"/></th>
                            <th><spring:message code="cart.category"/></th>
                            <th><spring:message code="cart.bookset"/></th>
                            <th><spring:message code="cart.total"/> (VNĐ)</th>
                        </tr>
                        <% int l = 0;%>
                        <c:forEach items="${listCart.listBook}" var="rowsBInC">
                            <tr>
                                <td>
                                    <a href="<%=request.getContextPath()%>/book/${rowsBInC.book.idBook}-${rowsBInC.book.sortLink}.html">
                                        ${rowsBInC.book.title}
                                    </a>
                                </td>
                                <td>
                                    <a href="<%=request.getContextPath()%>/book/${rowsBInC.book.idBook}-${rowsBInC.book.sortLink}.html">
                                        <img src="${image}/${rowsBInC.book.image}" alt="${rowsBInC.book.title}">
                                    </a>
                                </td>
                                <td>${rowsBInC.book.author}</td>
                                <td>${rowsBInC.book.originalPrice}</td>
                                <td>${rowsBInC.book.salePrice}</td>
                                <td>${rowsBInC.quantity}</td>
                                <td>${rowsBInC.book.category.name}</td>
                                <td>${rowsBInC.book.set.name}</td>
                                <td>${rowsBInC.totalPrice}</td>
                            </tr>
                            <% l++;%>
                        </c:forEach>
                        <% l = 0;
                        %>
                        <tr>
                            <td colspan="9">
                                <p><spring:message code="cart.totalprice"/>: ${listCart.totalPrice} VNĐ</p>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="9">
                                <button class="mbOrder otherop" data-bind="${listCart.idCart}"><spring:message code="cart.order"/></button>
                                <button class="deleteCsv otherop" data-bind="${listCart.idCart}"><spring:message code="cart.delete"/></button>
                            </td>
                        </tr>
                    </table>
                    <hr>
                </c:forEach>
            </c:if>
        </fieldset>
    </section>
    <%@include file="inc/rightAside.jsp" %>
</section>
<%@include file="inc/seenBook.jsp" %>

<%@include file="../template/footer.jsp" %>