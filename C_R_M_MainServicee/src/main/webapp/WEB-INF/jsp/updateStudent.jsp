<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Student</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .container {
            max-width: 800px;
            margin: auto;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input, .form-group select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .button {
            padding: 10px 20px;
            font-size: 16px;
            color: white;
            background-color: #28a745;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Update Student</h1>

        <!-- Form to update student details -->
        <form action="<%= request.getContextPath() %>/auth/student/update" method="post">
            <div class="form-group">
                <label for="id">Student ID</label>
                <input type="text" id="id" name="id" value="${student.id}" readonly>
            </div>
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" value="${student.name}" placeholder="Enter name">
            </div>
            <div class="form-group">
                <label for="degree">Degree</label>
                <input type="text" id="degree" name="degree" value="${student.degree}" placeholder="Enter degree">
            </div>
            <div class="form-group">
                <label for="phone">Phone</label>
                <input type="text" id="phone" name="phone" value="${student.phone}" placeholder="Enter phone number">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" value="${student.email}" placeholder="Enter email">
            </div>
            <div class="form-group">
                <label for="courses">Courses</label>
                <select id="courses" name="courses">
                    <option value="java" ${student.courses == 'java' ? 'selected' : ''}>Java</option>
                    <option value="python" ${student.courses == 'python' ? 'selected' : ''}>Python</option>
                </select>
            </div>
            <div class="form-group">
                <label for="hr">HR Name</label>
                <select id="hr" name="hr">
                    <option value="subha" ${student.hr == 'subha' ? 'selected' : ''}>Subha</option>
                    <option value="liril" ${student.hr == 'liril' ? 'selected' : ''}>Liril</option>
                    <option value="karthika" ${student.hr == 'karthika' ? 'selected' : ''}>Karthika</option>
                </select>
            </div>
            <div class="form-group">
                <label for="stage">Stage</label>
                <select id="stage" name="stage">
                    <option value="counselling" ${student.stage == 'counselling' ? 'selected' : ''}>Counselling</option>
                    <option value="application" ${student.stage == 'application' ? 'selected' : ''}>Application</option>
                    <option value="admission" ${student.stage == 'admission' ? 'selected' : ''}>Admission</option>
                </select>
            </div>

            <div class="form-group">
                <input type="submit" value="Update" class="button">
            </div>
        </form>

        <!-- Display success message -->
        <c:if test="${not empty successMessage}">
            <div style="color:green;">${successMessage}</div>
        </c:if>

        <!-- Display error message -->
        <c:if test="${not empty errorMessage}">
            <div style="color:red;">${errorMessage}</div>
        </c:if>
    </div>
</body>
</html>
