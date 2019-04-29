<%--
  Created by IntelliJ IDEA.
  User: abday
  Date: 4/28/2019
  Time: 7:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/home/">Literatum</a>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <form id="home-form" method="post" action="/home/">
                    <input type="hidden" name="userEmail" value="${userEmail}"/>
                    <a class="nav-link" onclick="document.getElementById('home-form').submit();">Home</a>
                </form>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <form id="info-form" method="post" action="/user/info/">
                    <input type="hidden" name="userEmail" value="${userEmail}"/>
                    <a class="nav-link" onclick="document.getElementById('info-form').submit();">Welcome ${userName}</a>
                </form>
            </li>
            <li class="nav-item">
                <form method="post" action="/sign-out/">
                    <input type="hidden" name="userEmail" value="${userEmail}">
                    <button type="submit" class="btn btn-outline-danger">Sign out</button>
                </form>
            </li>
        </ul>
    </div>
</nav>