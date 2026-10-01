<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html>
<head>
    <link rel="stylesheet" href="<c:url value='/resources/css/styles.css' />">
    <title>New Task</title>
</head>
<body>
<main class="page">
    <section class="panel">
        <div class="page-header">
            <h1>New Task</h1>
            <a class="button secondary" href="<c:url value='/tasks' />">Back to all tasks</a>
        </div>

        <form:form cssClass="task-form" modelAttribute="task" action="${pageContext.request.contextPath}/tasks" method="post">
            <div class="field">
                <form:label path="name">Name</form:label>
                <form:input path="name"/>
                <form:errors path="name" cssClass="error"/>
            </div>

            <div class="field">
                <form:label path="priority">Priority</form:label>
                <form:select path="priority">
                    <form:option value="LOW" label="LOW"/>
                    <form:option value="MEDIUM" label="MEDIUM"/>
                    <form:option value="HIGH" label="HIGH"/>
                </form:select>
                <form:errors path="priority" cssClass="error"/>
            </div>

            <form:label cssClass="checkbox-label" path="completed">
                <form:checkbox path="completed"/>
                Completed
            </form:label>

            <div class="form-actions">
                <button type="submit">Create Task</button>
            </div>
        </form:form>
    </section>
</main>
</body>
</html>
