<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sashk
  Date: 21.09.2023
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script></head>
<body>
<nav class="navbar navbar-expand-sm bg-light">
    <div class="container-fluid">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=show_main_page">Home</a>
            </li>
            <c:choose>
                <c:when test="${not empty sessionScope.login}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=logout">Log out</a>
                    </li>
                    <li class="nav-item">
                        <form method="get" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" id="command" name="command" value="search_book"/>
                            <input type="text" id="search_query" name="search_query"/>
                            <input type="submit" value="Search"/>
                        </form>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=SHOW_MY_LIBRARY_PAGE">My Library</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=show_login_page">Sign In</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=show_signup_page">Sign Up</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
</body>
</html>


