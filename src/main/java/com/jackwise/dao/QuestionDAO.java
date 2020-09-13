package com.jackwise.dao;

import java.util.List;

import com.jackwise.model.Question;

public interface QuestionDAO {

    public void persist(Question question);
    public void update(Question question);
    public Question edit(Long questionId);
    public void delete(Long questionId);
    public Question findById(Long questionId);
    public List<Question> getAll();

}
