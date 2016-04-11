<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/resources/image/thumb" var="image" />
<%@include file="../template/header.jsp" %>
<%@include file="inc/topHeader.jsp" %>
<%@include file="inc/navigation.jsp" %>

<section class="inside clearfix mg">
    <section class="content">
        <c:if test="${!empty registed && registed == true}">
            <section id="registed">
                <h2><spring:message code="user.registed"/></h2> &nbsp;
                <a id="lgnow"><spring:message code="user.loginnow"/></a>
            </section>
        </c:if>
        <c:if test="${!empty rgError && rgError == true}">
            <h2 id="signuperror"><spring:message code="user.signuperror"/></h2>
        </c:if>
        <fieldset>
            <legend><spring:message code="user.signup"/></legend>
            <form method="post" action="<%=request.getContextPath()%>/user/signup.html">
                <input required="" type="text" name="firstName" placeholder="<spring:message code="user.firstname"/>"
                       title="<spring:message code="user.firstnametitle"/>"
                       value="${firstName}"
                       pattern="[a-zA-Z0-9àáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ ]{2,10}">
                <input required="" type="text" name="middleName" placeholder="<spring:message code="user.middlename"/>"
                       title="<spring:message code="user.middlenametitle"/>"
                       value="${middleName}"
                       pattern="[a-zA-Z0-9àáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ ]{2,10}">
                <input required="" type="text" name="lastName" placeholder="<spring:message code="user.lastname"/>"
                       title="<spring:message code="user.lastnametitle"/>"
                       value="${lastName}"
                       pattern="[a-zA-Z0-9àáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ ]{2,10}">
                <input required="" type="email" name="email" placeholder="<spring:message code="user.email"/>" 
                       value="${email}"
                       maxlength="30"
                       pattern="^([^.@]+)(\.[^.@]+)*@([^.@]+\.)+([^.@]+)$">
                <input required="" type="text" name="phoneNumber" placeholder="<spring:message code="user.phone"/>" 
                       value="${phoneNumber}"
                       pattern="((\+84)|(0))[1-9]\d{2,9}">
                <input required="" type="text" name="username" placeholder="<spring:message code="user.accountname"/>"
                       title="<spring:message code="user.accountnametitle"/>"
                       value="${username}"
                       pattern="[a-zA-Z0-9_]{6,30}">
                <input required="" type="password" name="password" placeholder="<spring:message code="user.pw"/>"
                       title="<spring:message code="user.signup"/>"
                       pattern=".{6,30}" value="">
                <input required="" type="password" name="rePassword" placeholder="<spring:message code="user.retypepw"/>"
                       pattern=".{6,30}" value="">
                <input required="" type="text" name="number" placeholder="<spring:message code="user.housenumber"/>"
                       value="${number}"
                       pattern="\d{1,10}">
                <input required="" type="text" name="lane" placeholder="<spring:message code="user.lane"/>"
                       value="${lane}"
                       pattern="[a-zA-Z0-9àáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ ]{1,10}">
                <input required="" type="text" name="street" placeholder="<spring:message code="user.street"/>"
                       value="${street}"
                       pattern="[a-zA-Z0-9àáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ ]{6,20}">
                <input required="" type="text" name="ward" placeholder="<spring:message code="user.ward"/>"
                       value="${ward}"
                       pattern="[a-zA-Z0-9àáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ ]{6,20}">
                <input required="" type="text" name="district" placeholder="<spring:message code="user.district"/>"
                       value="${district}"
                       pattern="[a-zA-Z0-9àáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ ]{6,20}">
                <input required="" type="text" name="city" placeholder="<spring:message code="user.city"/>"
                       value="${city}"
                       pattern="[a-zA-Z0-9àáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ ]{6,20}">
                <select name="country">
                    <option value="Việt Nam" selected="">Việt Nam</option>
                    <option value="UK">UK</option>
                </select>
                <input type="checkbox" checked="" disabled="">
                <h3><spring:message code="user.terms1"/></h3>
                <h3><a href="#"><spring:message code="user.terms2"/></a></h3>
                <h3><spring:message code="user.terms3"/></h3>
                <input type="submit" value="OK">
            </form>
        </fieldset>
    </section>
    <%@include file="inc/rightAside.jsp" %>
</section>
<%@include file="inc/seenBook.jsp" %>

<%@include file="../template/footer.jsp" %>