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
<div class="container top-ten-percent">
    <table class="table">
        <thead>
        <tr>
            <th>user name</th>
            <th>email</th>
            <th>license</th>
            <th>update</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${license}" begin="0" end="${license.size()-1}" var="idx">
            <tr>
                <td>${license.get(idx).getLicenseId()}</td>
                <td>${idx}</td>
                <td>${idx}</td>
                <td>${idx}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="../common/Footer.html"/>
