<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="tool.MyTool"%>
<%@page import="entity.person.employee.Employee"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="pg" uri="/WEB-INF/lib/pagertag.jar" %>
<spring:url value="/resources/image/thumb" var="image" />

<!DOCTYPE html>
<html>
<head>
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	  <spring:url value="/resources/image/thumb" var="logo" />
	  <link rel="Shortcut Icon" href="${logo}/logo.jpg" type="image/x-icon" />
	  <title>${title}</title>
	  <spring:url value="/resources/tinymce/tinymce.min.js" var="tinymce" />
	  <script type="text/javascript" src="${tinymce }"></script>
</head>
<body>	
	<section class="inside clearfix">
		<a href="${pageContext.request.contextPath }/staffmanager">Staff Manager 
		<a href="<%=request.getContextPath()%>/home.html">
				Trang chủ
		</a>
		<button id="lobtn">Đăng xuất</button>
	
