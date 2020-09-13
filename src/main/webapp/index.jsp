<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Java Quiz</title>
</head>
<body>
<h1>Java Quiz</h1>
<form action="StartQuizServlet" method="post">
    <br>How may questions do you want? <input type="text" name="questionsNumber"/>
    <br><input type="submit" value="begin the quiz">
</form>
<br><br>

<form action="addQuestion.jsp" method="get">
    <input type="submit" value="Add Question">
</form>
<br><br>

<form action="StartQuizServlet" method="get">
    <input type="hidden" name="questionsNumber" value="0">
    <input type="submit" value="Edit questions">
</form>

</body>
</html>
