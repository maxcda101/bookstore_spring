<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="tool.MyTool"%>
<%@page import="entity.person.employee.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="pg" uri="/WEB-INF/lib/pagertag.jar"%>
<spring:url value="/resources/image/thumb" var="image" />

<%@include file="inc/header.jsp" %>
	<section class="msg">${msg }</section>
	<a href="${pageContext.request.contextPath }/staffmanager/addBook"
		class="addBook"> Thêm sách</a>
		
	<section class="page">
		<c:forEach items="${pages }" var="page">
			<a href="${url }?pageNum=${page } ">${page } </a>
		</c:forEach>
	</section> <!-- End Page -->
	<section class="allBook">
		<table class="outtable">
			<tr>
				<th><spring:message code="cart.book" /></th>
				<th><spring:message code="cart.coverimage" /></th>
				<th><spring:message code="cart.author" /></th>
				<th><spring:message code="cart.price" /></th>
				<th><spring:message code="cart.saleprice" /></th>
				<th><spring:message code="cart.quantity" /></th>
				<th><spring:message code="cart.category" /></th>
				<th><spring:message code="cart.bookset" /></th>
				<th colspan="2"><spring:message code="cart.option" /></th>
			</tr>
			<pg:paging pageSize="9">
				<c:forEach items="${listBook }" var="rows">
					<pg:item>
						<tr>
							<td>${rows.title }</td>
							<td><img src="${image}/${rows.image}" alt="${rows.title}"
								style="width: 100px; height: 100px;"></td>
							<td>${rows.author }</td>
							<td>${rows.originalPrice }</td>
							<td>${rows.salePrice }</td>
							<td>${rows.quantity }</td>
							<td>${rows.category.description }</td>
							<td>${rows.set.description }</td>
							<td><a href="" class="btnDelBook" data-id="${rows.idBook }">
									Xóa </a></td>
							<td><a class="btnEditBook" data-id="${rows.idBook }"
								href="${pageContext.request.contextPath }/staffmanager/editBook?idBook=${rows.idBook }"> 
									Sửa
							</a></td>
						</tr>
					</pg:item>
				</c:forEach>
			</pg:paging>
		</table> <!-- End Table -->
	</section> <!-- End AllBook -->
</section>
<%@include file="inc/footer.jsp" %>