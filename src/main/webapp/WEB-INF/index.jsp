<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<div style="    margin: 0px auto;
    width: 1200px;
    text-align: center;
    padding-top: 100px;">
    	<a href="/logout"  class="btn btn-sm btn-primary" style="margin-left:1000px; ">Logout</a>
    
	<h1>
		Welcome,
		<c:out value="${user.name}" />
	</h1>



	<h1  class="h3 mb-3 font-weight-normal" style="margin-left:-1000px;">All Ideas:</h1>

	
	<table class="table table-striped ">
		<thead class="thead-dark">
			<tr>
				<th scope="col">Idea</th>
				<th scope="col">Created By</th>
				<th scope="col">Likes</th>
				<th scope="col">Action</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ideas}" var="idea">
				<tr scope="row">
					<td><a href="/ideas/${idea.id}"><c:out
								value="${idea.title}" /></a></td>
					<td><c:out value="${idea.createdBy}" /></td>
					<td><c:out value="${idea.likes}" /></td>
					
					<c:if test="${! idea.users.contains(user)}">
						<td><a href="/ideas/${idea.id}/like"> Like</a></td>
					</c:if>
					<c:if test="${idea.users.contains(user)}">
						<td><a href="/ideas/${idea.id}/Unlike">Unlike</a></td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/ideas/new" class="btn btn-lg btn-primary" >New Idea</a>
	</div>
</body>
</html>