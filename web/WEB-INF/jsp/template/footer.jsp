<%@page import="entity.book.BookSet"%>
<%@page import="entity.book.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<spring:url value="/resources/image/thumb" var="thumb" />
<spring:url value="/resources/image/icon" var="icon" />
<spring:url value="/resources/js/jquery-2.1.4.min.js" var="jquery" />
<spring:url value="/resources/js/script.js" var="script" />
<spring:url value="/resources/js/html5.js" var="html5" />
<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapjs" />
<spring:url value="/resources/js/slick.min.js" var="slick" />

            <footer class="clearfix">
                <section class="insidefooter clearfix">
                    <section class="cl1">
                        <div class="fb-page" data-href="https://www.facebook.com/facebook" data-width="380" data-height="150" data-tabs="timeline" data-small-header="false" data-adapt-container-width="true" data-hide-cover="false" data-show-facepile="true"><div class="fb-xfbml-parse-ignore"><blockquote cite="https://www.facebook.com/facebook"><a href="https://www.facebook.com/facebook">Facebook</a></blockquote></div></div>
                    </section>
                    <section class="cl">
                        <h1><spring:message code="nav.category"/></h1>
                        <c:if test="${!empty scCategory}">
                    <% int indexCtFt = 0;%>
                    <c:forEach items="${scCategory}" var="rowsScCt">
                        <h2>
                            <a href="<%=request.getContextPath()%>/category/<%=indexCtFt%>-${rowsScCt.sortLink}.html">
                                &raquo; ${rowsScCt.name}
                            </a>
                        </h2>
                        <% indexCtFt++;%>
                    </c:forEach>
                    <% indexCtFt = 0;%>
                    </c:if>
                    </section>
                    <section class="cl">
                        <h1><spring:message code="aside.bookset"/></h1>
                        <c:if test="${!empty scBookSet}">
                    <% int indexBsFt = 0;%>
                    <c:forEach items="${scBookSet}" var="rowsScBs">
                        <h2>
                            <a href="<%=request.getContextPath()%>/bookset/<%=indexBsFt%>-${rowsScBs.sortLink}.html">
                                &raquo; ${rowsScBs.name}
                            </a>
                        </h2>
                        <% indexBsFt++;%>
                    </c:forEach>
                    <% indexBsFt = 0;%>
                    </c:if>
                    </section>
                    <section class="btfooter clearfix">
                        <a href="<%=request.getContextPath()%>/home.html">
                            <img src="${thumb}/logo.jpg" alt="Logo">
                        </a>
                        <h2><%=request.getContextPath()%> by tientx</h2>
                    </section>
                </section>
            </footer>
            <section class="mirrorbg hide" id="mirrorbg">
                <fieldset>
                    <legend><spring:message code="user.login"/></legend>
                    <form id="lgFrm" action="" method="post">
                        <img id="clostlogin" src="${icon}/close1.png" title="close">
                        <h2 class="hide"><spring:message code="user.loginerror"/></h2>
                        <input name="username" autofocus="" type="text" required="" pattern="[a-zA-Z0-9_]{6,30}" placeholder="<spring:message code="user.username"/>">
                        <input name="password" type="password" required="" pattern=".{6,30}" placeholder="<spring:message code="user.password"/>">
                        <%
                        String crQr = request.getQueryString();
                        if (crQr != null) {
                        %>
                        <input name="crUrl" type="hidden" value="<%=request.getAttribute("javax.servlet.forward.request_uri") + "?" + crQr%>">
                        <%
                        } else {
                        %>
                        <input name="crUrl" type="hidden" value="<%=request.getAttribute("javax.servlet.forward.request_uri")%>">
                        <%
                            }
                        %>
                        <input type="submit" value="OK">
                    </form>
                </fieldset>
            </section>
            <section id="back-to-top" title="Back to top">&uarr;</section>
            <script type="text/javascript" src="${jquery}"></script>
            <script type="text/javascript" src="${script}"></script>
            <script type="text/javascript" src="${html5}"></script>
            <script type="text/javascript" src="${bootstrapjs}"></script>
            <script type="text/javascript" src="${slick}"></script>
        </section>
    </body>
</html>