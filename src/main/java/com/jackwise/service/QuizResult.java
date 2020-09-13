package com.jackwise.service;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.jackwise.model.Answer;
import com.jackwise.model.Question;

public class QuizResult {

    private final double score;
    private final Map<Question, Set<Answer>> selectedAnswersGroupedByQuestion;

    public QuizResult(Map<Question, Set<Answer>> selectedAnswersGroupedByQuestion) {
        this.score = calculateScore(selectedAnswersGroupedByQuestion);
        this.selectedAnswersGroupedByQuestion = selectedAnswersGroupedByQuestion;
    }

    private double calculateScore(Map<Question, Set<Answer>> selectedAnswersGroupedByQuestion) {
        double count = selectedAnswersGroupedByQuestion.entrySet().stream().filter(entry ->
                entry.getValue().equals(entry.getKey().getCorrectAnswers())).count();
        return (count * 100 / selectedAnswersGroupedByQuestion.entrySet().size());
    }

    public double getScore() {
        return score;
    }

    public Map<Question, Set<Answer>> getSelectedAnswersGroupedByQuestion() {
        return selectedAnswersGroupedByQuestion;
    }

    public boolean checkCheckboxStatus(Question question, Answer answer) {
        return Objects.nonNull(selectedAnswersGroupedByQuestion.get(question))
                && selectedAnswersGroupedByQuestion.get(question).contains(answer);
    }

    public String checkAnswerStatus(Question question, Answer answer) {
        boolean isSelected = selectedAnswersGroupedByQuestion.get(question).contains(answer);
        if (answer.getIsCorrect()) {
            return AnswerStatusApperance.CORRECT.name();
        } else if (isSelected && !answer.getIsCorrect()) {
            return AnswerStatusApperance.WRONG.name();
        } else {
            return AnswerStatusApperance.NEUTRAL.name();
        }
    }
}
