<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title><c:out value="${idea.title}"></c:out></title>
</head>
<body>
	<div style="    margin: 0px auto;
    width: 1200px;
    text-align: center;
    padding-top: 100px;
    text-align: left;">
	<h1>
		<c:out value="${idea.title}" />
	</h1>
	<h2 class="h3 mb-3 font-weight-normal">
		Created By:
		<c:out value="${idea.createdBy}" />
	</h2>
	<h1>Users who liked this idea:</h1>
	<table class="table table-striped ">
		<thead class="thead-dark">
			<tr>
				<th>Name</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${idea.users}" var="u">
				<tr>
					<td><c:out value="${u.name}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:if test="${ idea.createdBy.equals(user.name)}">
		<a href="/ideas/${idea.id}/edit" class="btn btn-sm btn-primary">Edit</a>
	</c:if>
	</div>
</body>
</html>