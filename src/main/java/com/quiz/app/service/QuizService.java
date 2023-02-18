package com.quiz.app.service;

import java.util.List;

import com.quiz.app.model.Quiz;


public interface QuizService {

    public  Quiz addQuiz(Quiz quiz);

    public Quiz updateQuiz(Quiz quiz);

    public List<Quiz> getAllQuizzes();

    public Quiz getQuizById(Long id);

    public List<Quiz> getQuizByCategoryId(Long id);
    
    public void deleteQuiz(Long id);
    
}
