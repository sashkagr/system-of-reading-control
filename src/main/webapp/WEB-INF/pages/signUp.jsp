<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<jsp:include page="common/navbar.jsp"></jsp:include>
<div class="container mt-3">
    <h2>Sign Up</h2>
    <c:if test="${not empty signupError}">
        <div class="alert alert-danger alert-dismissible fade show">
            <strong>Error!</strong>
            <c:choose>
                <c:when test="${signupError eq 'login'}">
                    Login already taken
                </c:when>
                <c:when test="${signupError eq 'password'}">
                    Login or password doesn't match requirements
                </c:when>
                <c:otherwise></c:otherwise>
            </c:choose>
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>

    </c:if>
    <form action="${pageContext.request.contextPath}/controller?command=signup" method="post">
        <div class="mb-3 mt-3">
            <label for="login">Login:</label>
            <input type="text" class="form-control" id="login" placeholder="Only letters (either case), numbers, and the underscore, no more than 15 characters" name="login" pattern="[A-Za-z0-9_]{1,15}">
        </div>
        <div class="mb-3">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="pwd" placeholder="At least 6 but no more than 30 characters" name="password" pattern=".{6,30}">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
