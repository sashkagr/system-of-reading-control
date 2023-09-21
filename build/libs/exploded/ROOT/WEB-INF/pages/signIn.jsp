<%--
  Created by IntelliJ IDEA.
  User: sashk
  Date: 21.09.2023
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="common/navbar.jsp"></jsp:include>
<div class="container mt-3">
    <h2>Sign In</h2>
    <form action="">
        <div class="mb-3 mt-3">
            <label for="login">Login:</label>
            <input type="text" class="form-control" id="login" placeholder="Enter login" name="login">
        </div>
        <div class="mb-3">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
