<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light sticky">
  <a class="navbar-brand" href="/"><img src="/images/logo.png" alt="logo"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav mr-auto">
	<li class="nav-item">
		<form class="form-inline" action="/search/results" method="post">
			<input class="form-control mr-sm-1" type="search" placeholder="Search" name="search">
			<button class="btn btn-outline-info my-2 my-sm-0 mr-4" type="submit">Search</button>
		</form>
	</li>
      <li class="nav-item">
        <a class="nav-link" href="/">All Recipes</a>
      </li>
      <c:if test="${not empty session}">
	      <li class="nav-item">
	        <a class="nav-link" href="/recipes/new">Add Recipe</a>
	      </li>
      </c:if>
    </ul>
    <c:if test="${not empty session}">
	    <div class="navbar-text">
	  		<span class="mr-4">Welcome back <c:out value="${session.getUsername()}"/>!</span>
	  		<a class='text-danger' href="/logout" title="logout">Logout</a>
	    </div>    
    </c:if>
    <c:if test="${empty session}">
	    <div class="navbar-text">
	  		<a class='text-info' href="/login" title="login">Login</a>
	    </div>      
    </c:if>
  </div>
</nav>