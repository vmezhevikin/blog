<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<header class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#searchArticle" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/news">Blog</a>
		</div>
		<div class="collapse navbar-collapse" id="searchArticle">
			<form class="navbar-form navbar-right" role="search" action="search" method="get">
				<div class="form-group">
					<input type="text" class="form-control" name="query" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">Find</button>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!hasAuthority('USER')">
					<li>
						<a href="/sign-in">Sign-in</a>
					</li>
					<li>
						<a href="/sign-up">Sign-up</a>
					</li>
				</sec:authorize>
				<sec:authorize access="hasAuthority('USER')">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
							<sec:authentication property="principal.name" />
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="/user/add-article">
									<i class="fa fa-file-text-o" aria-hidden="true"></i>
									Add article
								</a>
							</li>
							<li>
								<a href="/user/my-articles">
									<i class="fa fa-files-o" aria-hidden="true"></i>
									All articles
								</a>
							</li>
							<li>
								<a href="/user/edit-info">
									<i class="fa fa-pencil" aria-hidden="true"></i>
									Edit profile
								</a>
							</li>
							<li>
								<a href="/user/edit-password">
									<i class="fa fa-unlock-alt" aria-hidden="true"></i>
									Edit password
								</a>
							</li>
							<li role="separator" class="divider"></li>
							<li>
								<a href="/user/remove">
									<i class="fa fa-trash" aria-hidden="true"></i>
									Remove
								</a>
							</li>
							<li>
								<form id="signoutForm" action="/sign-out" method="post">
									<a id="signoutBtn">
										<i class="fa fa-sign-out" aria-hidden="true"></i>
										Sign-out
									</a>
									<sec:csrfInput />
								</form>
							</li>
						</ul>
					</li>
				</sec:authorize>
			</ul>
		</div>
	</div>
</header>