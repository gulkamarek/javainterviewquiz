<%--
  Created by IntelliJ IDEA.
  User: A687971
  Date: 9/15/2020
  Time: 11:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Questions upload</title>
</head>
<body>
<form action="QuestionsUploadServlet" method="post">
    Password <input type="password" name="uploadPassword">
    <input type="submit" value="reload questions from csv">
</form>
<br><br>

<form action="index.jsp">
    <input type="submit" value="Go back to main page"/>
</form>

</body>
</html>
