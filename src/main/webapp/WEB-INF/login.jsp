<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login and Registration</title>
	<link rel="icon" href="/images/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" />		
	<link href="/webjars/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
	<link href="/css/login.css" rel="stylesheet">
	<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
	<script src="/scripts/login.js"></script>
</head>
<body>
	<div class="container">
	
		<div class="card login-wrapper">
		  <div class="card-body">

			<!-- tabs -->
			<ul class="nav nav-tabs">
				<li class="nav-item">
					<a class="nav-link active" href="#" data-target="login-form">Sign In</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#" data-target="registration-form">Register</a>
				</li>
			</ul>	

			<!--  login form -->
			<form:form class="forms login-form" action="/signin/process" method="post" modelAttribute="signin">
				<div class="form-group">
					<label for="email">Email</label>
					<form:input type="email" class="form-control" id="email" path="email"/>
					<form:errors class="text-danger" path="email"/>
				</div>
				<div class="form-group">
					<label for="password">Password</label>
					<form:password class="form-control" id="password" path="password"/>
					<form:errors class="text-danger" path="password"/>
					<c:if test="${not empty errors}">
						<div class="alert alert-danger mt-4" role="alert">
						  <c:out value="${errors}"/>
						</div>	
					</c:if>
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form:form>
	
			<!-- registration form -->
			<form:form class="forms registration-form hide" action="/register/process" method="post" modelAttribute="register">
				<div class="form-group">
					<label for="firstname">First Name</label>
					<form:input type="text" class="form-control" id="firstname" path="firstName"/>
					<form:errors class="text-danger" path="firstName"/>
				</div>
				<div class="form-group">
					<label for="lastname">Last Name</label>
					<form:input type="text" class="form-control" id="lastname" path="lastName"/>
					<form:errors class="text-danger" path="lastName"/>
				</div>
				<div class="form-group">
					<label for="username">Username</label>
					<form:input type="text" class="form-control" id="username" path="username"/>
					<form:errors class="text-danger" path="username"/>
				</div>
				<div class="form-group">
					<label for="email">Email</label>
					<form:input type="email" class="form-control" id="email" path="email"/>
					<form:errors class="text-danger" path="email"/>
				</div>
				<div class="form-group">
					<label for="password">Password</label>
					<form:password class="form-control" id="password" path="password"/>
					<form:errors class="text-danger" path="password"/>
				</div>
				<div class="form-group">
					<label for="confirm">Confirm Password</label>
					<form:password class="form-control" id="confirm" path="confirm"/>
					<form:errors class="text-danger" path="confirm"/>
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form:form>

		  </div>
		</div>	
	

	
	</div>
</body>
</html>