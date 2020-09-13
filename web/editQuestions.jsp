<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quiz</title>
</head>
<body>

<c:set var="i" value="${1}"/>

<c:forEach items="${questions}" var="question">
    <%@ include file="WEB-INF/singleQuestionEdit.jspf" %>
</c:forEach>

<form action="index.jsp">
    <input type="submit" value="Go back to main page"/>
</form>

</body>
</html>
