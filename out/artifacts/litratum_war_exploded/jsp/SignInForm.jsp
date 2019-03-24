<%--
  Created by IntelliJ IDEA.
  User: abday
  Date: 3/18/2019
  Time: 8:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="Header.html"/>

<div class="container">
    <h1>Welcome to Litratum</h1>
    <form method="post">
        <input class="form-control" name="username" type="text" placeholder="username"/>
        <input class="form-control" name="password" type="password" placeholder="password"/>
        <button type="submit" class="btn btn-primary">sign in</button>
    </form>

</div>
<jsp:include page="Footer.html"/>