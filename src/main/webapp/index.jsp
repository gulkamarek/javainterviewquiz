<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Java Quiz</title>
</head>
<body>
<h1>Java Quiz</h1>
<h3>The application is being developed. This is only demo version.</h3>
<form action="StartQuizServlet" method="post">
    <br>How may questions do you want? (Currently only 5 available)<input type="text" name="questionsNumber" value="3"/>
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
<br><br>

<form action="questionsUpload.jsp">
    <input type="submit" value="Reload all questions">
</form>
<br><br>

</body>
</html>
