<%@page import="tool.MyTool"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <spring:url value="/resources/image/thumb" var="logo" />
        <link rel="Shortcut Icon" href="${logo}/logo.jpg" type="image/x-icon" />
        <title><spring:message code="mess.home"/></title>
        <spring:url value="/resources/image/icon" var="icon" />
        <spring:url value="/resources/css/style.css" var="style" />
        <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapcss" />
        <spring:url value="/resources/css/slick.css" var="slickcss" />
        <spring:url value="/resources/image/icon" var="icon" />
        <link href="${style}" rel="stylesheet" type="text/css"/>
        <link href="${bootstrapcss}" rel="stylesheet" type="text/css"/>
        <link href="${slickcss}" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <section class="clearfix wrapper">
            <div id="fb-root"></div>
            <script>(function (d, s, id) {
                    var js, fjs = d.getElementsByTagName(s)[0];
                    if (d.getElementById(id))
                        return;
                    js = d.createElement(s);
                    js.id = id;
                    js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.5&appId=834009886708645";
                    fjs.parentNode.insertBefore(js, fjs);
                }(document, 'script', 'facebook-jssdk'));
            </script>
            <header class="clearfix">
                <section class="insidefix clearfix">
                    <%
                        String crPr = request.getQueryString();
                        if (crPr != null) {
                            crPr = MyTool.handleParameterString(crPr);
                    %>
                    <a href="<%=request.getAttribute("javax.servlet.forward.request_uri") + "?" + crPr%>lang=vi"><img src="${icon}/viIcon.png" alt="Vi"></a>
                    <a href="<%=request.getAttribute("javax.servlet.forward.request_uri") + "?" + crPr%>lang=en"><img src="${icon}/enIcon.png" alt="En"></a>
                        <%
                        } else {
                        %>
                    <a href="?lang=vi"><img src="${icon}/viIcon.png" alt="Vi"></a>
                    <a href="?lang=en"><img src="${icon}/enIcon.png" alt="En"></a>
                        <%
                            }
                        %>
                    <span>
                        <%
                            out.print(MyTool.getCurrentTimeInYYYYMMDDHH24mmssFormat());
                        %>
                    </span>
                </section>
            </header>