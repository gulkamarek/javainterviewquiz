<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your Score!</title>
</head>
<body>

<h1>Your Score is ${sessionScope.quizResult.score} %!</h1>
<form action="result.jsp">
    <input type="submit" value="See Results"/>
</form>

</body>
</html>
