
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
            <c:if test="${!empty ctNotExist && ctNotExist == true}">
                <h1><i>&Phi; <spring:message code="category.notexist"/></i></h1>
            </c:if>
            <c:if test="${empty ctNotExist or ctNotExist != true}">
                <h1>&clubs; <spring:message code="book.category"/>: <i>"${crCategory}"</i></h1>
                <section class="searchResult clearfix">
                    <c:if test="${!empty emptyBookByCategory}">
                        <h3><spring:message code="category.emptyresult"/></h3>
                    </c:if>
                    <c:if test="${empty emptyBookByCategory}">
                        <pg:paging pageSize="12">
                            <section class="rowsb">
                                <% int i = 0;%>
                                <c:forEach items="${listBookByCategory}" var="rowsBbCt">
                                    <% if (i == 4) {
                                            out.print("</section><section class='rowsb'>");
                                            i = 0;
                                        }%>
                                    <pg:item>
                                        <section class="book">
                                            <a class="coverimg" href="<%=request.getContextPath()%>/book/${rowsBbCt.idBook}-${rowsBbCt.sortLink}.html">
                                                <img src="${image}/${rowsBbCt.image}" alt="${rowsBbCt.title}">
                                            </a>
                                            <a class="bgasl" href="<%=request.getContextPath()%>/book/${rowsBbCt.idBook}-${rowsBbCt.sortLink}.html">
                                                <section>
                                                    <h2>${rowsBbCt.title}</h2>
                                                    <h3><i><spring:message code="book.author"/></i><br>${rowsBbCt.author}</h3>
                                                </section>
                                            </a>
                                            <c:if test="${rowsBbCt.originalPrice != -1}">
                                                <h4><i><spring:message code="book.originalprice"/>:</i> <strike><font color="green">${rowsBbCt.originalPrice} VNĐ</font></strike></h4>
                                                    </c:if>
                                                    <c:if test="${rowsBbCt.originalPrice eq -1}">
                                                <h4><i><spring:message code="book.originalprice"/>:</i> <i><font color="green"><spring:message code="book.isupdating"/></font></i></h4>
                                                    </c:if>
                                                    <c:if test="${rowsBbCt.salePrice != -1}">
                                                <h3><i><spring:message code="book.saleprice"/>:</i> <font color="red">${rowsBbCt.salePrice} VNĐ</font></h3>
                                                </c:if>
                                                <c:if test="${rowsBbCt.salePrice eq -1}">
                                                <h3><i><spring:message code="book.status"/>:</i> <i><spring:message code="book.outofstock"/></i></h3>
                                            </c:if>
                                            <h5>
                                                <i><spring:message code="book.category"/>:</i>
                                                <a>
                                                    &nbsp;${rowsBbCt.category.name}
                                                </a>
                                            </h5>
                                            <h5>
                                                <i><spring:message code="book.bookset"/>:</i>
                                                <a>
                                                    &nbsp;${rowsBbCt.set.name}
                                                </a>
                                            </h5>
                                            <section class="option">
                                                <c:if test="${rowsBbCt.salePrice != -1}">
                                                    <c:if test="${!empty listBookInCart}">
                                                        <c:set var="inCart" value ="false"/>
                                                        <c:forEach items="${listBookInCart}" var="rows1">
                                                            <c:if test="${rows1.book.idBook eq rowsBbCt.idBook}">
                                                                <c:set var="inCart" value ="true"/>
                                                            </c:if>
                                                        </c:forEach>
                                                        <c:if test="${inCart eq true}">
                                                            <img src="${icon}/incart.png" alt="<spring:message code="book.isincart"/>">
                                                        </c:if>
                                                        <c:if test="${inCart eq false}">
                                                            <button class="addToCart" data-bind="${rowsBbCt.idBook}"><spring:message code="book.addtocart"/></button>
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${empty listBookInCart}">
                                                        <button class="addToCart" data-bind="${rowsBbCt.idBook}"><spring:message code="book.addtocart"/></button>
                                                    </c:if>
                                                    <img class="hide" src="${icon}/incart.png" alt="<spring:message code="book.isincart"/>">
                                                    <button class="odb" data-bind="${rowsBbCt.idBook}#${crMbUserName}"><spring:message code="book.order"/></button>
                                                </c:if>
                                                <section class="fb-like" data-href="${rowsBbCt.idBook}" data-layout="button" data-action="like" data-show-faces="false" data-share="false"></section>
                                            </section>
                                        </section>
                                    </pg:item>
                                    <%i++;%>
                                </c:forEach>
                                <% i = 0;%>
                            </section>
                        </pg:paging>
                    </c:if>
                </section>
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
        </section>
        <%@include file="inc/rightAside.jsp" %>
    </section>
    <%@include file="inc/seenBook.jsp" %>
<!--</section>-->
<%@include file="../template/footer.jsp" %>