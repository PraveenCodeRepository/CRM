 <!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Send Custom Email</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h2 {
            color: #333;
            text-align: center;
        }
        form {
            width: 50%;
            margin: 0 auto;
        }
        input, textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
        }
        .button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }
        .button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>Send Email to Student</h2>
    <form action="<%=request.getContextPath()%>/auth/sendEmail" method="post">
        <input type="hidden" name="email" value="${email}" />
        <label for="subject">Subject:</label>
        <input type="text" name="subject" id="subject" required />

        <label for="message">Message:</label>
        <textarea name="message" id="message" rows="10" required></textarea>

        <button type="submit" class="button">Send Email</button>
    </form>
</body>
</html>
 