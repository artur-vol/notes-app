<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Application error</title>

    <style>
        body {
            max-width: 700px;
            margin: 40px auto;
            padding: 0 20px;
            font-family: Arial, sans-serif;
        }

        .error-box {
            border: 1px solid #b00020;
            border-radius: 8px;
            padding: 20px;
        }

        a {
            display: inline-block;
            margin-top: 16px;
            padding: 8px 12px;
            border: 1px solid #555;
            border-radius: 5px;
            color: black;
            text-decoration: none;
        }
    </style>
</head>

<body>
<div class="error-box">
    <h1>Something went wrong</h1>

    <p>
        The application could not complete your request.
        Please try again later.
    </p>

    <c:if test="${not empty errorMessage}">
        <p>
            <c:out value="${errorMessage}" />
        </p>
    </c:if>

    <a href="${pageContext.request.contextPath}/notes">
        Back to notes
    </a>
</div>
</body>
</html>
