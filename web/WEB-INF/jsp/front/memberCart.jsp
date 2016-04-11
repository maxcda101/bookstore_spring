
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/resources/image/thumb" var="image" />
<%@include file="../template/header.jsp" %>
<%@include file="inc/topHeader.jsp" %>
<%@include file="inc/navigation.jsp" %>

<section class="inside clearfix mg">
    <section class="content">
        <fieldset id="fscart">
            <legend><spring:message code="cart.title"/></legend>
            <h2 id="saveerror" class="hide"><spring:message code="cart.saveerror"/></h2>
            <c:if test="${!empty emptyCart && emptyCart == true}">
                <h2><spring:message code="cart.empty"/></h2>
            </c:if>
            <c:if test="${empty emptyCart && !empty crListBookOrder}">
                <form action="" method="post" id="fcart">
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
                            <th><spring:message code="cart.option"/></th>
                        </tr>
                        <% int l = 0;%>
                        <c:forEach items="${crListBookOrder}" var="rowsBookInCart">
                            <tr>
                                <td>
                                    <a href="<%=request.getContextPath()%>/book/${rowsBookInCart.book.idBook}-${rowsBookInCart.book.sortLink}.html">
                                        ${rowsBookInCart.book.title}
                                    </a>
                                </td>
                                <td>
                                    <a href="<%=request.getContextPath()%>/book/${rowsBookInCart.book.idBook}-${rowsBookInCart.book.sortLink}.html">
                                        <img src="${image}/${rowsBookInCart.book.image}" alt="${rowsBookInCart.book.title}">
                                    </a>
                                </td>
                                <td>${rowsBookInCart.book.author}</td>
                                <td>${rowsBookInCart.book.originalPrice}</td>
                                <td>
                                    <input type="text" value="${rowsBookInCart.book.salePrice}" name="salePrice" required="" disabled="">
                                </td>
                                <td class="quantity">
                                    <select name="quantity">
                                        <c:if test="${!empty rowsBookInCart.quantity}">
                                            <option selected="" value="${rowsBookInCart.quantity}>">${rowsBookInCart.quantity}</option>
                                        </c:if>
                                        <% for (int k = 1; k <= 10; k++) {%>
                                        <option value="<%=k%>"><%=k%></option>
                                        <%}%>
                                    </select>
                                    <input type="hidden" value="<%=l%>">
                                </td>
                                <td>${rowsBookInCart.book.category.name}</td>
                                <td>${rowsBookInCart.book.set.name}</td>
                                <td>
                                    <input type="text" value="${rowsBookInCart.totalPrice}" name="totalPrice[]" required="" disabled="">
                                </td>
                                <td>
                                    <a href="<%=request.getContextPath()%>/cart/delete/<%=l%>">
                                        <img class="deleteicon" src="${icon}/icon_trash.png" alt="<spring:message code="cart.delete"/>">
                                    </a>
                                </td>
                            </tr>
                            <% l++;%>
                        </c:forEach>
                        <% l = 0;%>
                        <tr>
                            <td class="ttPr" colspan="10">
                                <p>VNĐ</p>
                                <input id="quantity" type="text" value="${crTotalPrice}" name="quantity" required="" disabled="">
                                <p><spring:message code="cart.totalprice"/>:</p>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="10">
                                <input class="hide" type="submit" value="<spring:message code="cart.update"/>" name="updateCart" id="updateCart" disabled="">
                                <input type="submit" value="Lưu" name="saveCart" id="saveCart">
                                <a class="otherop" href="<%=request.getContextPath()%>/order.html"><spring:message code="cart.order"/></a>
                                <a class="otherop" href="<%=request.getContextPath()%>/home.html"><spring:message code="cart.continue"/></a>
                            </td>
                        </tr>
                    </table>
                </form>
            </c:if>
        </fieldset>
    </section>
    <%@include file="inc/rightAside.jsp" %>
</section>
<%@include file="inc/seenBook.jsp" %>

<%@include file="../template/footer.jsp" %>