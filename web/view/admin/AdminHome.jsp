<%--
  Created by IntelliJ IDEA.
  User: abday
  Date: 4/29/2019
  Time: 8:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/Header.html"/>
<jsp:include page="AdminSiteNavBar.html"/>
<div class="container">
    <form method="post" class="left-thirty" action="/admin/sign-in/">
        <input type="email" id="admin-email" class="form-control sign-form admin-form-width" name="adminEmail" placeholder="Email"/>
        <input type="password" class="form-control sign-form admin-form-width" name="adminPassword" placeholder="Password"/>
        <button type="submit" class="btn btn-primary left-thirty">Sign In</button>
    </form>
</div>
<jsp:include page="../common/Footer.html"/>