<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@include file="inc/header.jsp" %>

<script>tinymce.init({ selector:'textarea' });</script>
<section class="addBook">
	<section class="msg">${msg }</section>
	<h3>Thêm sách</h3>
	<form:form id="addBookFrm" commandName="book" 
		action="${pageContext.request.contextPath }/staffmanager/checkAddBook"  
		enctype="multipart/form-data" method="post" >
		<table>
			<tr> <td><span>Tiêu đề: </span></td>
				<td><form:input path="title" /></td>
			</tr>
			<tr> <td><span>Hình ảnh: </span></td>
				<td><input name="fImage" type="file" /></td>
			</tr>
			<tr> <td><span>Tác giả: </span></td>
				<td><form:input path="author" /></td>
			</tr>
			<tr> <td><span>Nhà xuất bản: </span></td>
				<td><form:input path="publisher" /></td>
			</tr>
			<tr> <td><span>Năm sản xuất: </span></td>
				<td><form:input path="publishYear" /></td>
			</tr>
			<tr> <td><span>Mô tả: </span></td>
				<td><form:textarea path="description" /></td>
			</tr>
			<tr> <td><span>Giá bìa: </span></td>
				<td><form:input path="originalPrice" /></td>
			</tr>
			<tr> <td><span>Giá sách: </span></td>
				<td><form:input path="salePrice" /></td>
			</tr>
			<tr> <td><span>Số lượng: </span></td>
				<td><form:input path="quantity" /></td>
			</tr>
			<tr> <td>Chuyên mục:</td>
				<td><form:select path="category.idCategory">
						<c:forEach items="${list[0] }" var="rowsC">
							<form:option value="${rowsC.idCategory }">${rowsC.name }</form:option>
						</c:forEach>
				</form:select></td>
			</tr>
			<tr> <td>Bộ sách:</td>
				<td><form:select path="set.idBookSet">
						<c:forEach items="${list[1] }" var="rowsBS">
							<option value="${rowsBS.idBookSet }">${rowsBS.name }</option>
						</c:forEach>
				</form:select></td>
			</tr>
			<tr> <td colspan="2">
				<input type="submit" value="Thêm sách" /> 
			</td> </tr>
		</table> <!-- End Table -->
	</form:form> <!-- End Form -->
</section>
<%@include file="inc/footer.jsp" %>
