package com.jackwise.servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jackwise.model.Question;
import com.jackwise.service.QuestionsService;

@WebServlet("/StartQuizServlet")
public class StartQuizServlet extends HttpServlet {

    private QuestionsService questionsService = new QuestionsService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int questionsNumber = Integer.valueOf(request.getParameter("questionsNumber"));
        Set<Question> questions = questionsService.getQuestions(questionsNumber);
        request.getSession().setAttribute("questions",questions);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("quiz.jsp");
        requestDispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int questionsNumber = Integer.valueOf(request.getParameter("questionsNumber"));
        Set<Question> questions = questionsService.getQuestions(questionsNumber);
        request.getSession().setAttribute("questions",questions);
        response.sendRedirect("editQuestions.jsp");
    }


}
