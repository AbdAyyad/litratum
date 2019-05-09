<%--
  Created by IntelliJ IDEA.
  User: abday
  Date: 5/8/2019
  Time: 5:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/Header.html"/>
<jsp:include page="AdminNavBar.jsp"/>

<div class="container top-ten-percent">
    <label class="left-thirty"><input type="radio" name="optradio" value="admin" checked onclick="showCountForm()">Count</label>
    <label class="left-twenty-px"><input type="radio" name="optradio" value="backstage" onclick="showSubForm()">Subscription</label>
    <br/>
    <form id="count" class="left-thirty" action="/admin/user/count/" method="post">
        <input type="hidden" name="userId" value="${updatedUserId}"/>
        <input type="text" name="count" class="form-control admin-form-width sign-form" placeholder="number of access times"/>
        <button type="submit" class="btn btn-primary">update</button>
    </form>
    <form id="sub" class="left-thirty" action="/admin/user/sub/" method="post" hidden>
        <input type="hidden" name="userId" value="${updatedUserId}"/>
        <input type="date" class="form-control admin-form-width sign-form" name="date"/>
        <button type="submit" class="btn btn-primary">update</button>
    </form>
</div>

<jsp:include page="../common/Footer.html"/>