<%@ page import="java.util.List"%>
<%@ page import="com.codetodo.courseapp.model.Course"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<body>
	<h2>Courses</h2>

	<ul>
		<c:forEach items="${courses}" var="course">
			<li>${course.title}</li>
		</c:forEach>
	</ul>

</body>
</html>
