<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-offset-1 col-md-10 col-lg-10">
				<form action="/sign-in-handler" method="post">
					<c:if test="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION != null}">
						<div class="alert alert-danger" role="alert">
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message }
							<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" />
						</div>
					</c:if>
					<div class="form-group">
						<label>Email</label>
						<input name="name" class="form-control" type="text" placeholder="Type your name">
					</div>
					<div class="form-group">
						<label>Password</label>
						<input name="password" class="form-control" type="password" placeholder="Type your password"></input>
					</div>
					<div class="checkbox">
						<label>
							<input name="remember-me" type="checkbox">
							Remember me
						</label>
					</div>
					<input class="btn btn-info" type="submit" value="Sign in">
					<sec:csrfInput />
				</form>
			</div>
		</div>
	</div>
</body>