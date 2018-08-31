<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.codetodo.courseapp.model.Course"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Home</title>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

	<div class="container">
		<h2>Courses</h2>

		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Title</th>
					<th>Nivel</th>
					<th>Horas</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${courses}" var="course">
					<tr>
						<td>${course.title}</td>
						<td>${course.level}</td>
						<td>${course.hours}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<a href="courses/add" class="btn btn-primary">Nuevo</a>

	</div>

</body>
</html>
