<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><c:out value="${pageTitle}" /></title>

    <style>
        body {
            max-width: 700px;
            margin: 40px auto;
            padding: 0 20px;
            font-family: Arial, sans-serif;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 16px;
        }

        input,
        textarea {
            width: 100%;
            box-sizing: border-box;
            padding: 10px;
            font: inherit;
        }

        textarea {
            min-height: 180px;
            resize: vertical;
        }

        .actions {
            display: flex;
            gap: 10px;
        }

        button,
        a {
            padding: 9px 14px;
            border: 1px solid #555;
            border-radius: 5px;
            background: white;
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        .error {
            color: #b00020;
        }
    </style>
</head>

<body>
<h1><c:out value="${pageTitle}" /></h1>

<c:if test="${not empty error}">
    <p class="error">
        <c:out value="${error}" />
    </p>
</c:if>

<form method="post" action="${formAction}">
    <c:if test="${not empty note}">
        <input type="hidden" name="id" value="${note.id}">
    </c:if>

    <label for="title">Title</label>

    <input
        id="title"
        name="title"
        type="text"
        required
        maxlength="200"
        value="<c:out value='${title}' />">

    <label for="content">Content</label>

    <textarea
        id="content"
        name="content"
        maxlength="4000"><c:out value="${content}" /></textarea>

    <div class="actions">
        <button type="submit">Save</button>

        <a href="${pageContext.request.contextPath}/notes">
            Cancel
        </a>
    </div>
</form>
</body>
</html>
