<%--
  Created by IntelliJ IDEA.
  User: abday
  Date: 4/29/2019
  Time: 4:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/Header.html"/>
<jsp:include page="AdminNavBar.jsp"/>
<div class="container">
    <form method="post" action="/admin/create/">
        <input type="text" class="form-control" name="newAdminName" placeholder="Name"/>
        <input type="email" class="form-control" name="newAdminEmail" placeholder="Email"/>
        <input type="password" class="form-control" name="newAdminPassword" placeholder="Password"/>
        <div class="radio">
            <label><input type="radio" name="optradio" checked>Admin</label>
        </div>
        <div class="radio">
            <label><input type="radio" name="optradio">Backstage</label>
        </div>
        <input type="hidden" name="adminEmail" value="${adminEmail}"/>
        <button type="submit" class="btn btn-outline-primary">submit</button>
    </form>
</div>
<jsp:include page="../common/Footer.html"/>