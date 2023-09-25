<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="common/navbar.jsp"></jsp:include>
<div class="container mt-3">
    <h2>Main Page</h2>
    <c:if test="${not empty sessionScope.login}">
        Welcome, ${sessionScope.login}
    </c:if>
</div>
</body>
</html>