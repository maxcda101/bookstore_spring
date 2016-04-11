<%-- 
    Document   : home
    Created on : Mar 14, 2016, 4:22:51 AM
    Author     : zOzDarKzOz
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/resources/image/thumb" var="image" />
<spring:url value="/resources/image/icon" var="icon" />
<%@include file="../template/header.jsp" %>
<%@include file="inc/topHeader.jsp" %>
<%@include file="inc/navigation.jsp" %>

<section class="inside clearfix mg">
    <section class="content">
        <h1 id="title"><spring:message code="book.new"/><br><spring:message code="book.book"/></h1>
        <section class="newbook">
            <c:forEach items="${listFiveBook}" var="rowsLFB">
                <section>
                    <a href="<%=request.getContextPath()%>/book/${rowsLFB.idBook}-${rowsLFB.sortLink}.html">
                        <img src="${image}/${rowsLFB.image}" alt="${rowsLFB.title}">
                    </a>
                    <a href="<%=request.getContextPath()%>/book/${rowsLFB.idBook}-${rowsLFB.sortLink}.html">
                        <h2>${rowsLFB.title}</h2>
                    </a>
                    <section class="dts">
                        <section>
                            <h3 class="author"><i><spring:message code="book.author"/>:</i>&nbsp; ${rowsLFB.author}</h3>
                            <c:if test="${rowsLFB.originalPrice != -1}">
                                <h4><i><spring:message code="book.originalprice"/>:</i>&nbsp; <strike><font color="green">${rowsLFB.originalPrice} VNĐ</font></strike></h4>
                                    </c:if>
                                    <c:if test="${rowsLFB.originalPrice eq -1}">
                                <h4><i><spring:message code="book.originalprice"/>:</i>&nbsp; <i><font color="green"><spring:message code="book.isupdating"/></font></i></h4>
                                    </c:if>
                                    <c:if test="${rowsLFB.salePrice != -1}">
                                <h3><i><spring:message code="book.saleprice"/>:</i>&nbsp; <font color="red">${rowsLFB.salePrice} VNĐ</font></h3>
                                </c:if>
                                <c:if test="${rowsLFB.salePrice eq -1}">
                                <h3><i><spring:message code="book.status"/>:</i>&nbsp; <i><spring:message code="book.outofstock"/></i></h3>
                            </c:if>
                            <h3>
                                <i><spring:message code="book.category"/>:</i>&nbsp;
                                <a>
                                    &nbsp;${rowsLFB.category.name}
                                </a>
                            </h3>
                            <h3>
                                <i><spring:message code="book.bookset"/>:</i>&nbsp;
                                <a>
                                    &nbsp;${rowsLFB.set.name}
                                </a>
                            </h3>
                            <c:if test="${rowsLFB.salePrice != -1}">
                                <c:if test="${!empty listBookInCart}">
                                    <c:set var="inCart" value ="false"/>
                                    <c:forEach items="${listBookInCart}" var="rows1">
                                        <c:if test="${rows1.book.idBook eq rowsLFB.idBook}">
                                            <c:set var="inCart" value ="true"/>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${inCart eq true}">
                                        <img src="${icon}/incart.png" alt="<spring:message code="book.isincart"/>">
                                    </c:if>
                                    <c:if test="${inCart eq false}">
                                        <button class="addToCart" data-bind="${rowsLFB.idBook}"><spring:message code="book.addtocart"/></button>
                                    </c:if>
                                </c:if>
                                <c:if test="${empty listBookInCart}">
                                    <button class="addToCart" data-bind="${rowsLFB.idBook}"><spring:message code="book.addtocart"/></button>
                                </c:if>
                                <img class="hide" src="${icon}/incart.png" alt="<spring:message code="book.isincart"/>">
                                <button class="odb" data-bind="${rowsLFB.idBook}#${crMbUserName}"><spring:message code="book.order"/></button>
                            </c:if>
                            <section class="fb-like" data-href="${rowsLFB.idBook}" data-layout="button" data-action="like" data-show-faces="false" data-share="false"></section>
                        </section>
                    </section>
                    <article class="article">
                        <p>${rowsLFB.description}</p>
                        <a href="<%=request.getContextPath()%>/book/${rowsLFB.idBook}-${rowsLFB.sortLink}.html" class="btn btn-mini">&raquo; <spring:message code="book.readmore"/></a>
                    </article>
                </section>
            </c:forEach>
        </section>

        <section class="bookByWeek">
            <h1>&clubs; <spring:message code="book.bookbyweek"/></h1>
            <c:if test="${!empty listBookByWeek}">
                <section id="bookByWeek">
                    <c:forEach items="${listBookByWeek}" var="rbByWeek">
                        <section class="book">
                            <a class="coverimg2" href="<%=request.getContextPath()%>/book/${rbByWeek.idBook}-${rbByWeek.sortLink}.html">
                                <img src="${image}/${rbByWeek.image}" alt="${rbByWeek.title}">
                            </a>
                            <a class="bgasl" href="<%=request.getContextPath()%>/book/${rbByWeek.idBook}-${rbByWeek.sortLink}.html">
                                <section>
                                    <h2>${rbByWeek.title}</h2>
                                    <h3><i><spring:message code="book.author"/></i><br>${rbByWeek.author}</h3>
                                </section>
                            </a>
                            <c:if test="${rbByWeek.originalPrice != -1}">
                                <h4><i><spring:message code="book.originalprice"/>:</i> <strike><font color="green">${rbByWeek.originalPrice} VNĐ</font></strike></h4>
                                    </c:if>
                                    <c:if test="${rbByWeek.originalPrice eq -1}">
                                <h4><i><spring:message code="book.originalprice"/>:</i> <i><font color="green"><spring:message code="book.isupdating"/></font></i></h4>
                                    </c:if>
                                    <c:if test="${rbByWeek.salePrice != -1}">
                                <h3><i><spring:message code="book.saleprice"/>:</i> <font color="red">${rbByWeek.salePrice} VNĐ</font></h3>
                                </c:if>
                                <c:if test="${rbByWeek.salePrice eq -1}">
                                <h3><i><spring:message code="book.status"/>:</i> <i><spring:message code="book.outofstock"/></i></h3>
                            </c:if>
                            <h5>
                                <i><spring:message code="book.category"/>:</i>
                                <a>
                                    &nbsp;${rbByWeek.category.name}
                                </a>
                            </h5>
                            <h5>
                                <i><spring:message code="book.bookset"/>:</i>
                                <a>
                                    &nbsp;${rbByWeek.set.name}
                                </a>
                            </h5>
                            <section class="option">
                                <c:if test="${rbByWeek.salePrice != -1}">
                                    <c:if test="${!empty listBookInCart}">
                                        <c:set var="inCart" value ="false"/>
                                        <c:forEach items="${listBookInCart}" var="rows1">
                                            <c:if test="${rows1.book.idBook eq rbByWeek.idBook}">
                                                <c:set var="inCart" value ="true"/>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${inCart eq true}">
                                            <img src="${icon}/incart.png" alt="<spring:message code="book.isincart"/>">
                                        </c:if>
                                        <c:if test="${inCart eq false}">
                                            <button class="addToCart" data-bind="${rbByWeek.idBook}"><spring:message code="book.addtocart"/></button>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${empty listBookInCart}">
                                        <button class="addToCart" data-bind="${rbByWeek.idBook}"><spring:message code="book.addtocart"/></button>
                                    </c:if>
                                    <img class="hide" src="${icon}/incart.png" alt="<spring:message code="book.isincart"/>">
                                    <button class="odb" data-bind="${rbByWeek.idBook}#${crMbUserName}"><spring:message code="book.order"/></button>
                                </c:if>
                                <section class="fb-like" data-href="${rbByWeek.idBook}" data-layout="button" data-action="like" data-show-faces="false" data-share="false"></section>
                            </section>
                        </section>
                    </c:forEach>
                </section>
            </c:if>
        </section>

        <section class="bookByMonth">
            <h1>&clubs; <spring:message code="book.bookbymonth"/></h1>
            <c:if test="${!empty listBookByMonth}">
                <section id="bookByMonth">
                    <c:forEach items="${listBookByMonth}" var="rbByMonth">
                        <section class="book">
                            <a class="coverimg2" href="<%=request.getContextPath()%>/book/${rbByMonth.idBook}-${rbByMonth.sortLink}.html">
                                <img src="${image}/${rbByMonth.image}" alt="${rbByMonth.title}">
                            </a>
                            <a class="bgasl" href="<%=request.getContextPath()%>/book/${rbByMonth.idBook}-${rbByMonth.sortLink}.html">
                                <section>
                                    <h2>${rbByMonth.title}</h2>
                                    <h3><i><spring:message code="book.author"/></i><br>${rbByMonth.author}</h3>
                                </section>
                            </a>
                            <c:if test="${rbByMonth.originalPrice != -1}">
                                <h4><i><spring:message code="book.originalprice"/>:</i> <strike><font color="green">${rbByMonth.originalPrice} VNĐ</font></strike></h4>
                                    </c:if>
                                    <c:if test="${rbByMonth.originalPrice eq -1}">
                                <h4><i><spring:message code="book.originalprice"/>:</i> <i><font color="green"><spring:message code="book.isupdating"/></font></i></h4>
                                    </c:if>
                                    <c:if test="${rbByMonth.salePrice != -1}">
                                <h3><i><spring:message code="book.saleprice"/>:</i> <font color="red">${rbByMonth.salePrice} VNĐ</font></h3>
                                </c:if>
                                <c:if test="${rbByMonth.salePrice eq -1}">
                                <h3><i><spring:message code="book.status"/>:</i> <i><spring:message code="book.outofstock"/></i></h3>
                            </c:if>
                            <h5>
                                <i><spring:message code="book.category"/>:</i>
                                <a>
                                    &nbsp;${rbByMonth.category.name}
                                </a>
                            </h5>
                            <h5>
                                <i><spring:message code="book.bookset"/>:</i>
                                <a>
                                    &nbsp;${rbByMonth.set.name}
                                </a>
                            </h5>
                            <section class="option">
                                <c:if test="${rbByMonth.salePrice != -1}">
                                    <c:if test="${!empty listBookInCart}">
                                        <c:set var="inCart" value ="false"/>
                                        <c:forEach items="${listBookInCart}" var="rows1">
                                            <c:if test="${rows1.book.idBook eq rbByMonth.idBook}">
                                                <c:set var="inCart" value ="true"/>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${inCart eq true}">
                                            <img src="${icon}/incart.png" alt="<spring:message code="book.isincart"/>">
                                        </c:if>
                                        <c:if test="${inCart eq false}">
                                            <button class="addToCart" data-bind="${rbByMonth.idBook}"><spring:message code="book.addtocart"/></button>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${empty listBookInCart}">
                                        <button class="addToCart" data-bind="${rbByMonth.idBook}"><spring:message code="book.addtocart"/></button>
                                    </c:if>
                                    <img class="hide" src="${icon}/incart.png" alt="<spring:message code="book.isincart"/>">
                                    <button class="odb" data-bind="${rbByMonth.idBook}#${crMbUserName}"><spring:message code="book.order"/></button>
                                </c:if>
                                <section class="fb-like" data-href="${rbByMonth.idBook}" data-layout="button" data-action="like" data-show-faces="false" data-share="false"></section>
                            </section>
                        </section>
                    </c:forEach>
                </section>
            </c:if>
        </section>

        <section class="bookByYear">
            <h1>&clubs; <spring:message code="book.bookbyyear"/></h1>
            <c:if test="${!empty listBookByYear}">
                <section id="bookByYear">
                    <c:forEach items="${listBookByYear}" var="rbByYear">
                        <section class="book">
                            <a class="coverimg2" href="<%=request.getContextPath()%>/book/${rbByYear.idBook}-${rbByYear.sortLink}.html">
                                <img src="${image}/${rbByYear.image}" alt="${rbByYear.title}">
                            </a>
                            <a class="bgasl" href="<%=request.getContextPath()%>/book/${rbByYear.idBook}-${rbByYear.sortLink}.html">
                                <section>
                                    <h2>${rbByYear.title}</h2>
                                    <h3><i><spring:message code="book.author"/></i><br>${rbByYear.author}</h3>
                                </section>
                            </a>
                            <c:if test="${rbByYear.originalPrice != -1}">
                                <h4><i><spring:message code="book.originalprice"/>:</i> <strike><font color="green">${rbByYear.originalPrice} VNĐ</font></strike></h4>
                                    </c:if>
                                    <c:if test="${rbByYear.originalPrice eq -1}">
                                <h4><i><spring:message code="book.originalprice"/>:</i> <i><font color="green"><spring:message code="book.isupdating"/></font></i></h4>
                                    </c:if>
                                    <c:if test="${rbByYear.salePrice != -1}">
                                <h3><i><spring:message code="book.saleprice"/>:</i> <font color="red">${rbByYear.salePrice} VNĐ</font></h3>
                                </c:if>
                                <c:if test="${rbByYear.salePrice eq -1}">
                                <h3><i><spring:message code="book.status"/>:</i> <i><spring:message code="book.outofstock"/></i></h3>
                            </c:if>
                            <h5>
                                <i><spring:message code="book.category"/>:</i>
                                <a>
                                    &nbsp;${rbByYear.category.name}
                                </a>
                            </h5>
                            <h5>
                                <i><spring:message code="book.bookset"/>:</i>
                                <a>
                                    &nbsp;${rbByYear.set.name}
                                </a>
                            </h5>
                            <section class="option">
                                <c:if test="${rbByYear.salePrice != -1}">
                                    <c:if test="${!empty listBookInCart}">
                                        <c:set var="inCart" value ="false"/>
                                        <c:forEach items="${listBookInCart}" var="rows1">
                                            <c:if test="${rows1.book.idBook eq rbByYear.idBook}">
                                                <c:set var="inCart" value ="true"/>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${inCart eq true}">
                                            <img src="${icon}/incart.png" alt="<spring:message code="book.isincart"/>">
                                        </c:if>
                                        <c:if test="${inCart eq false}">
                                            <button class="addToCart" data-bind="${rbByYear.idBook}"><spring:message code="book.addtocart"/></button>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${empty listBookInCart}">
                                        <button class="addToCart" data-bind="${rbByYear.idBook}"><spring:message code="book.addtocart"/></button>
                                    </c:if>
                                    <img class="hide" src="${icon}/incart.png" alt="<spring:message code="book.isincart"/>">
                                    <button class="odb" data-bind="${rbByYear.idBook}#${crMbUserName}"><spring:message code="book.order"/></button>
                                </c:if>
                                <section class="fb-like" data-href="${rbByYear.idBook}" data-layout="button" data-action="like" data-show-faces="false" data-share="false"></section>
                            </section>
                        </section>
                    </c:forEach>
                </section>
            </c:if>
        </section>
    </section>
    <%@include file="inc/rightAside.jsp" %>
</section>
<%@include file="inc/seenBook.jsp" %>

<%@include file="../template/footer.jsp" %>