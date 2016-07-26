<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="blog" tagdir="/WEB-INF/tags"%>
<body>
	<div class="container">
		<div class="row">
			<nav class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<ol class="breadcrumb">
					<li>
						<a href="/news">News</a>
					</li>
					<li>
						<a href="/category/${article.category.id}">${article.category.name}</a>
					</li>
					<li class="active">${article.name}</li>
				</ol>
				<a id="xs-option" class="btn btn-default" role="button" data-toggle="collapse" href="#categoryList" aria-expanded="false"
					aria-controls="categoryList">
					Categories
					<span class="caret"></span>
				</a>
			</nav>
			<aside id="categoryList" class="col-xs-12 col-sm-12 col-md-2 col-lg-2 collapse category-list">
				<div class="panel panel-default">
					<div class="panel-heading hidden-xs hidden-sm">
						<h3 class="panel-title">Categories</h3>
					</div>
					<ul class="list-group">
						<c:forEach var="category" items="${categories}">
							<li class="list-group-item">
								<a href="/category/${category.id}">${category.name}</a>
								<span class="badge">${category.count}</span>
							</li>
						</c:forEach>
					</ul>
				</div>
			</aside>
			<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
				<article class="panel panel-body">
					<c:if test="${article.image != null}">
						<img class="article-image" src="${article.image}" alt="Article image">
					</c:if>
					<c:if test="${article.image == null}">
						<img class="article-image" src="/static/img/blank-photo-sm.jpg" alt="Article image">
					</c:if>
					<h3>${article.name}</h3>
					<ul class="nav nav-pills">
						<li role="presentation">
							<a href="/category/${article.category.id}">
								<i class="fa fa-folder" aria-hidden="true"></i>
								${article.category.name}
							</a>
						</li>
						<li role="presentation">
							<a class="article-stat">
								<i class="fa fa-user" aria-hidden="true"></i>
								${article.author.name}
							</a>
						</li>
						<li role="presentation">
							<a class="article-stat">
								<i class="fa fa-comments" aria-hidden="true"></i>
								${article.comments.size()} comments
							</a>
						</li>
						<li role="presentation">
							<a class="article-stat">
								<i class="fa fa-calendar" aria-hidden="true"></i>
								${article.getDateAsFormattedString()}
							</a>
						</li>
						<li role="presentation">
							<a class="article-stat">
								<i class="fa fa-eye" aria-hidden="true"></i>
								Hits: ${article.views}
							</a>
						</li>
					</ul>
					<div class="article-text">${article.text}</div>
					<sec:authorize access="hasAuthority('USER')">
						<sec:authentication var="principalId" property="principal.id"/>
						<sec:authentication var="principalName" property="principal.name"/>
						<div class="comment">
							<input id="articleId" type="hidden" value="${article.id}"/>
							<input id="authorId" type="hidden" value="${principalId}"/>
							<input id="csrfToken" type="hidden" value="${_csrf.token}"/>
							<textarea id="addCommentText" class="comment-textarea" placeholder="Type your comment here" rows="4"></textarea>
							<button id="addCommentBtn" class="btn btn-primary">Send</button>
						</div>
					</sec:authorize>
					<div id="commentContainer" data-article-id="${article.id}" data-total-comments="${totalComments}" data-first-comments="${firstComments}">
						<c:forEach var="comment" items="${comments}">
							<blog:comment comment="${comment}" />
						</c:forEach>
					</div>
					<div class="comment-more" id="comment-more">
						<img id="loadAllCommentIndicator" src="/static/img/loading.gif" alt="Loading...">
						<button id="loadAllCommentBtn" class="btn btn-info">Show all...</button>
					</div>
				</article>
			</div>
		</div>
	</div>
</body>