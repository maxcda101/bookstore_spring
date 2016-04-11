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
                    <h4><i>Mã:</i> <a href="<%=request.getContextPath()%>/staffstore/deals/${crDeals.idDeals}-${crDeals.code}.html">${crDeals.code}</a></h4>
                    <h4><i>Mô tả:</i> ${crDeals.description}</h4>
                    <h4><i>Giảm giá:</i> ${crDeals.discount}</h4>
                    <h4><i>Ngày áp dụng:</i> ${crDeals.startDate}</h4>
                    <h4><i>Ngày ngưng áp dụng:</i> ${crDeals.endDate}</h4>
                    <c:if test="${crDeals.status eq 1}">
                        <h4><i>Trạng thái:</i> Áp dụng</h4>
                    </c:if>
                    <c:if test="${crDeals.status eq 0}">
                        <h4><i>Trạng thái:</i> Không áp dụng</h4>
                    </c:if>
                </fieldset>
            </c:if>
            <fieldset>
                <legend>Áp dụng sách khuyến mãi</legend>
                <form method="get" action="" name="adsearchFrm" id="adsearchFrm">
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
                    <input type="submit" value="OK">
                </form>
                <h1>&clubs; <spring:message code="book.bookset"/>:</h1>
                <c:if test="${!empty scBookSet}">
                    <% int rScBsIndex = 0;%>
                    <ul class="allbs">
                        <c:forEach items="${scBookSet}" var="rowsScBs">
                            <li>
                                <a href="<%=request.getContextPath()%>/staffstore/deals/${crDeals.idDeals}-${crDeals.code}/applydeals.html?bs=<%=rScBsIndex%>&name=${rowsScBs.sortLink}">
                                    <h2>&raquo; ${rowsScBs.name}</h2>
                                </a>
                            </li>
                            <% rScBsIndex++;%>
                        </c:forEach>
                        <% rScBsIndex = 0;%>
                    </ul>
                </c:if>
                <h1>&clubs; <spring:message code="book.category"/>:</h1>
                <c:if test="${!empty scCategory}">
                    <ul class="allct">
                        <% int rowsScCtIndex = 0;%>
                        <c:forEach items="${scCategory}" var="rowsScCt">
                            <li>
                                <a href="<%=request.getContextPath()%>/staffstore/deals/${crDeals.idDeals}-${crDeals.code}/applydeals.html?ct=<%=rowsScCtIndex%>&name=${rowsScCt.sortLink}">
                                    <h2>&raquo; ${rowsScCt.name}</h2>
                                </a>
                            </li>
                            <% rowsScCtIndex++;%>
                        </c:forEach>
                        <% rowsScCtIndex = 0;
                        %>
                    </ul>
                </c:if>
                <c:if test="${empty allBookBySearch}">
                    Trống
                </c:if>
                <c:if test="${!empty allBookBySearch}">
                    <table class="outtables">
                        <tr>
                            <th>STT</th>
                            <th>Tên sách</th>
                            <th>Ảnh bìa</th>
                            <th>Tuỳ chọn</th>
                        </tr>
                        <pg:paging pageSize="5">
                            <%int i = 0;%>
                            <c:forEach items="${allBookBySearch}" var="allBookBySearch">
                                <pg:item>
                                    <tr>
                                        <td><%=i++%></td>
                                        <td>${allBookBySearch.title}</td>
                                        <td><img src="${image}/${allBookBySearch.image}" title="${allBookBySearch.title}"></td>
                                        <td>
                                            <button class="applyDeals" data-bind="${allBookBySearch.idBook}#${crDeals.idDeals}#${crDeals.code}">Áp dụng</button>
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
                                <c:if test="${!empty bs && !empty name}">
                                    <a href="<%=request.getContextPath()%>${requestScope['javax.servlet.forward.servlet_path']}?bs=${bs}&name=${name}&pageNum=${i}">
                                        ${i}
                                    </a>
                                </c:if>
                                <c:if test="${!empty ct && !empty name}">
                                    <a href="<%=request.getContextPath()%>${requestScope['javax.servlet.forward.servlet_path']}?ct=${ct}&name=${name}&pageNum=${i}">
                                        ${i}
                                    </a>
                                </c:if>
                                <c:if test="${!empty vl && !empty op}">
                                    <a href="<%=request.getContextPath()%>${requestScope['javax.servlet.forward.servlet_path']}?op=${op}&vl=${vl}&pageNum=${i}">
                                        ${i}
                                    </a>
                                </c:if>
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