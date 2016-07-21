<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
	<div class="container">
		<div class="row">
			<nav class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<ol class="breadcrumb">
					<li>
						<a href="/news">News</a>
					</li>
					<li>
						<a href="#">${article.category.name}</a>
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
					<img class="article-image" src="${article.image}" alt="Article image">
					<h3>${article.name}</h3>
					<ul class="nav nav-pills">
						<li role="presentation">
							<a href="#">
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
					<div class="comment">
						<form action="comment" method="post">
							<input type="hidden" name="idArticle" value="${article.id}">
							<textarea class="comment-textarea" name="comment" placeholder="Type your comment here" rows="4"></textarea>
							<input class="btn btn-primary" type="submit" value="Send">
						</form>
					</div>
					<c:forEach var="comment" items="${comments}">
						<div class="comment">
							<img class="comment-image" src="${comment.author.image}" alt="User image">
							<div class="comment-text">
								<h4>${comment.author.name}</h4>
								<p>${comment.getDateAsFormattedString()}</p>
								<p>${comment.text}</p>
							</div>
						</div>
					</c:forEach>
					<div class="comment-more">
						<img id="loadMoreCommentIndicator" src="/media/img/loading.gif" alt="Loading...">
						<button id="more-comment" class="btn btn-info">Show more...</button>
					</div>
				</article>
			</div>
		</div>
	</div>
</body>