<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <link rel="stylesheet" href="<c:url value='/resources/css/styles.css' />">
    <title>Task</title>
</head>
<body>
<main class="page">
    <section class="panel">
        <div class="page-header">
            <h1><c:out value="${task.name}" /></h1>
            <a class="button secondary" href="<c:url value='/tasks' />">Back to all tasks</a>
        </div>

        <div class="table-wrap">
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Priority</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><c:out value="${task.id}" /></td>
                    <td><c:out value="${task.name}" /></td>
                    <td><c:out value="${task.priority}" /></td>
                    <td>
                        <c:choose>
                            <c:when test="${task.completed}">
                                <span class="status completed">Completed</span>
                            </c:when>
                            <c:otherwise>
                                <span class="status pending">Not completed</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </section>
</main>
</body>
</html>
