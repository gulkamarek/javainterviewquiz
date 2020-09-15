package com.jackwise.servlet;

import java.io.IOException;

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
        for (int i = 0; i < 4; i++) {
            String answer = request.getParameter("answ" + i);
            if (!answer.equals("")) {
                question.addAnswer(new Answer(request.getParameter("answ" + i), BooleanUtil.getBoolean(request.getParameter("isTrueAnsw" + i))));
            }
        }
        question.setTopic("java");
        questionsService.addQuestion(question);
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
