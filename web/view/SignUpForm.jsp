<%--
  Created by IntelliJ IDEA.
  User: aayyad
  Date: 3/18/19
  Time: 10:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="common/Header.html"/>

<div class="container">
    <h1>Sign up</h1>
        <form method="post">
            <input type="text" placeholder="username" name="username" class="form-control"/>
            <input type="password" placeholder="password" name="password" class="form-control"/>
            <input type="password" placeholder="confirm password" name="confirm password" class="form-control"/>
            <input type="email" placeholder="email" name="email" class="form-control"/>
            <button type="submit" class="btn btn-primary">submit</button>
        </form>
</div>
<jsp:include page="common/Footer.html"/>