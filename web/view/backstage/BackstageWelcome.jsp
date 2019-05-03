<%--
  Created by IntelliJ IDEA.
  User: abday
  Date: 5/2/2019
  Time: 6:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/Header.html"/>
<jsp:include page="BackstageWelcomeNavBar.jsp"/>
<div class="container">
    <form class="left-thirty" action="/backstage/sign-in/" method="post">
        <input class="form-control sign-form admin-form-width top-ten-px" type="email" name="backstageEmail" placeholder="Email">
        <input class="form-control sign-form admin-form-width" type="password" name="backstagePassword" placeholder="Password">
        <button type="submit" class="btn btn-primary left-thirty">sign in</button>
    </form>
</div>
<jsp:include page="../common/Footer.html"/>