<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }

        .book {
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 20px;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            display: flex;
            align-items: center; /* Center vertically within the flex container */
        }

        .book img {
            max-width: 150px; /* Adjust the width as needed */
            margin-right: 20px;
        }

        .book-details {
            flex-grow: 1;
        }

        .add-button {
            margin-top: 10px; /* Adjust the top margin as needed */
        }
    </style>
</head>
<body>
<jsp:include page="common/navbar.jsp"></jsp:include>
<div class="container mt-3">
        <div class="book">
            <img src="${requestScope.book.cover}" alt="${requestScope.book.title} cover"/>
            <div class="book-details">
                <h2><c:out value="${requestScope.book.title}"/></h2>
                <p><strong>Author:</strong> <c:out value="${requestScope.book.author}"/></p>
                <p><strong>Description:</strong> <c:out value="${requestScope.book.description}"/>  </p>
            </div>
        </div>

</div>
</body>
</html>