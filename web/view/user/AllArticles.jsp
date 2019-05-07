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

    <table class="table">
        <thead>
        <tr>
            <th>
                #
            </th>
            <th>
                Title
            </th>
            <th>
                Date
            </th>
            <th>
                Author
            </th>
            <th>
                view
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach begin="0" var="i" end="${articles.size() -1}">
            <tr>
                <td>
                        ${i+1}
                </td>
                <td>
                        ${articles.get(i).getTitle()}
                </td>
                <td>
                        ${articles.get(i).getReleaseDate()}
                </td>
                <td>
                        ${articles.get(i).getAuthor()}
                </td>
                <td>
                    <form method="post" action="/show/article/">
                        <input type="hidden" name="doi" value="${articles.get(i).getDoi()}">
                        <button type="submit" class="btn btn-primary">view</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="../common/Footer.html"/>