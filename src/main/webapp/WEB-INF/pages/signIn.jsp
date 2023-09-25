<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="common/navbar.jsp"></jsp:include>
<div class="container mt-3">
    <h2>Sign In</h2>
    <c:if test="${not empty loginError}">
        <div class="alert alert-danger alert-dismissible fade show">
            <strong>Error!</strong> Incorrect login or password.
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>
    <form action="${pageContext.request.contextPath}/controller?command=login" method="post">
        <div class="mb-3 mt-3">
            <label for="login">Login:</label>
            <input type="text" class="form-control" id="login" placeholder="Enter login" name="login" required>
        </div>
        <div class="mb-3">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password" required>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
