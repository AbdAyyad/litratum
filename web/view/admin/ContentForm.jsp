<%--
  Created by IntelliJ IDEA.
  User: aayyad
  Date: 3/31/19
  Time: 11:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/Header.html"/>
<jsp:include page="AdminNavBar.jsp"/>

<div class="container">
    <h3 class="text-center" id="upload-articles-here">upload articles here</h3>
    <form method="post" class="left-thirty" action="/admin/content/upload/" enctype="multipart/form-data">
        <input type="hidden" name="adminEmail" value="${adminEmail}"/>
        <input class="sign-form" accept=".zip" type="file" name="content"/>
        <br/>
        <button type="submit" class="btn btn-primary">submit</button>
    </form>
</div>

<jsp:include page="../common/Footer.html"/>