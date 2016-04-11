
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
            <legend><spring:message code="order.saved"/></legend>
            <h2><c:if test="${empty crListOrder}"><spring:message code="order.saveempty"/></c:if></h2>
            <c:if test="${!empty crListOrder}">
                <c:forEach items="${crListOrder}" var="listOrder">
                    <table class="outtable">
                        <tr>
                            <td><spring:message code="order.date"/></td>
                            <td>${listOrder.date}</td>
                        </tr>
                        <tr>
                            <td><spring:message code="order.order"/></td>
                            <td>
                                <table>
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
                                    <c:forEach items="${listOrder.payment.cart.listBook}" var="BookOd">
                                        <tr>
                                            <td>
                                                <a href="<%=request.getContextPath()%>/book/${BookOd.book.idBook}-${BookOd.book.sortLink}.html">
                                                    ${BookOd.book.title}
                                                </a>
                                            </td>
                                            <td>
                                                <a href="<%=request.getContextPath()%>/book/${BookOd.book.idBook}-${BookOd.book.sortLink}.html">
                                                    <img src="${image}/${BookOd.book.image}" alt="${BookOd.book.title}">
                                                </a>
                                            </td>
                                            <td>${BookOd.book.author}</td>
                                            <td>${BookOd.book.originalPrice}</td>
                                            <td>${BookOd.book.salePrice}</td>
                                            <td>${BookOd.quantity}</td>
                                            <td>${BookOd.book.category.name}</td>
                                            <td>${BookOd.book.set.name}</td>
                                            <td>${BookOd.totalPrice}</td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td colspan="9">
                                            <p><spring:message code="cart.totalprice"/>: ${listOrder.payment.cart.totalPrice} VNĐ</p>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td><spring:message code="order.bank"/></td>
                            <td>${listOrder.payment.bank.idBank}</td>
                        </tr>
                        <tr>
                            <td><spring:message code="order.addressshipping"/></td>
                            <td>${listOrder.spif.num} - ${listOrder.spif.lane} - ${listOrder.spif.street} - ${listOrder.spif.ward} - ${listOrder.spif.district} - ${listOrder.spif.city} - ${listOrder.spif.country}</td>
                        </tr>
                        <tr>
                            <td><spring:message code="order.state"/></td>
                            <td>
                                <c:if test="${listOrder.state eq 'Đã gửi'}">
                                    <spring:message code="order.send"/>
                                </c:if>
                                <c:if test="${listOrder.state eq 'Đã nhận'}">
                                    <spring:message code="order.received"/>
                                </c:if>
                                <c:if test="${listOrder.state eq 'Đang xử lý'}">
                                    <spring:message code="order.processing"/>
                                </c:if>
                                <c:if test="${listOrder.state eq 'Đang giao hàng'}">
                                    <spring:message code="order.shipping"/>
                                </c:if>
                                <c:if test="${listOrder.state eq 'Đã huỷ'}">
                                    <spring:message code="order.cancel"/>
                                </c:if>
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