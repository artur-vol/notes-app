<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Notes</title>

    <style>
        body {
            max-width: 900px;
            margin: 40px auto;
            padding: 0 20px;
            font-family: Arial, sans-serif;
        }

        header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 24px;
        }

        .note {
            border: 1px solid #ccc;
            border-radius: 8px;
            padding: 16px;
            margin-bottom: 16px;
        }

        .note-content {
            white-space: pre-wrap;
        }

        .actions {
            display: flex;
            gap: 8px;
            margin-top: 12px;
        }

        a,
        button {
            padding: 8px 12px;
            border: 1px solid #555;
            border-radius: 5px;
            background: white;
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        form {
            margin: 0;
        }

        .empty-message {
            color: #555;
        }
    </style>
</head>

<body>
<header>
    <h1>Notes</h1>

    <a href="${pageContext.request.contextPath}/notes/new">
        Create note
    </a>
</header>

<c:choose>
    <c:when test="${empty notes}">
        <p class="empty-message">No notes yet.</p>
    </c:when>

    <c:otherwise>
        <c:forEach var="note" items="${notes}">
            <article class="note">
                <h2>
                    <c:out value="${note.title}" />
                </h2>

                <p class="note-content">
                    <c:out value="${note.content}" />
                </p>

                <small>
                    Created:
                    <c:out value="${note.createdAt}" />
                </small>

                <div class="actions">
                    <a href="${pageContext.request.contextPath}/notes/edit?id=${note.id}">
                        Edit
                    </a>

                    <form method="post"
                          action="${pageContext.request.contextPath}/notes/delete">

                        <input type="hidden" name="id" value="${note.id}">

                        <button type="submit">
                            Delete
                        </button>
                    </form>
                </div>
            </article>
        </c:forEach>
    </c:otherwise>
</c:choose>
</body>
</html>
