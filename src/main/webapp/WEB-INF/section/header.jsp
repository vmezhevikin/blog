<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<li>
					<a href="/sign-in">Sign-in</a>
				</li>
				<li>
					<a href="/sign-out">Sign-up</a>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
						Name
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="/add-article">Add article</a>
						</li>
						<li>
							<a href="/articles">All articles</a>
						</li>
						<li>
							<a href="/edit-info">Edit profile</a>
						</li>
						<li>
							<a href="/edit-password">Edit password</a>
						</li>
						<li role="separator" class="divider"></li>
						<li>
							<a href="/remove-profile">Remove</a>
						</li>
						<li>
							<a href="/sign-out">Sign-out</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</header>