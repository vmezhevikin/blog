<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Blog</title>
<jsp:include page="../section/css.jsp" />
</head>
<body class="body">
	<jsp:include page="../section/header.jsp" />
	<section>
		<sitemesh:write property="body" />
	</section>
	<jsp:include page="../section/footer.jsp" />
	<jsp:include page="../section/script.jsp" />
</body>
</html>