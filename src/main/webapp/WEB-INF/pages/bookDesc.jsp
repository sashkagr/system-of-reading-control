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
        <a href="?command=manage_book&key=${book.id}">
            <button class="add-button">
                <c:choose>
                    <c:when test="${requestScope.isAdded}">
                        Remove from my library
                    </c:when>
                    <c:otherwise>
                        Add to my library
                    </c:otherwise>
                </c:choose>
            </button>
        </a>
        <c:if test="${requestScope.isAdded}">
            <form method="get" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" id="command" name="command" value="manage_book"/>
                <input type="hidden" id="edit" name="edit" value="true"/>
                <input type="hidden" id="key" name="key" value="${requestScope.book.id}" />

                <label for="startDate">Start date:</label>
                <input type="date" id="startDate" name="startDate"value="${requestScope.book.startDate}" />

                <label for="endDate">End date:</label>
                <input type="date" id="endDate" name="endDate" value="${requestScope.book.endDate}" />

                <label for="finishedCheckbox">Book finished:</label>
                <input type="checkbox" id="finishedCheckbox" name="finishedCheckbox" <c:if test="${requestScope.book.finished}">checked</c:if>/>

                <input type="submit" value="Update"/>
            </form>
        </c:if>
        <c:out value="${requestScope.errorMessage}" />
</div>
</body>
</html>