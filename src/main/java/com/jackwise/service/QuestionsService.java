package com.jackwise.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import com.jackwise.dao.QuestionDAO;
import com.jackwise.dao.impl.QuestionDAOImpl;
import com.jackwise.model.Answer;
import com.jackwise.model.Question;

public class QuestionsService {

    private QuestionDAO questionDAO = new QuestionDAOImpl();

    public Map<Question, Set<Answer>> extractSelectedAnswers(Set<Question> questions, HttpServletRequest request) {
        Map<Question, Set<Answer>> selectedAnswersGroupedByQuestion = new HashMap<>();
        questions.forEach(question -> {
            Set<Answer> answersSelectedByUser = getAnswersSelectedByUser(question, request);
            selectedAnswersGroupedByQuestion.put(question, answersSelectedByUser);
        });
        return selectedAnswersGroupedByQuestion;
    }

    private Set<Answer> getAnswersSelectedByUser(Question question, HttpServletRequest request) {
        Set<Answer> selectedAnswer = new HashSet<>();
        for (Answer answer : question.getAnswers()) {
            if (Objects.nonNull(request.getParameter(question.getId() + "answ" + answer.getId())))
                selectedAnswer.add(answer);
        }
        return selectedAnswer;
    }

    public Set<Question> getQuestions(int questionsNumber) {

        List<Question> allQuestions = questionDAO.getAll();
        if (questionsNumber == 0) {
            questionsNumber = allQuestions.size();
        }
        Set<Question> randomQuestions = getRandomElements(allQuestions, questionsNumber);

        return randomQuestions;
    }

    private Set<Question> getRandomElements(List<Question> allQuestions, int questionsNumber) {
        Set<Question> randomQuestions = new HashSet<>();
        Set<Integer> randomNumbers = Stream.iterate(0, n -> n + 1).limit(questionsNumber).collect(Collectors.toSet());

        for (int i : randomNumbers) {
            randomQuestions.add(allQuestions.get(i));
        }
        return randomQuestions;
    }

    public QuizResult getQuizResult(Set<Question> questions, HttpServletRequest request) {
        Map<Question, Set<Answer>> selectedAnswersGroupedByQuestion = new QuestionsService().extractSelectedAnswers(questions, request);
        return new QuizResult(selectedAnswersGroupedByQuestion);
    }

    public void addQuestion(Question question) {
        questionDAO.persist(question);
    }

    public Question findById(Long questionId) {
        return questionDAO.findById(questionId);
    }

    public void update(Question question) {
        questionDAO.update(question);

    }

    public void delete(Long questionId) {
        questionDAO.delete(questionId);
    }

    public void persistAll(Set<Question> questions) {
        for (Question q : questions) {
            questionDAO.persist(q);
        }
    }

    public void deleteAllQuestions() {
        questionDAO.deleteAll();
    }
}
