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
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.js"></script>

<!-- Latest compiled and minified Locales -->
<script
	src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/locale/bootstrap-table-zh-CN.min.js"></script>

</head>

</head>
<body>

	<div class="container">


		<div class="page-header">
			<h1>Catálogo de Cursos</h1>
		</div>

		<c:if test="${not  empty createdCourse}">
			<div class="alert alert-success alert-dismissible">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				El curso '<strong>${createdCourse.title}</strong>' se ha creado
				correctamente.
			</div>
		</c:if>

		<div class="row">
			<div class="col-sm-12">


				<div class="row">
					<div class="col-sm-12 mb-2">
						<a href="${pageContext.request.contextPath}/courses/add"
							class="btn btn-primary">Nuevo</a>
					</div>
				</div>
<br>
				<div class="row">
					<div class="col-sm-12">
						<table id="coursesTable" class="table table-bordered">
							<thead>
								<tr>
									<th data-field="title" data-sortable="true" onclick="sortCourses()">Title</th>
									<th>Profesor</th>
									<th>Nivel</th>
									<th>Horas</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${courses}" var="course">
									<tr>
										<td><a href="#">${course.title}</a></td>
										<td>${course.professor.name}</td>
										<td>${course.level.text}</td>
										<td>${course.hours}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
								
				
				

			</div>
		</div>

	</div>


</body>
</html>
