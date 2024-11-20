<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Results</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 20px;
}

h2 {
	color: #333;
	text-align: center;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}

th, td {
	padding: 12px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #4CAF50;
	color: white;
}

tr:hover {
	background-color: #f5f5f5;
}

p {
	text-align: center;
	color: #ff0000;
	font-weight: bold;
}

.button {
	display: block;
	width: 200px;
	margin: 0 auto;
	padding: 10px 20px;
	background-color: #4CAF50;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 16px;
}

.button:hover {
	background-color: #45a049;
}

form {
	text-align: center;
}
</style>
</head>
<body>
	<h2>Student Search Results</h2>
	<c:if test="${not empty students}">
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Phone</th>
					<th>Courses</th>
					<th>HR Name</th>
					<th>Stage</th>
					<th>Email</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="student" items="${students}">
					<tr>
						<td><c:out value="${student.id}" /></td>
						<td><c:out value="${student.name}" /></td>
						<td><c:out value="${student.phone}" /></td>
						<td><c:out value="${student.courses}" /></td>
						<td><c:out value="${student.hr}" /></td>
						<td><c:out value="${student.stage}" /></td>
						<td><c:out value="${student.email}" /></td>
						<td>
							<form action="<%=request.getContextPath()%>/auth/student/update"
								method="get">
								<input type="hidden" name="id" value="${student.id}" />
								<!-- Other form fields for student details -->
								<button type="submit" class="button">Update</button>
							</form>

							<form action="/auth/student/delete/${student.id}" method="get"
								style="display: inline;">
								<button type="submit" class="button"
									style="background-color: #f44336;">Delete</button>
							</form> <!-- Send Email Button -->
							<form
								action="<%=request.getContextPath()%>/auth/emailPage"
								method="get">
								<input type="hidden" name="email" value="${student.email}" />
								<button type="submit" class="button"
									style="background-color: #2196F3;">Send Email</button>
							</form>

						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		 <!-- Download Button -->
        <form action="<%=request.getContextPath()%>/auth/downloadFilteredStudents" method="get">
            <input type="hidden" name="searchBy" value="${searchBy}" />
            <input type="hidden" name="searchValue" value="${searchValue}" />
            <button type="submit" class="button" style="background-color: #2196F3;">Download Filtered Details (Excel)</button>
        </form>
	</c:if>
	<c:if test="${empty students}">
		<p>No students found.</p>
	</c:if>

	<!-- Redirect Button -->
	<form action="<%=request.getContextPath()%>/auth/educationalcrm"
		method="get">
		<button type="submit" class="button">Go to Home Page</button>
	</form>
</body>
</html>
