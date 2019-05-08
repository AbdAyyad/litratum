<%--
  Created by IntelliJ IDEA.
  User: abday
  Date: 5/7/2019
  Time: 4:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../common/Header.html"/>
<jsp:include page="UserNavBar.jsp"/>
<div class="container">
    <c:choose>
        <c:when test="${articles.size() > 0}">
            <jsp:include page="AllArticlesTable.jsp"/>
        </c:when>
        <c:when test="${articles.size() == 0}">
            <h3 class="text-center top-ten-percent">no processed articles</h3>
        </c:when>
    </c:choose>
</div>
<jsp:include page="../common/Footer.html"/>