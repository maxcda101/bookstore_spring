<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="tool.MyTool"%>
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
            <a href="<%=request.getContextPath()%>/home.html">Trang chủ</a>
            <button id="lobtn">Đăng xuất</button>
            <a href="<%=request.getContextPath()%>/staffstore/alldeals.html">Tất cả khuyến mãi</a>
            <c:if test="${!empty crDeals}">
                <fieldset>
                    <legend>Thông tin khuyến mãi</legend>
                    <form action="" method="post" id="dtDeals">
                        <h2 class="hide">Sửa không thành công! Vui lòng thử lại!</h2>
                        <input type="text" name="code" required="" value="${crDeals.code}"
                               placeholder="Mã khuyến mãi ..." title="" >
                        <input type="text" name="description" required="" value="${crDeals.description}"
                               placeholder="Mô tả khuyến mãi ..." title="" >
                        <input type="text" name="discount" required="" value="${crDeals.discount}"
                               placeholder="Giảm giá ..." title="" >
                        <input type="text" name="startDate" required="" value="${crDeals.startDate}"
                               placeholder="Ngày áp dụng ..." title="" >
                        <input type="text" name="endDate" required="" value="${crDeals.endDate}"
                               placeholder="Ngày hết hạn ..." title="" >
                        <select name="status">
                            <c:if test="${crDeals.status eq 1}">
                                <option selected="" value="1">Áp dụng</option>
                                <option value="0">Không áp dụng</option>
                            </c:if>
                            <c:if test="${crDeals.status eq 0}">
                                <option selected="" value="0">Không áp dụng</option>
                                <option value="1">Áp dụng</option>
                            </c:if>
                        </select>
                        <input type="hidden" value="${crDeals.idDeals}" name="idD">
                        <input type="submit" value="OK" >
                    </form>
                    <button id="editDeals">Sửa</button>
                    <button id="cancelEdit" class="hide">Huỷ</button>
                    <a href="<%=request.getContextPath()%>/staffstore/deals/${crDeals.idDeals}-${crDeals.code}/applydeals.html">
                        Thêm sách áp dụng khuyến mãi
                    </a>
                </fieldset>
            </c:if>
            <fieldset>
                <legend>Sách áp dụng khuyến mãi</legend>
                <c:if test="${empty allBookByDeals}">
                    Trống
                </c:if>
                <c:if test="${!empty allBookByDeals}">
                    <table class="outtables">
                        <tr>
                            <th>STT</th>
                            <th>Tên sách</th>
                            <th>Ảnh bìa</th>
                            <th>Tuỳ chọn</th>
                        </tr>
                        <pg:paging pageSize="5">
                            <%int i = 0;%>
                            <c:forEach items="${allBookByDeals}" var="allBookByDeals">
                                <pg:item>
                                    <tr>
                                        <td><%=i++%></td>
                                        <td>${allBookByDeals.title}</td>
                                        <td><img src="${image}/${allBookByDeals.image}" title="${allBookByDeals.title}"></td>
                                        <td>
                                            <button class="cancelDeals" data-bind="${allBookByDeals.idBook}#${crDeals.idDeals}#${crDeals.code}">Huỷ</button>
                                        </td>
                                    </tr>
                                </pg:item>
                            </c:forEach>
                            <%i = 0;%>
                        </pg:paging>
                    </table>
                    <c:if test="${!empty pageCount}">
                        <section class="pageNum clearfix">
                            <c:forEach var="i" begin="1" end="${pageCount}">
                                <a href="<%=request.getContextPath()%>${requestScope['javax.servlet.forward.servlet_path']}?pageNum=${i}">
                                    ${i}
                                </a>
                            </c:forEach>
                        </section>
                    </c:if>
                </c:if>
            </fieldset>
        </section>
        <script type="text/javascript" src="${jquery}"></script>
        <script type="text/javascript" src="${adscript}"></script>
        <script type="text/javascript" src="${html5}"></script>
        <script type="text/javascript" src="${bootstrapjs}"></script>
    </body>
</html>