<%@ page import="java.util.List" %>
<%@ page import="com.atypon.training.litratum.mvc.model.database.UnprocessedContent" %><%--
  Created by IntelliJ IDEA.
  User: aayyad
  Date: 4/8/19
  Time: 10:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/Header.html"/>
<jsp:include page="BackstageNavBar.html"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% List<Object> content = (List<Object>) request.getAttribute("datalist");%>

<div class="container">
    <table class="table">
        <thead>
        <th>
            #
        </th>
        <th>
            file name
        </th>
        <th>
            status
        </th>
        <th>

        </th>
        </thead>
        <tbody>
        <% for (int i = 0; i < content.size(); ++i) {%>
        <tr>
            <td>
                <%= i + 1 %>
            </td>
            <td>
                <%= ((UnprocessedContent) content.get(i)).getFileName()%>
            </td>
            <td>
                not processed
            </td>
            <td>
                <form method="post">
                    <input licenseType="hidden" value="<%= ((UnprocessedContent) content.get(i)).getId()%>" name="userId">
                    <button licenseType="submit" class="btn btn-primary">process</button>
                </form>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

<jsp:include page="../common/Footer.html"/>