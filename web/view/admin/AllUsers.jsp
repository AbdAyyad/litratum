<%--
  Created by IntelliJ IDEA.
  User: aayyad
  Date: 5/8/19
  Time: 12:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../common/Header.html"/>
<jsp:include page="AdminNavBar.jsp"/>
<div class="container top-ten-px">
    <table class="table">
        <thead>
        <tr>
            <th>user name</th>
            <th>email</th>
            <th>license</th>
            <th>change licence</th>
            <th>delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach begin="0" end="${license.size()-1}" var="idx">
            <tr>
                <td>${users.get(idx).getUserEmail()}</td>
                <td>${users.get(idx).getUserName()}</td>
                <td>${license.get(idx)}</td>
                <td>
                    <form method="post" action="/admin/user/">
                        <input type="hidden" name="userId" value="${users.get(idx).getUserId()}">
                        <button type="submit" class="btn btn-primary">update</button>
                    </form>
                </td>
                <td>
                    <form method="post" action="/admin/user/delete/">
                        <input type="hidden" name="userId" value="${users.get(idx).getUserId()}">
                        <input type="hidden" name="email" value="${users.get(idx).getUserEmail()}">
                        <button type="submit" class="btn btn-danger">delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="../common/Footer.html"/>
