<%@page import="tool.MyTool"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="entity.person.employee.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="pg" uri="/WEB-INF/taglib139.tld" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <spring:url value="/resources/image/thumb" var="logo" />
        <link rel="Shortcut Icon" href="${logo}/logo.jpg" type="image/x-icon" />
        <title>${title}</title>
        <spring:url value="/resources/image/thumb" var="image" />
        <spring:url value="/resources/css/style.css" var="style" />
        <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapcss" />
        <link href="${style}" rel="stylesheet" type="text/css"/>
        <link href="${bootstrapcss}" rel="stylesheet" type="text/css"/>
        <spring:url value="/resources/js/jquery-2.1.4.min.js" var="jquery" />
        <spring:url value="/resources/js/ad.js" var="adscript" />
        <spring:url value="/resources/js/html5.js" var="html5" />
        <spring:url value="/resources/js/bootstrap.min.js" var="bootstrapjs" />
    </head>
    <body>
        <section class="inside clearfix">
            <fieldset class="mnOrder">
                <legend>Danh sách Order</legend>
                <a href="<%=request.getContextPath()%>/home.html">Trang chủ</a>
                <a href="<%=request.getContextPath()%>/seller/allorder.html">Tất cả đơn hàng</a>
                <button id="lobtn">Đăng xuất</button>
                <h2>
                    <c:if test="${empty orderByDate}"><spring:message code="order.saveempty"/></c:if>
                </h2>
                <form action="<%=request.getContextPath()%>/seller/order.html" method="get" id="filter">
                    <select name="date">
                        <c:if test="${!empty date}">
                            <option value="${date}" selected="">${date}</option>
                        </c:if>
                        <%
                            Calendar c = Calendar.getInstance();
                            Date date;
                            String s;
                            for (int i = 0; i < 7; i++) {
                                date = c.getTime();
                                s = MyTool.getCurrentTimeSQLFormat(date);
                        %>
                        <option value="<%=s%>"><%=s%></option>
                        <%
                                c.setTime(date);
                                c.add(Calendar.DATE, -1);
                            }
                        %>
                    </select>
                    <input type="submit" value="<spring:message code="filter.filter"/>">
                </form>
                <c:if test="${!empty orderByDate}">
                    <pg:paging pageSize="3">
                        <c:forEach items="${orderByDate}" var="orderByDate">
                            <pg:item>
                                <table class="outtables">
                                    <tr>
                                        <td><spring:message code="order.date"/></td>
                                        <td>${orderByDate.date}</td>
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
                                                <c:forEach items="${orderByDate.payment.cart.listBook}" var="allBookOd">
                                                    <tr>
                                                        <td>${allBookOd.book.title}</td>
                                                        <td><img src="${image}/${allBookOd.book.image}" alt="${allBookOd.book.title}"></td>
                                                        <td>${allBookOd.book.author}</td>
                                                        <td>${allBookOd.book.originalPrice}</td>
                                                        <td>${allBookOd.book.salePrice}</td>
                                                        <td>${allBookOd.quantity}</td>
                                                        <td>${allBookOd.book.category.name}</td>
                                                        <td>${allBookOd.book.set.name}</td>
                                                        <td>${allBookOd.totalPrice}</td>
                                                    </tr>
                                                </c:forEach>
                                                <tr>
                                                    <td colspan="9">
                                                        <p><spring:message code="cart.totalprice"/>: ${orderByDate.payment.cart.totalPrice} VNĐ</p>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><spring:message code="order.customer"/></td>
                                        <td>${orderByDate.spif.customer.phoneNum} - ${orderByDate.spif.customer.email}</td>
                                    </tr>
                                    <tr>
                                        <td><spring:message code="order.bank"/></td>
                                        <td>
                                            <c:if test="${orderByDate.payment.bank.idBank eq 0}">
                                                <spring:message code="bank.no"/>
                                            </c:if>
                                            <c:if test="${orderByDate.payment.bank.idBank != 0}">
                                                ${orderByDate.payment.bank.idBank}
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><spring:message code="order.addressshipping"/></td>
                                        <td>${orderByDate.spif.num} - ${orderByDate.spif.lane} - ${orderByDate.spif.street} - ${orderByDate.spif.ward} - ${orderByDate.spif.district} - ${orderByDate.spif.city} - ${orderByDate.spif.country}</td>
                                    </tr>
                                    <tr>
                                        <td><spring:message code="order.state"/></td>
                                        <td class="stateOption">
                                            <select>
                                                <c:if test="${!empty orderByDate.state}">
                                                    <option selected="" value="${orderByDate.state}>">${orderByDate.state}</option>
                                                </c:if>
                                                <c:if test="${orderByDate.state != 'Đã nhận'}">
                                                    <option value="Đã nhận"><spring:message code="order.received"/></option>
                                                </c:if>
                                                <c:if test="${orderByDate.state != 'Đang xử lý'}">
                                                    <option value="Đang xử lý"><spring:message code="order.processing"/></option>
                                                </c:if>
                                                <c:if test="${orderByDate.state != 'Đang giao hàng'}">
                                                    <option value="Đang giao hàng"><spring:message code="order.shipping"/></option>
                                                </c:if>
                                                <c:if test="${orderByDate.state != 'Đã huỷ'}">
                                                    <option value="Đã huỷ"><spring:message code="order.cancel"/></option>
                                                </c:if>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><spring:message code="cart.option"/></td>
                                        <td>
                                            <button class="updateOrder" data-bind="${orderByDate.id}" disabled=""><spring:message code="cart.update"/></button>
                                        </td>
                                    </tr>
                                </table>
                                <hr>
                            </pg:item>
                        </c:forEach>
                    </pg:paging>
                </c:if>
                <c:if test="${!empty pageCount}">
                    <section class="pageNum clearfix">
                        <c:forEach var="i" begin="1" end="${pageCount}">
                            <a href="<%=request.getContextPath()%>${requestScope['javax.servlet.forward.servlet_path']}?date=${date}&pageNum=${i}">
                                ${i}
                            </a>
                        </c:forEach>
                    </section>
                </c:if>
            </fieldset>
        </section>
        <script type="text/javascript" src="${jquery}"></script>
        <script type="text/javascript" src="${adscript}"></script>
        <script type="text/javascript" src="${html5}"></script>
        <script type="text/javascript" src="${bootstrapjs}"></script>
    </body>
</html>