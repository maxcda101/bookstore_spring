<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.reflect.TypeToken"%>
<%@page import="tool.MyTool"%>
<%@page import="entity.book.BookSet"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<aside>
    <h1 id="bstt"><spring:message code="aside.bookset"/></h1>
    <ul>
        <c:if test="${!empty scBookSet}">
            <% int rowsScBsIndex = 0;%>
            <c:forEach items="${scBookSet}" var="rowsScBs">
                <li>
                    <a href="<%=request.getContextPath()%>/bookset/<%=rowsScBsIndex%>-${rowsScBs.sortLink}.html">
                        &spades; ${rowsScBs.name}
                    </a>
                </li>
                <% rowsScBsIndex++;%>
            </c:forEach>
            <% rowsScBsIndex = 0;%>
        </c:if>
    </ul>
</aside>