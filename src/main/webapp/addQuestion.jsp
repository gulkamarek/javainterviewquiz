<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Question</title>
</head>
<body>
<h1>Add Question</h1>
<%--todo attach object to form ?? --%>
<form action="AddQuestionServlet" method="post">
    <br>Question: <input type="text" name="question"/>
    <c:forEach var="i" begin="0" end="3">
        <br>Answer${i}: <input type="text" name="answ${i}" id="answ${i}"/><input type="checkbox" name="isTrueAnsw${i}" id="isTrueAnsw${i}"> <label for="isTrueAnsw${i}">is
        true</label>
    </c:forEach>
    <br><input type="submit" value="Add Question">
</form>

<a href="index.jsp">Index</a>

</body>
</html>
