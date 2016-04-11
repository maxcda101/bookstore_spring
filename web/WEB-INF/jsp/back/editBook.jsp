<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<spring:url value="/resources/image/thumb" var="image" />

<%@include file="inc/header.jsp" %>
<script>tinymce.init({ selector:'textarea' });</script>
<section class="addBook">
	<section class="msg">${msg }</section>
	<h3>Sửa sách</h3>
	<form:form id="addBookFrm" commandName="bookEdit" 
		action="${pageContext.request.contextPath }/staffmanager/checkEditBook"  
		enctype="multipart/form-data" method="post" >
		<table>
			<tr> 
				<td>
					<form:input path="idBook" value="${book.idBook }" style="display: none"/>
				</td>
			</tr>
			<tr> <td><span>Tiêu đề: </span></td>
				<td><form:input 
					path="title" 
					value="${book.title }" 
					/>
				</td>
			</tr>
			<tr> <td><span>Hình ảnh: </span></td>
				<td><img src="${image }/${book.image}" style="width: 300px; height: 300px;"/></td>
				<td><form:input path="image" value="${book.image }" style="display: none"/></td>
			</tr>
			<tr>
				<td></td>
				<td colspan="2"><input name="fImage" type="file" /></td>
			</tr>
			<tr> <td><span>Tác giả: </span></td>
				<td><form:input 
					path="author" 
					value="${book.author }" 
					/>
				</td>
			</tr>
			<tr> <td><span>Nhà xuất bản: </span></td>
				<td><form:input 
					path="publisher" 
					value="${book.publisher }" 
					/>
				</td>
			</tr>
			<tr> <td><span>Năm sản xuất: </span></td>
				<td><form:input 
					path="publishYear" 
					value="${book.publishYear }" 
					/>
				</td>
			</tr>
			<tr> <td><span>Mô tả: </span></td>
				<td>
					<textarea id="area1"
						style="width: 700px; height: 200px;" 
						name="description">
						${book.description }
					</textarea>
				</td>
			</tr>
			<tr> <td><span>Giá bìa: </span></td>
				<td><form:input path="originalPrice" value="${book.originalPrice }" /></td>
			</tr>
			<tr> <td><span>Giá sách: </span></td>
				<td><form:input path="salePrice" value="${book.salePrice }" /></td>
			</tr>
			<tr> <td><span>Số lượng: </span></td>
				<td><form:input path="quantity" value="${book.quantity }" /></td>
			</tr>
			<tr> <td>Chuyên mục:</td>
				<td><form:select path="category.idCategory">
					<form:option value="${book.category.idCategory }" selected="selected">${book.category.name }</form:option>
					<c:forEach items="${list[0] }" var="rowsC">
						<form:option value="${rowsC.idCategory }">
							${rowsC.name }
						</form:option>
					</c:forEach>
				</form:select></td>
			</tr>
			<tr> <td>Bộ sách:</td>
				<td><form:select path="set.idBookSet">
					<form:option value="${book.set.idBookSet }" selected="selected">${book.set.name }</form:option>
						<c:forEach items="${list[1] }" var="rowsBS">
							<option value="${rowsBS.idBookSet }">${rowsBS.name }</option>
						</c:forEach>
				</form:select></td>
			</tr>
			<tr> <td colspan="2">
				<input type="submit" value="Cập nhật" /> 
			</td> </tr>
		</table> <!-- End Table -->
	</form:form> <!-- End Form -->
</section>
<%@include file="inc/footer.jsp" %>
