<%--
  Created by IntelliJ IDEA.
  User: luiryna
  Date: 16.07.2020
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Card List</title>
</head>
<body>
<h3>Card List</h3>

<p style="color: red;">${errorString}</p>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>User ID</th>
        <th>Account ID</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${cardList}" var="card" >
        <tr>
            <td>${card.id}</td>
            <td>${card.name}</td>
            <td>${card.userId}</td>
            <td>${card.accountId}</td>
            <td>
                <a href="editProduct?code=${card.id}">Edit</a>
            </td>
            <td>
                <a href="deleteProduct?code=${card.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="createProduct" >Add Card</a>

<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
