package com.quiz.app.service.Implement;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.app.model.Question;
import com.quiz.app.model.Quiz;
import com.quiz.app.repository.QuestionRepository;
import com.quiz.app.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepo;
    @Override
    public Question addQuestion(Question question) {

        return this.questionRepo.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        
        return this.questionRepo.save(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        
        return this.questionRepo.findAll();
    }

    @Override
    public Question getQuestionById(Long id) {
        
        return this.questionRepo.findById(id).get();
    }

    @Override
    public void deleteQuestion(Long id) {

        Question question = new Question();

        question.setQuestionId(id);
        this.questionRepo.delete(question);        
    }

    @Override
    public List<Question> getQuestionsOfQuiz(Quiz quiz) {
        
        return this.questionRepo.findByQuiz(quiz);
    }

    @Override
    public List<Question> addList(List<Question> questions) {
        
        return this.questionRepo.saveAll(questions);
    }

    @Override
    public List<Question> getQuestionByQuiz(Long id) {
        Quiz q = new Quiz();
        q.setQuizId(id);
        return this.questionRepo.findByQuiz(q);
    }


    
}
