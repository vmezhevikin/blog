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
					<c:if test="${currentCategory != null}">
						<li>
							<a href="/category/${currentCategory.id}">${currentCategory.name}</a>
						</li>
					</c:if>
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
				<c:forEach var="article" items="${articles}">
					<article class="panel panel-body">
						<a href="/article/${article.id}">
							<img class="article-image" src="${article.image}" alt="Article image">
						</a>
						<h3>
							<a href="/article/${article.id}">${article.name}</a>
						</h3>
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
						<div class="article-text">${article.description}</div>
					</article>
				</c:forEach>
				<nav class="text-center">
					<ul class="pagination" data-page-number="${page.number}" data-page-total="${page.totalPages}" data-category-url="${categoryUrl}">
						<li id="prev">
							<a class="pgLink" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span>
							</a>
						</li>
						<li id="btn1">
							<a class="pgLink"></a>
						</li>
						<li id="btn2">
							<a class="pgLink"></a>
						</li>
						<li id="btn3">
							<a class="pgLink"></a>
						</li>
						<li id="btn4">
							<a class="pgLink"></a>
						</li>
						<li id="btn5">
							<a class="pgLink"></a>
						</li>
						<li id="next">
							<a class="pgLink" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a>
						</li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
</body>