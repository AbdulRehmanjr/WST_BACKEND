package com.quiz.app.service.Implement;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.app.model.Category;
import com.quiz.app.model.Quiz;
import com.quiz.app.repository.QuizRepository;
import com.quiz.app.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {


    @Autowired
    private QuizRepository quizRepo;
  
    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepo.save(quiz);
    }
    @Override
    public Quiz updateQuiz(Quiz quiz) {
      
        return this.quizRepo.save(quiz);
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        
        return this.quizRepo.findAll();
    }

    @Override
    public Quiz getQuizById(Long id) {
        
        return this.quizRepo.findById(id).get();
    }

    @Override
    public List<Quiz> getQuizByCategoryId(Long id) {
       Category category = new Category();
       category.setCategoryId(id);
        return this.quizRepo.findByCategory(category);
    }

    @Override
    public void deleteQuiz(Long id) {
        this.quizRepo.deleteByid(id);
    }
    
   


    
}
