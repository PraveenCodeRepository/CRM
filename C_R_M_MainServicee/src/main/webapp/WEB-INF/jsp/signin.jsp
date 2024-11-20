<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CRM - Sign In</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #6e8efb, #a777e3);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            text-align: center;
            color: white;
            margin-bottom: 20px;
        }

        .container h1 {
            font-size: 36px;
            font-weight: bold;
            margin: 0;
        }

        .container p {
            font-size: 18px;
            margin: 5px 0;
            color: #ddd;
        }

        form {
            background-color: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0px 4px 30px rgba(0, 0, 0, 0.2);
            width: 350px;
            animation: fadeIn 1s ease;
        }

        @keyframes fadeIn {
            0% {
                opacity: 0;
                transform: translateY(-20px);
            }
            100% {
                opacity: 1;
                transform: translateY(0);
            }
        }

        h2 {
            text-align: center;
            color: #444;
            font-size: 24px;
            margin-bottom: 20px;
        }

        label {
            font-size: 14px;
            color: #555;
            margin-bottom: 8px;
            display: block;
        }

        input[type="text"], input[type="password"], select {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 8px;
            font-size: 14px;
            transition: all 0.3s ease;
        }

        input[type="text"]:focus, input[type="password"]:focus, select:focus {
            border-color: #6e8efb;
            outline: none;
            box-shadow: 0px 0px 8px rgba(110, 142, 251, 0.5);
        }

        button {
            width: 100%;
            padding: 12px;
            background-color: #6e8efb;
            border: none;
            border-radius: 8px;
            color: white;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #577dd4;
        }

        .alert {
            padding: 12px;
            margin-top: 20px;
            border-radius: 8px;
            font-size: 14px;
            text-align: center;
        }

        .alert-warning {
            background-color: #f0ad4e;
            color: white;
        }

        .alert-danger {
            background-color: #d9534f;
            color: white;
        }

        /* Mobile responsive styling */
        @media (max-width: 768px) {
            form {
                width: 90%;
            }
        }
    </style>
</head>
<body>
    <!-- Container for CRM Heading -->
    <div class="container">
        <h1>CRM</h1>
        <p>Customer Relationship Management</p>
    </div>
    
    <!-- Sign In Form -->
    <form action="${pageContext.request.contextPath}/auth/signin" method="post">
        <h2>Sign In</h2>
        
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" placeholder="Enter your username" required>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" placeholder="Enter your password" required>
        
        <label for="crmType">Choose CRM:</label>
        <select id="crmType" name="crmType">
            <option value="Educational CRM">Educational CRM</option>
            <option value="Healthcare CRM">Healthcare CRM</option>
        </select>
        
        <button type="submit">Sign In</button>
        
        <c:if test="${not empty warning}">
            <div class="alert alert-warning">${warning}</div>
        </c:if>
        
        <c:if test="${not empty danger}">
            <div class="alert alert-danger">${danger}</div>
        </c:if>
    </form>
</body>
</html>
