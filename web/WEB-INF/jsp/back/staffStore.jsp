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
            <fieldset>
                <legend>Thêm khuyến mãi</legend>
                <form action="" method="post" id="addDeals">
                    <h2 class="hide">Thêm không thành công! Vui lòng thử lại!</h2>
                    <input type="text" name="code" required="" value=""
                           placeholder="Mã khuyến mãi ..." title="">
                    <input type="text" name="description" required="" value=""
                           placeholder="Mô tả khuyến mãi ..." title="">
                    <input type="text" name="discount" required="" value=""
                           placeholder="Giảm giá ..." title="">
                    <input type="text" name="startDate" required="" value=""
                           placeholder="Ngày áp dụng ..." title="">
                    <input type="text" name="endDate" required="" value=""
                           placeholder="Ngày hết hạn ..." title="">
                    <input type="submit" value="Thêm">
                </form>
            </fieldset>
            <c:if test="${!empty allDeals}">
                <fieldset>
                    <legend>Tất cả khuyến mãi</legend>
                    <table class="outtables">
                        <tr>
                            <th>STT</th>
                            <th>Mã</th>
                            <th>Mô tả</th>
                            <th>Giảm giá</th>
                            <th>Ngày áp dụng</th>
                            <th>Ngày hết hạn</th>
                            <th>Trạng thái</th>
                            <th>Tuỳ chọn</th>
                        </tr>
                        <pg:paging pageSize="10">
                            <c:forEach items="${allDeals}" var="allDeals">
                                <pg:item>
                                    <tr>
                                        <td>${allDeals.idDeals}</td>
                                        <td>${allDeals.code}</td>
                                        <td>${allDeals.description}</td>
                                        <td>${allDeals.discount}%</td>
                                        <td>${allDeals.startDate}</td>
                                        <td>${allDeals.endDate}</td>
                                        <td>
                                            <c:if test="${allDeals.status eq 1}">
                                                Áp dụng
                                            </c:if>
                                            <c:if test="${allDeals.status eq 0}">
                                                Không áp dụng
                                            </c:if>
                                        </td>
                                        <td>
                                            <button class="deleteDeals" data-bind="${allDeals.idDeals}">Xoá</button>
                                            <a href="<%=request.getContextPath()%>/staffstore/deals/${allDeals.idDeals}-${allDeals.code}.html">Chi tiết</a>
                                        </td>
                                    </tr>
                                </pg:item>
                            </c:forEach>
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
                </fieldset>
            </c:if>
        </section>
        <script type="text/javascript" src="${jquery}"></script>
        <script type="text/javascript" src="${adscript}"></script>
        <script type="text/javascript" src="${html5}"></script>
        <script type="text/javascript" src="${bootstrapjs}"></script>
    </body>
</html>