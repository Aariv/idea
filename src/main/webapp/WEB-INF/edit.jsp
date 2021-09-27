<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>Edit<c:out value="${idea.title}"></c:out></title>
</head>
<body>
	<div style="    margin: 0px auto;
    width: 1200px;
    text-align: center;
    padding-top: 100px;">
	<h1 class="h3 mb-3 font-weight-normal">
		Edit
		<c:out value="${idea.title}" />
	</h1>

	<p>
		<form:errors path="idea.*" />
	</p>
	<form:form method="POST" action="/ideas/${idea.id}/edit"
		modelAttribute="idea">
		<p>
			<form:label path="title">Title:</form:label>
			<form:input type="title" path="title" class="form-control" />
		</p>
		<input type="submit" value="Update" class="btn btn-sm btn-primary " /> <br><br>
	</form:form>
	<form:form method="POST" action="/ideas/${idea.id}/delete">
		<input type="submit" value="Delete" class="btn btn-sm btn-primary " />
	</form:form>
</div>
</body>
</html>