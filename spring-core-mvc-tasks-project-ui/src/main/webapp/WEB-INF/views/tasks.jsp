<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <link rel="stylesheet" href="<c:url value='/resources/css/styles.css' />">
    <title>Tasks</title>
</head>
<body>
<main class="page">
    <section class="panel">
        <div class="page-header">
            <h1>Tasks</h1>
            <a class="button" href="<c:url value='/tasks/new' />">Add new task</a>
        </div>

        <c:if test="${not empty priority}">
            <p class="notice">Showing priority: <c:out value="${priority}" /></p>
        </c:if>

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
                <c:forEach var="task" items="${tasks}">
                    <tr>
                        <td><c:out value="${task.id}" /></td>
                        <td>
                            <a href="<c:url value='/tasks/${task.id}' />">
                                <c:out value="${task.name}" />
                            </a>
                        </td>
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
                </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
</main>
</body>
</html>
