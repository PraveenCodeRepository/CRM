<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .container {
            max-width: 800px;
            margin: auto;
        }
        .button {
            padding: 10px 20px;
            font-size: 16px;
            color: white;
            background-color: #007BFF;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-right: 10px;
        }
        .button:hover {
            background-color: #0056b3;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group select, .form-group input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .form-group input[type="submit"] {
            background-color: #28a745;
            color: white;
            border: none;
            cursor: pointer;
        }
        .form-group input[type="submit"]:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Student Management</h1>
        
        <h2>Search Students</h2>
        <form action="<%= request.getContextPath() %>/auth/student/search" method="get">
            <div class="form-group">
                <label for="searchBy">Search By</label>
                <select id="searchBy" name="searchBy">
                    <option value="name">Name</option>
                    <option value="stage">Stage</option>
                    <option value="phone">Phone</option>
                    <option value="course">Course</option>
                    <option value="hr">HR Name</option>
                </select>
            </div>
            <div class="form-group">
                <label for="searchValue">Search Value</label>
                <input type="text" id="searchValue" name="searchValue" placeholder="Enter search value">
            </div>
            <div class="form-group">
                <input type="submit" value="Search">
            </div>
        </form>

        <h2>Insert Student</h2>
        <form action="<%= request.getContextPath() %>/auth/student/insert" method="post">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" placeholder="Enter name">
            </div>
            <div class="form-group">
                <label for="degree">Degree</label>
                <input type="text" id="degree" name="degree" placeholder="Enter degree">
            </div>
            <div class="form-group">
                <label for="phone">Phone</label>
                <input type="text" id="phone" name="phone" placeholder="Enter phone number">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Enter email">
            </div>
            <div class="form-group">
                <label for="courses">Courses</label>
                <select id="courses" name="courses">
                    <option value="java">Java</option>
                    <option value="python">Python</option>
                </select>
            </div>
            <div class="form-group">
                <label for="hr">HR Name</label>
                <select id="hr" name="hr">
                    <option value="subha">Subha</option>
                    <option value="liril">Liril</option>
                    <option value="karthika">Karthika</option>
                </select>
            </div>
            <div class="form-group">
                <label for="stage">Stage</label>
                <select id="stage" name="stage">
                    <option value="counselling">Counselling</option>
                    <option value="application">Application</option>
                    <option value="admission">Admission</option>
                </select>
            </div>
            <div class="form-group">
                <input type="submit" value="Insert">
            </div>
        </form>

        <!-- New Form for Excel Upload -->
        <h2>Upload Student Data (Excel)</h2>
        <form action="<%= request.getContextPath() %>/auth/student/uploadExcel" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="file">Choose Excel File</label>
                <input type="file" id="file" name="file" accept=".xls,.xlsx" required>
            </div>
            <div class="form-group">
                <input type="submit" value="Upload Excel">
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