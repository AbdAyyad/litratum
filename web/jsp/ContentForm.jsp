<%--
  Created by IntelliJ IDEA.
  User: aayyad
  Date: 3/31/19
  Time: 11:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="Header.html"/>
<jsp:include page="AdminNabBar.html"/>

<div class="container">
    <form method="post" action="http://localhost:8080/front/backstage">
        <input type="file" name="content">
        <button type="submit" class="btn btn-primary">submit</button>
    </form>
</div>

<jsp:include page="Footer.html"/>