<%--
  Created by IntelliJ IDEA.
  User: aayyad
  Date: 4/22/19
  Time: 12:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/Header.html"/>
<jsp:include page="UserNavBar.jsp"/>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>
                attribute
            </th>
            <th>
                value
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                Name
            </td>
            <td>
                ${user.getUserName()}
            </td>
        </tr>
        <tr>
            <td>
                Email
            </td>
            <td>
                ${user.getUserEmail()}
            </td>
        </tr>
        <tr>
            <td>
                License
            </td>
            <td>
                ${license}
            </td>
        </tr>
        </tbody>
    </table>
</div>

<jsp:include page="../common/Footer.html"/>
