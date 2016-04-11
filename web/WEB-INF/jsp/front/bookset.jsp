
<%@page import="entity.book.BookSet"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/resources/image/thumb" var="image" />
<!--<section class="clearfix wrapper">-->
    <%@include file="../template/header.jsp" %>
    <%@include file="inc/topHeader.jsp" %>
    <%@include file="inc/navigation.jsp" %>

    <section class="inside clearfix mg">
        <section class="content">
            <h1>&clubs; <spring:message code="book.bookset"/>:</h1>
            <c:if test="${!empty scBookSet}">
                <% int rScBsIndex = 0;%>
                <ul class="allbs">
                    <c:forEach items="${scBookSet}" var="rowsScBs">
                        <li>
                            <a href="<%=request.getContextPath()%>/bookset/<%=rScBsIndex%>-${rowsScBs.sortLink}.html">
                                <h2>&raquo; ${rowsScBs.name}</h2>
                            </a>
                        </li>
                        <% rScBsIndex++;%>
                    </c:forEach>
                    <% rScBsIndex = 0;
                    %>
                </ul>
            </c:if>
        </section>
        <%@include file="inc/rightAside.jsp" %>
    </section>
    <%@include file="inc/seenBook.jsp" %>

    <%@include file="../template/footer.jsp" %>
<!--</section>-->