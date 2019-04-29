<%--
  Created by IntelliJ IDEA.
  User: abday
  Date: 4/29/2019
  Time: 8:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/Header.html"/>
<jsp:include page="AdminNavBar.html"/>
<div class="container">
    <form method="post" action="/admin/sign-in">
        <input type="text" class="form-control sign-form" name="adminEmail" placeholder="Email"/>
        <input type="password" class="form-control sign-form" name="adminPassword" placeholder="Password"/>
        <button type="submit" class="btn btn-primary">Sign In</button>
    </form>
</div>
<jsp:include page="../common/Footer.html"/>