package com.quiz.app.service;

import java.util.List;

import com.quiz.app.model.Question;
import com.quiz.app.model.Quiz;



public interface QuestionService {
    
    public  Question addQuestion(Question question);

    public List<Question> addList(List<Question> questions);
    public List<Question> getQuestionByQuiz(Long id);
    public Question updateQuestion(Question question);

    public List<Question> getAllQuestions();

    public Question getQuestionById(Long id);
    
    public void deleteQuestion(Long id);

    public List<Question> getQuestionsOfQuiz(Quiz quiz);
}
