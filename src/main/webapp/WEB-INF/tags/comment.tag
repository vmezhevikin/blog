<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ attribute name="comment" required="false" type="com.vmezhevikin.blog.entity.Comment"%>
<div class="comment" id="comment${comment.id}">
	<c:if test="${comment.author.image != null}">
		<img class="comment-image" src="${comment.author.image}" alt="User image">
	</c:if>
	<c:if test="${comment.author.image == null}">
		<img class="comment-image" src="/static/img/blank-photo-sm.jpg" alt="User image">
	</c:if>
	<div class="comment-text">
		<h4>${comment.author.name}</h4>
		<p>${comment.getDateAsFormattedString()}</p>
		<p>${comment.text}</p>
		<sec:authorize access="hasAuthority('USER')">
			<sec:authentication var="principalId" property="principal.id" />
			<c:if test="${principalId == comment.author.id}">
				<a class="delCommentBtn" data-comment-id="${comment.id}">Remove</a>
			</c:if>
		</sec:authorize>
	</div>
</div>