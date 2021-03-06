<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Question</title>
</head>
<body>
<h1>Edit Question</h1>
<form action="EditQuestionServlet" method="post">
    <br>Question: <input type="text" name="question" value="${question.question}"/>
    <c:set var="i" value="1"></c:set>
    <c:forEach items="${question.answers}" var="answer">
        <br>Answer${i}: <input type="text" name="answ${answer.id}" id="answ${answer.id}" value="${answer.answer}"/>
        <input type="checkbox" name="isTrueAnsw${answer.id}" id="isTrueAnsw${answer.id}"
               <c:if test="${answer.isCorrect == 'true'}">checked</c:if>>
        <label for="isTrueAnsw${answer.id}">is true</label>
        <c:set var="i" value="${i+1}"></c:set>
    </c:forEach>
    <br><input type="submit" value="Submit change">
<%--    <jsp:useBean id="editedQuesiton" class="com.jackwise.model.Question" scope="request">--%>
<%--        <jsp:s--%>
<%--    </jsp:useBean>--%>
    <input type="hidden" name="questionId" value="${question.id}">
</form>
<br><br>

<form action="index.jsp">
    <input type="submit" value="Go back to main page"/>
</form>
<br><br>

<form action="StartQuizServlet" method="get">
    <input type="hidden" name="questionsNumber" value="0">
    <input type="submit" value="Back to questions list">
</form>

</body>
</html>
