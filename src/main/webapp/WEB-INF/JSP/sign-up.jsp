<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-offset-1 col-md-10 col-lg-10">
				<form:form action="/sign-up" method="post" commandName="signUpForm">
					<div class="form-group">
						<label>Name *</label>
						<input class="form-control" type="text" name="name" placeholder="Type your name">
					</div>
					<div class="form-group">
						<label>Email *</label>
						<input class="form-control" type="text" name="email" placeholder="Type your email">
					</div>
					<div class="form-group">
						<label>Password *</label>
						<input class="form-control" type="password" name="password" placeholder="Type your password"></input>
					</div>
					<div class="form-group">
						<label>Confirm *</label>
						<input class="form-control" type="password" name="confirm" placeholder="Type your password again"></input>
					</div>
					<input class="btn btn-info" type="submit" value="Send">
				</form:form>
			</div>
		</div>
	</div>
</body>