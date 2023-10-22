<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="common/navbar.jsp"></jsp:include>
<c:forEach var="book" items="${requestScope.books}">
    <div> <c:out value="${book.title}"/> </div>
    <div> <c:out value="${book.author}"/> </div>
    <div> <c:out value="${book.description}"/> </div>
    <img src="${book.cover}"/>
    <hr/>
</c:forEach>

</body>
</html>
