package com.jackwise.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jackwise.model.Answer;
import com.jackwise.model.Question;
import com.jackwise.service.QuestionsService;

@WebServlet("/QuestionsUploadServlet")
public class QuestionsUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameter("uploadPassword").equals("u")) {
            response.getWriter().println("Wrong Password");
            return;
        }

        Set<Question> questions = new HashSet<>();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("questions.csv");
        InputStreamReader streamReader = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(streamReader);

        for (String line; (line = reader.readLine()) != null; ) {
            questions.add(createQuestion(line));
        }
        QuestionsService questionsService = new QuestionsService();
        questionsService.deleteAllQuestions();
        questionsService.persistAll(questions);
        response.sendRedirect("index.jsp");
    }

    private Question createQuestion(String line) {
        System.out.println(line.substring(0, 5));

        String[] split = line.split(",");
        Question question = new Question(split[0].substring(2), split[1]);
        for (int i = 2; i < split.length; i++) {
            if (split[i].substring(0, 1).equals("w")) {
                question.addAnswer(new Answer(split[i].substring(2), false));
            } else if (split[i].substring(0, 1).equals("r")) {
                question.addAnswer(new Answer(split[i].substring(2), true));
            }
        }
        return question;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
