<%--
  Created by IntelliJ IDEA.
  User: aayyad
  Date: 5/8/19
  Time: 1:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
