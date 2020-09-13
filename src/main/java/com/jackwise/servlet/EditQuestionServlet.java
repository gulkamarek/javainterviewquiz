package com.jackwise.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jackwise.model.Answer;
import com.jackwise.model.Question;
import com.jackwise.service.QuestionsService;
import com.jackwise.util.BooleanUtil;
import com.sun.corba.se.pept.protocol.ServerRequestDispatcher;

@WebServlet("/EditQuestionServlet")
public class EditQuestionServlet extends HttpServlet {

    private QuestionsService questionsService = new QuestionsService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Question question = questionsService.findById(Long.valueOf(request.getParameter("questionId")));
        question.setQuestion(request.getParameter("question"));
        question.getAnswers().forEach(answer->{
            answer.setAnswer(request.getParameter("answ"+answer.getId()));
            answer.setIsCorrect(BooleanUtil.getBoolean(request.getParameter("isTrueAnsw"+answer.getId())));
        });
        questionsService.update(question);
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long questionId = Long.valueOf(request.getParameter("questionId"));
        Question question = questionsService.findById(questionId);
        request.setAttribute("question", question);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("editQuestion.jsp");
        requestDispatcher.forward(request,response);
    }
}
