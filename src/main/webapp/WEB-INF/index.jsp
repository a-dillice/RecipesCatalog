<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Recipes</title>
	<link rel="icon" href="/images/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" />		
	<link href="/webjars/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">	
	<link href="/css/main.css" rel="stylesheet">	
	<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
	<script src="/webjars/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="/webjars//bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="/scripts/main.js"></script>
</head>
<body class="bg bg1">
	<c:if test="${not empty success}">
		<div class="alert nav-alert alert-success" role="alert">
			<a href="#" class="close" title="close">×</a>
			<c:out value="${success}"/>
		</div>	
	</c:if>
	<c:if test="${not empty errors}">
		<div class="alert nav-alert alert-danger" role="alert">
			<a href="#" class="close" title="close">×</a>
			<c:out value="${errors}"/>
		</div>	
	</c:if>	
	
	<!--  top nav -->
	<c:import url="nav.jsp"/>
	
	<!--  content -->
	<div class="container p-4">
		
		<div class="jumbotron">
			<h1 class="display-4">Recipes</h1>
			<p class="lead">To get the full recipe please click the learn more button on the recipe you are interested in! If you want to add a recipe, please sign in or register <a class="text-info" href="/login">here.</a></p>
			<hr class="my-4">
			
			<!-- all recipe card -->
			<div class="all-recipes">
				<c:if test="${empty recipes}">
					<div class="alert nav-alert alert-warning" role="alert">
						Currently we have no recipes to display. <a href="/recipes/new" title="add recipe">Click here</a> to add a new recipe.							
					</div>
				</c:if>
				<c:forEach items="${recipes}" var="recipe">
					<div class="card">
					  <img class="card-img-top" src="/images/recipes/${recipe.getImage()}" alt="${recipe.getTitle()} image">
					  <div class="card-body">
					    <p class="uploaded-date card-text text-muted"><c:out value="${recipe.getDate()}"/></p>
					    <h5 class="card-title"><c:out value="${recipe.getTitle()}"/></h5>
   					  <div class="category-wrapper">
						  	<c:forEach items="${recipe.getCategorys()}" var="cat">
						  		<span class="category-name badge badge-info"><c:out value="${cat.name}"/></span>
						  	</c:forEach>
					  </div>
					    <p class="card-text"><c:out value="${recipe.getSummary()}"/></p>
					    <p class="uploaded-by card-text text-muted">By: <c:out value="${recipe.user.username}"/></p>
					    <a href="/recipe/show/${recipe.getId()}" class="btn btn-primary">Learn More</a>
					  </div>
					</div>
				</c:forEach>
			</div>
			
		</div>
		
	</div>
	
</body>
</html>