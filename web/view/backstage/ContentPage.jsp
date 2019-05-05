<%--
  Created by IntelliJ IDEA.
  User: abday
  Date: 5/2/2019
  Time: 7:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../common/Header.html"/>
<jsp:include page="BackstageNavBar.jsp"/>
<div class="container">
    <table class="table top-ten-percent">
        <thead>
        <tr>
            <th>
                file name
            </th>
            <th>
                status
            </th>
            <th>
                re/process
            </th>
            <th>
                delete
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${unprocessedContents}" var="content">
            <tr>
                <td>${content.getFileName()}</td>
                <td>${content.getStatus()}</td>
                <td>
                    <form method="post" action="/backstage/content/process/">
                        <input type="hidden" name="unprocessedContentId" value="${content.getUnprocessedContentId()}"/>
                        <button type="submit" class="btn btn-primary">process</button>
                    </form>
                </td>
                <td>
                    <form method="post" action="/backstage/content/delete/">
                        <input type="hidden" name="unprocessedContentId" value="${content.getUnprocessedContentId()}"/>
                        <button type="submit" class="btn btn-danger">delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="../common/Footer.html"/>