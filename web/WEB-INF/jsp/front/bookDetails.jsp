<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/resources/image/thumb" var="image" />
<spring:url value="/resources/image/icon" var="icon" />
<!--<section class="clearfix wrapper">-->
    <%@include file="../template/header.jsp" %>
    <%@include file="inc/topHeader.jsp" %>
    <%@include file="inc/navigation.jsp" %>

    <section class="inside clearfix mg">
        <section class="content">
            <c:if test="${!empty bookNotExist}">
                <h2><spring:message code="book.notexist"/></h2>
            </c:if>
            <c:if test="${empty bookNotExist}">
                <c:if test="${!empty bookNotFound}">
                    <h2><spring:message code="book.notfound"/></h2>
                </c:if>
                <c:if test="${empty bookNotExist && empty bookNotFound && !empty crBook}">
                    <section class="bookdetails">
                        <a class="cvim" href="<%=request.getContextPath()%>/book/${crBook.idBook}-${crBook.sortLink}.html">
                            <img src="${image}/${crBook.image}" alt="${crBook.title}">
                            <section class="fb-like" data-href="${crBook.idBook}" data-layout="button" data-action="like" data-show-faces="false" data-share="true"></section>
                        </a>
                        <section class="dtls">
                            <h1>
                                <a href="<%=request.getContextPath()%>/book/${crBook.idBook}-${crBook.sortLink}.html">
                                    ${crBook.title}
                                </a>
                            </h1>
                            <h3><i><spring:message code="book.author"/>:</i>&nbsp; ${crBook.author}</h3>
                            <c:if test="${crBook.originalPrice != -1}">
                                <h4><i><spring:message code="book.originalprice"/>:</i>&nbsp; <strike><font color="green">${crBook.originalPrice} VNĐ</font></strike></h4>
                                    </c:if>
                                    <c:if test="${crBook.originalPrice eq -1}">
                                <h4><i><spring:message code="book.originalprice"/>:</i>&nbsp; <i><font color="green"><spring:message code="book.isupdating"/></font></i></h4>
                                    </c:if>
                                    <c:if test="${crBook.salePrice != -1}">
                                <h3><i><spring:message code="book.saleprice"/>:</i>&nbsp; <font color="red">${crBook.salePrice} VNĐ</font></h3>
                                </c:if>
                                <c:if test="${crBook.salePrice eq -1}">
                                <h3><i><spring:message code="book.status"/>:</i>&nbsp; <i><spring:message code="book.outofstock"/></i></h3>
                            </c:if>
                            <c:if test="${crBook.quantity > 0}">
                                <h3><i><spring:message code="book.quantity"/>:</i>&nbsp; <font color="brown">${crBook.quantity}</font></h3>
                                </c:if>
                                <c:if test="${crBook.quantity <= 0}">
                                <h3><i><spring:message code="book.quantity"/>:</i>&nbsp; <i><spring:message code="book.outofstock"/></i></h3>
                            </c:if>
                            <h3>
                                <i><spring:message code="book.category"/>:</i>&nbsp;
                                <a>
                                    &nbsp;${crBook.category.name}
                                </a>
                            </h3>
                            <h3>
                                <i><spring:message code="book.bookset"/>:</i>&nbsp;
                                <a>
                                    &nbsp;${crBook.set.name}
                                </a>
                            </h3>
                        </c:if>
                    </section>
                    <section class="odop">
                        <h4><spring:message code="book.booknow"/></h4>
                        <c:if test="${crBook.salePrice != -1}">
                            <c:if test="${!empty isIncart && isIncart == true}">
                                <img src="${icon}/incart.png" alt="<spring:message code="book.isincart"/>">
                            </c:if>
                            <c:if test="${empty isIncart}">
                                <button class="addToCart" data-bind="${crBook.idBook}"><spring:message code="book.addtocart"/></button>
                            </c:if>
                        </c:if>
                        <img class="hide" src="${icon}/incart.png" alt="<spring:message code="book.isincart"/>">
                        <button class="odb" data-bind="${crBook.idBook}#${crMbUserName}"><spring:message code="book.order"/></button>
                    </section>
                    <article class="artcl">
                        <h2><spring:message code="book.description"/></h2>
                        <p><i>&nbsp;</i>${crBook.description}</p>
                    </article>
                </section>
                <h2 class="bivtt"><spring:message code="book.involve"/></h2>
                <section class="bookinvolve">
                    <c:if test="${!empty listInvolveBook}">
                        <c:forEach items="${listInvolveBook}" var="rLIB">
                            <section class="eachbiv">
                                <a href="<%=request.getContextPath()%>/book/${rLIB.idBook}-${rLIB.sortLink}.html">
                                    <img src="${image}/${rLIB.image}" title="${rLIB.title}">
                                    <section>
                                        <h5>${rLIB.title}</h5>
                                    </section>
                                </a>
                            </section>
                        </c:forEach>
                    </c:if>
                </section>
            </c:if>
        </section>
        <%@include file="inc/rightAside.jsp" %>
    </section>
    <%@include file="inc/seenBook.jsp" %>

    <%@include file="../template/footer.jsp" %>
<!--</section>-->