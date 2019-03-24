<%@ page import="com.atypon.training.litratum.mvc.model.database.User" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: aayyad
  Date: 3/24/19
  Time: 10:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% List<User> users = (List<User>) request.getAttribute("users");%>
<html>
<head>
    <title>Litratum Admin</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<jsp:include page="AdminNabBar.html"/>

<div class="container">
    <table class="table">
        <thead>
        <th>
            #
        </th>
        <th>
            user name
        </th>
        <th>
            email
        </th>
        </thead>
        <tbody>
        <% for (int i = 0; i < users.size(); ++i) {%>
        <tr>
            <td>
                <%= i+1 %>
            </td>
            <td>
                <%= users.get(i).getUsername()%>
            </td>
            <td>
                <%= users.get(i).getEmail()%>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

<jsp:include page="Footer.html"/>