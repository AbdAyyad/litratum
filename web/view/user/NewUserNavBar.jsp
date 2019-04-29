<%--
  Created by IntelliJ IDEA.
  User: aayyad
  Date: 4/22/19
  Time: 2:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/welcome/">Litratum</a>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
            <jsp:include page="SignUp.html"/>
            <div class="vertical-line"></div>
            <jsp:include page="SignIn.html"/>
        </ul>
    </div>
</nav>
