package com.jackwise.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jackwise.model.Question;
import com.jackwise.service.QuestionsService;
import com.jackwise.service.QuizResult;

@WebServlet("/SubmitQuizServlet")
public class SubmitQuizServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Set<Question> questions = (HashSet<Question>) request.getSession().getAttribute("questions");
        QuizResult quizResult = new QuestionsService().getQuizResult(questions, request);
        request.getSession().setAttribute("quizResult", quizResult);
        response.sendRedirect("score.jsp");
    }
}
