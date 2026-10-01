<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <link rel="stylesheet" href="<c:url value='/resources/css/styles.css' />">
    <title>Task Not Found</title>
</head>
<body>
<main class="page">
    <section class="panel">
        <div class="page-header">
            <h1>Task Not Found</h1>
            <a class="button secondary" href="<c:url value='/tasks' />">Back to all tasks</a>
        </div>

        <p class="empty-state">
            <strong>#<c:out value="${message}" /></strong>
        </p>
    </section>
</main>
</body>
</html>
