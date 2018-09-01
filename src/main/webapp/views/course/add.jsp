<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.codetodo.courseapp.model.Professor"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>

<title>Nuevo Curso</title>
<meta name="viewport" content="width=device-width, initial-scale=1"
	charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link
	href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css"
	rel="stylesheet">
<script
	src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
<body>


	<div class="container" class="mx-auto" style="width: 50%;">

		<div class="row">

			<div class="col-sm-12">
				<div class="page-header">
					<h1>Nuevo Curso</h1>
				</div>

				<form class="form-horizontal"
					action="${pageContext.request.contextPath}/courses" method="POST">

					<div class="form-group">
						<label for="inputEmail" class="control-label col-xs-2">Activo</label>
						<div class="col-xs-10">
							<input type="checkbox" name="active" data-toggle="toggle" data-on="Si"
								data-off="No">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword" class="control-label col-xs-2">Profesor</label>
						<div class="col-xs-10">
							<select id="professorSelect" name="professor" class="form-control" required="true">
								<c:forEach items="${professors}" var="professor">
									<option value="${professor.id}">${professor.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword" class="control-label col-xs-2">Título</label>
						<div class="col-xs-10">
							<input class="form-control" name="title" type="text" required="true">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword" class="control-label col-xs-2">Nivel</label>
						<div class="col-xs-10">
							<select id="levelSelect" name="level" class="form-control" required="true">
								<c:forEach items="${levels}" var="level">
									<option value="${level.id}">${level.text}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label for="inputPassword" class="control-label col-xs-2">Horas</label>
						<div class="col-xs-4">
							<input class="form-control" name="hours" type="text" required="true">
						</div>
					</div>


					<div class="form-group">
						<div class="col-xs-offset-2 col-xs-10">
							<button type="submit" class="btn btn-primary">Aceptar</button>
							<a href="${pageContext.request.contextPath}/courses" class="btn btn-default">Cancelar</a>
						</div>						
					</div>



				</form>
			</div>
		</div>



	</div>

	</div>

</body>

</html>