<%--
  Created by IntelliJ IDEA.
  User: aayyad
  Date: 4/8/19
  Time: 10:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/Header.html"/>
<jsp:include page="AdminNavBar.jsp"/>
<div class="container">
    <h1 class="text-center">content <span class="file-name">"${fileName}"</span> uploaded successfully</h1>
    <h3 class="text-center"> click <a href="/admin/content/">here</a> to upload another one</h3>
</div>
<jsp:include page="../common/Footer.html"/>