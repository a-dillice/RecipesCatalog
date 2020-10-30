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
	<link href="/css/editor.css" rel="stylesheet">	
	<link href="/css/main.css" rel="stylesheet">	
	<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
	<script src="/webjars/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="/webjars//bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="/scripts/editor.js"></script>
	<script src="/scripts/wysiwyg.js"></script>
	<script src="/scripts/main.js"></script>
</head>
<body class="bg bg2">
	
	<c:if test="${not empty success}">
		<div class="alert nav-alert alert-success" role="alert">
			<a href="#" class="close" title="close">×</a>
			<c:out value="${success}"/>
		</div>	
	</c:if>
	
	<!--  top nav -->
	<c:import url="nav.jsp"/>
	
	<!--  content -->
	<div class="container bg-white p-4">
		<h2>Add a Recipe</h2>
		<form:form action="/recipes/new/process" method="post" enctype="multipart/form-data" modelAttribute="recipe">	
			<div class="form-group">
				<label for="image">Image<span class="required">*</span></label>
				<div class="input-group">
				  <div class="input-group-prepend">
				    <span class="input-group-text">Upload</span>
				  </div>
				  <div class="custom-file">
				    <form:input type="file" class="custom-file-input" id="image" path="file"/>
				    <label class="custom-file-label" for="image">
				    	<c:out value="${empty recipe.getFile().getOriginalFilename() ? 'Choose file' : recipe.getFile().getOriginalFilename()}"/>
				    </label>
				  </div>
				</div>	
			    <form:errors class="text-danger" path="file"/>
			</div>
			<div class="form-group">
				<label for="title">Title<span class="required">*</span></label>
				<form:input class="form-control" id="title" path="title"/>
				<form:errors class="text-danger" path="title"/>
			</div>				
			<div class="form-group">
				<label for="categories">Categories<span class="required">*</span></label>
				<form:input class="form-control" id="title" path="categories"/>
				<small class="text-muted">Add up to 3 categories separated by a comma. Example: Easy, Dinner, Vegetable</small>
				<form:errors class="text-danger" path="categories"/>
			</div>						
			 <div class="form-group">
			   	<label for="summary">summary<span class="required">*</span></label>
		   		<form:textarea class="form-control" id="summary" rows="3" maxLength="250" path="summary"/>
		 		<form:errors class="text-danger" path="summary"/>
			 </div>				
						  		
			<div class="form-group">
				<label for="yield">Yield<span class="required">*</span></label>
				<form:input type="number" min="1" class="form-control" id="yield" path="yield"/>
				<small class="text-muted">The final amount of the finished product.</small>
				<form:errors class="text-danger" path="yield"/>
			</div>
			<div class="form-group">
				<label for="time">Total Cooking Time<span class="required">*</span></label>
				<form:input class="form-control" id="time" path="time"/>
				<small class="text-muted">Example: 1 hour and 30 minutes, plus 12 hours' chilling</small>
				<form:errors class="text-danger" path="time"/>
			</div>
		  <div class="form-group">
		    	<label for="ingredients">Ingredients<span class="required">*</span></label>
		    	<form:textarea class="form-control" id="ingredients" rows="3" path="ingredients"/>
		    	<form:errors class="text-danger" path="ingredients"/>
		  </div>
		  <div class="form-group">
		    	<label for="directions">Directions<span class="required">*</span></label>
		    	<form:textarea class="form-control" id="directions" rows="3" path="directions"/>
		  		<form:errors class="text-danger" path="directions"/>
		  </div>
		  <button type="submit" class="btn btn-info">Add Recipe</button>		
		</form:form>
	</div>
	
	
	

</body>
</html>