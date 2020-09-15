package com.jackwise.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.jackwise.dao.QuestionDAO;
import com.jackwise.model.Answer;
import com.jackwise.model.Question;
import com.jackwise.util.HibernateUtil;


public class QuestionDAOImpl implements QuestionDAO {

    private Session currentSession;

    private Transaction currentTransaction;

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    protected Session openCurrentSession() {
        return sessionFactory.openSession();
    }

    private Session openCurrentSessionWithTransaction() {
        currentSession = sessionFactory.openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    private void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private void closeCurrentSession() {
        currentSession.close();
    }

    @Override
    public void persist(Question question) {
        openCurrentSessionWithTransaction();
        currentSession.persist(question);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(Question question) {
        openCurrentSessionWithTransaction();
        currentSession.merge(question);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public Question edit(Long questionId) {
        openCurrentSessionWithTransaction();
        Question questionById = findById(questionId);
        closeCurrentSessionWithTransaction();
        return questionById;
    }

    @Override
    public void delete(Long questionId) {
        openCurrentSessionWithTransaction();
        currentSession.delete(currentSession.get(Question.class, questionId));
        closeCurrentSessionWithTransaction();
    }

    @Override
    public Question findById(Long questionId) {
        openCurrentSessionWithTransaction();
        Question question = (Question) currentSession.get(Question.class, questionId);
        closeCurrentSessionWithTransaction();
        return question;
    }

    @Override
    public List<Question> getAll() {
        openCurrentSessionWithTransaction();
        List allQuestions = currentSession.createQuery("FROM " + Question.class.getSimpleName()).getResultList();
        closeCurrentSessionWithTransaction();
        return allQuestions;
    }

    @Override
    public void deleteAll() {
        openCurrentSessionWithTransaction();
        Query deleteAnswers = currentSession.createQuery("DELETE FROM " + Answer.class.getSimpleName());
        Query deleteQuestions = currentSession.createQuery("DELETE FROM " + Question.class.getSimpleName());
        deleteAnswers.executeUpdate();
        deleteQuestions.executeUpdate();
        closeCurrentSessionWithTransaction();
    }
}
