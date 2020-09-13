package com.jackwise.servlet;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jackwise.model.Answer;
import com.jackwise.model.Question;
import com.jackwise.service.QuestionsService;
import com.jackwise.util.BooleanUtil;

@WebServlet("/AddQuestionServlet")
public class AddQuestionServlet extends HttpServlet {

    QuestionsService questionsService = new QuestionsService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Question question = new Question();
        question.setQuestion(request.getParameter("question"));
        question.addAnswers(new Answer(request.getParameter("answ0"), BooleanUtil.getBoolean(request.getParameter("isTrueAnsw0"))));
        question.addAnswers(new Answer(request.getParameter("answ1"), BooleanUtil.getBoolean(request.getParameter("isTrueAnsw1"))));
        question.addAnswers(new Answer(request.getParameter("answ2"), BooleanUtil.getBoolean(request.getParameter("isTrueAnsw2"))));
        question.addAnswers(new Answer(request.getParameter("answ3"), BooleanUtil.getBoolean(request.getParameter("isTrueAnsw3"))));
        questionsService.addQuestion(question);
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
