<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quiz</title>
</head>
<body>

<c:set var="i" value="${1}"/>
<form action="SubmitQuizServlet" method="post">
    <c:forEach items="${questions}" var="question">
        <%@ include file="WEB-INF/singleQuestion.jspf" %>
    </c:forEach>
    <input type="submit" value="Submit test"/>
</form>
<c:set var="questions" scope="session" value="${questions}"/>

</body>
</html>
