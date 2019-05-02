<%--
  Created by IntelliJ IDEA.
  User: abday
  Date: 5/2/2019
  Time: 7:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/backstage">Litratum Backstage</a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <%--            <li class="nav-item">--%>
            <%--                <a class="nav-link" href="/admin/content/">content</a>--%>
            <%--            </li>--%>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <form method="post" action="/backstage/sign-out/">
                    <button type="submit" class="btn btn-outline-danger">Sign out</button>
                </form>
            </li>
        </ul>
    </div>
</nav>