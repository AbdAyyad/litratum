<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.FileReader" %>
<%--
  Created by IntelliJ IDEA.
  User: abday
  Date: 5/7/2019
  Time: 4:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/Header.html"/>
<jsp:include page="UserNavBar.jsp"/>
<%
    String path = (String) session.getAttribute("article");
    BufferedReader reader = new BufferedReader(new FileReader(path));
    StringBuilder output = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
        output.append(line);
    }
%>
<%=output.toString()%>
<jsp:include page="../common/Footer.html"/>