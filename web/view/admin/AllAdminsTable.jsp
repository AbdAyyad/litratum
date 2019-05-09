<%--
  Created by IntelliJ IDEA.
  User: abday
  Date: 5/9/2019
  Time: 4:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container top-ten-px">
    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Email</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach begin="0" end="${admins.size()-1}" var="idx">
            <tr>
                <td>${idx + 1}</td>
                <td>${admins.get(idx).getUserName()}</td>
                <td>${admins.get(idx).getUserEmail()}</td>
                <td>
                    <form method="post" action="/admin/admin/delete/">
                        <input type="hidden" name="userId" value="${admins.get(idx).getUserId()}"/>
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
