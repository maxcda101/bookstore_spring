<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>



		<spring:url value="/resources/image/thumb" var="image" />
		<spring:url value="/resources/css/style.css" var="style" />
		<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapcss" />
		<link href="${style}" rel="stylesheet" type="text/css"/>
		<link href="${bootstrapcss}" rel="stylesheet" type="text/css"/>
		<spring:url value="/resources/js/jquery-2.1.4.min.js" var="jquery" />
		<spring:url value="/resources/js/ad.js" var="adscript" />
		<spring:url value="/resources/js/html5.js" var="html5" />
		<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapjs" />
		<spring:url value="/resources/js/nicEdit.js" var="nicEdit" />
		<script type="text/javascript" src="${jquery}"></script>
		<script type="text/javascript" src="${adscript}"></script>
		<script type="text/javascript" src="${html5}"></script>
		<script type="text/javascript" src="${bootstrapjs}"></script>
		<script type="text/javascript" src="${nicEdit}"></script>
    </body>
</html>