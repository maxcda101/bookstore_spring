
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pg" uri="/WEB-INF/taglib139.tld" %>
<spring:url value="/resources/image/thumb" var="image" />
<spring:url value="/resources/image/icon" var="icon" />
<!--<section class="clearfix wrapper">-->
    <%@include file="../template/header.jsp" %>
    <%@include file="inc/topHeader.jsp" %>
    <%@include file="inc/navigation.jsp" %>

    <section class="inside clearfix mg">
        <section class="content">
            <c:if test="${!empty noResult}">
                <h1><i>&Phi; <spring:message code="search.noresult"/></i></h1>
            </c:if>
            <c:if test="${empty noResult}">
                <h1>&clubs; <spring:message code="search.searchresultfor"/>: <i>"${vl}"</i></h1>
                <section class="searchResult clearfix">
                    <c:if test="${!empty emptyResult}">
                        <spring:message code="search.emptyresult"/>
                    </c:if>
                    <c:if test="${empty emptyResult}">
                        <pg:paging pageSize="12">
                            <section class="rowsb">
                                <% int k = 0;%>
                                <c:forEach items="${listBook}" var="rowsBookBySearch">
                                    <% if (k == 4) {
                                            out.print("</section><section class='rowsb'>");
                                            k = 0;
                                        }%>
                                    <pg:item>
                                        <section class="book">
                                            <a class="coverimg" href="<%=request.getContextPath()%>/book/${rowsBookBySearch.idBook}-${rowsBookBySearch.sortLink}.html">
                                                <img src="${image}/${rowsBookBySearch.image}" alt="${rowsBookBySearch.title}">
                                            </a>
                                            <a class="bgasl" href="<%=request.getContextPath()%>/book/${rowsBookBySearch.idBook}-${rowsBookBySearch.sortLink}.html">
                                                <section>
                                                    <h2>${rowsBookBySearch.title}</h2>
                                                    <h3><i><spring:message code="book.author"/></i><br>${rowsBookBySearch.author}</h3>
                                                </section>
                                            </a>

                                            <c:if test="${rowsBookBySearch.originalPrice != -1}">
                                                <h4><i><spring:message code="book.originalprice"/>:</i> <strike><font color="green">${rowsBookBySearch.originalPrice} VNĐ</font></strike></h4>
                                                    </c:if>
                                                    <c:if test="${rowsBookBySearch.originalPrice eq -1}">
                                                <h4><i><spring:message code="book.originalprice"/>:</i> <i><font color="green"><spring:message code="book.isupdating"/></font></i></h4>
                                                    </c:if>
                                                    <c:if test="${rowsBookBySearch.salePrice != -1}">
                                                <h3><i><spring:message code="book.saleprice"/>:</i> <font color="red">${rowsBookBySearch.salePrice} VNĐ</font></h3>
                                                </c:if>
                                                <c:if test="${rowsBookBySearch.salePrice eq -1}">
                                                <h3><i><spring:message code="book.status"/>:</i> <i><spring:message code="book.outofstock"/></i></h3>
                                            </c:if>
                                            <h5>
                                                <i><spring:message code="book.category"/>:</i>
                                                <a href="<%=request.getContextPath()%>/category/${rowsBookBySearch.category.idCategory}-${rowsBookBySearch.category.sortLink}.html">
                                                    &nbsp;${rowsBookBySearch.category.name}
                                                </a>
                                            </h5>
                                            <h5>
                                                <i><spring:message code="book.bookset"/>:</i>
                                                <a href="<%=request.getContextPath()%>/bookset/${rowsBookBySearch.set.idBookSet}-${rowsBookBySearch.set.sortLink}.html">
                                                    &nbsp;${rowsBookBySearch.set.name}
                                                </a>
                                            </h5>
                                            <section class="option">
                                                <c:if test="${rowsBookBySearch.salePrice != -1}">
                                                    <c:if test="${!empty listBookInCart}">
                                                        <c:set var="inCart" value ="false"/>
                                                        <c:forEach items="${listBookInCart}" var="rows1">
                                                            <c:if test="${rows1.book.idBook eq rowsBookBySearch.idBook}">
                                                                <c:set var="inCart" value ="true"/>
                                                            </c:if>
                                                        </c:forEach>
                                                        <c:if test="${inCart eq true}">
                                                            <img src="${icon}/incart.png" alt="<spring:message code="book.isincart"/>">
                                                        </c:if>
                                                        <c:if test="${inCart eq false}">
                                                            <button class="addToCart" data-bind="${rowsBookBySearch.idBook}"><spring:message code="book.addtocart"/></button>
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${empty listBookInCart}">
                                                        <button class="addToCart" data-bind="${rowsBookBySearch.idBook}"><spring:message code="book.addtocart"/></button>
                                                    </c:if>
                                                    <img class="hide" src="${icon}/incart.png" alt="<spring:message code="book.isincart"/>">
                                                    <button class="odb" data-bind="${rowsBookBySearch.idBook}#${crMbUserName}"><spring:message code="book.order"/></button>
                                                </c:if>
                                                <section class="fb-like" data-href="${rowsBookBySearch.idBook}" data-layout="button" data-action="like" data-show-faces="false" data-share="false"></section>
                                            </section>
                                        </section>
                                    </pg:item>
                                    <%k++;%>
                                </c:forEach>
                                <% k = 0;%>
                            </section>
                        </pg:paging>
                    </c:if>
                </section>
            </c:if>
            <c:if test="${!empty pageCount}">
                <section class="pageNum clearfix">
                    <c:forEach var="i" begin="1" end="${pageCount}">
                        <a href="<%=request.getContextPath()%>${requestScope['javax.servlet.forward.servlet_path']}?op=${op}&vl=${vl}&pageNum=${i}">
                            ${i}
                        </a>
                    </c:forEach>
                </section>
            </c:if>
        </section>
        <%@include file="inc/rightAside.jsp" %>
    </section>
    <%@include file="inc/seenBook.jsp" %>
<!--</section>-->
<%@include file="../template/footer.jsp" %>