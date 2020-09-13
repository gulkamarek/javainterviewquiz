<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style><%@include file="/WEB-INF/style.css"%></style>
    <title>Results</title>
</head>
<body>
<h1>Your Score is ${sessionScope.quizResult.score} %!</h1>
<c:set var="i" value="${1}"/>
<form action="index.jsp">
    <c:forEach items="${questions}" var="question">
        <%@ include file="WEB-INF/singleQuestionResult.jspf" %>
    </c:forEach>
    <input type="submit" value="Back to main Page"/>
</form>
<c:set var="questions" scope="session" value="${questions}"/>

</body>
</html>
