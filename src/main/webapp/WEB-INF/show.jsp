<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Add a new Recipe</title>
	<link rel="icon" href="/images/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" />		
	<link href="/webjars/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">	
	<link href="/css/main.css" rel="stylesheet">	
	<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
	<script src="/webjars/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="/webjars//bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="/scripts/main.js"></script>
</head>
<body class="bg bg3">
	
	<!--  top nav -->
	<c:import url="nav.jsp"/>
	
	<!--  content -->
	<div class="container p-4">			
		<div class="jumbotron">
			<c:if test="${not empty recipe}">
			<h1 class="display-4"><c:out value="${recipe.getTitle()}"/></h1>
			<div class="intro">
				<img class="show-image" src="/images/recipes/${recipe.image}" alt="${recipe.title}">
				<div class="lead-wrapper">
					<p class="lead">
						<span class="creator-name"><c:out value="${recipe.user.username}"/></span>
						<span class="creator-date"><c:out value="${recipe.getDate()}"/></span>
					</p>
					<p class="category-show-wrapper">
						<c:forEach items="${recipe.getCategorys()}" var="cat">
						<span class="category-name badge badge-info"><c:out value="${cat.name}"/></span>
						</c:forEach>
					</p>					
					<p class="summary-wrapper">
						<c:out value="${recipe.summary}"/>
						<span class="time"><span>Total Time:</span> <c:out value="${recipe.time}"/></span>
						<span class="yield"><span>Yield:</span> <c:out value="${recipe.yield}"/></span>
					</p>
				</div>
			</div>
				
			<hr class="my-4">
			
			<!-- all recipe card -->
			<div class="all-recipes">
				<div class="row col-12">
					<div class="ingredients-wrapper col-4">
						<h2>Ingredients:</h2>
						<c:out value="${recipe.ingredients}" escapeXml="false"/>
					</div>
					<div class="direction-wrapper col-8">
						<h2>Directions:</h2>
						<c:out value="${recipe.directions}" escapeXml="false"/>
					</div>
				</div>
			</div>
			</c:if>
			<c:if test="${empty recipe}">
				<div class="alert nav-alert alert-warning" role="alert">
					No such recipe found.		
				</div>
			</c:if>
		</div>			
			
	</div>

</body>
</html>