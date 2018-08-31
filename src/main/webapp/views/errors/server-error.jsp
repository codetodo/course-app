<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
<meta charset="ISO-8859-1">
<title>Error</title>
</head>

<body>
	<h1>ERRORRRR FULAAAAAAA!!</h1>
	<c:set var="exception"
		value="${requestScope['javax.servlet.error.exception']}" />

	<jsp:scriptlet>exception.printStackTrace(new java.io.PrintWriter(out));</jsp:scriptlet>
</body>

</html>