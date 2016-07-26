<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="blog" tagdir="/WEB-INF/tags"%>
<body>
	<c:forEach var="comment" items="${comments}">
		<blog:comment comment="${comment}" />
	</c:forEach>
</body>