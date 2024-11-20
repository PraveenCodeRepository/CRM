<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Success</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            text-align: center;
        }
        .container {
            max-width: 600px;
            margin: auto;
            padding: 50px;
            border: 2px solid #28a745;
            border-radius: 10px;
            background-color: #f8f9fa;
        }
        .message {
            font-size: 24px;
            color: #28a745;
            margin-bottom: 20px;
        }
        .button {
            padding: 10px 20px;
            font-size: 16px;
            color: white;
            background-color: #007BFF;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 20px;
        }
        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="message">
            Details updated successfully!
        </div>
        <form action="<%= request.getContextPath() %>/auth/educationalcrm" method="get">
            <button class="button" type="submit">Go to Home</button>
        </form>
    </div>
</body>
</html>
