package com.quiz.app.controller;

import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.app.model.Quiz;
import com.quiz.app.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin(origins = "http://localhost:4200")
public class QuizController {
    
    private Logger log = LoggerFactory.getLogger(QuizController.class);

    @Autowired
    private QuizService quizService;

    @PostMapping("/add-quiz")
    public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz) {

        log.info("POST: /add-quiz");
        //Quiz q = new Quiz();
     //   q.setCategory(this.catRepo.findById(quiz.getCategory().getCategoryId()).get());
        Quiz q = this.quizService.addQuiz(quiz);
       
        return ResponseEntity.ok(q);
    }

    // getting category by id

    @GetMapping("/{quizId}")
    public Quiz getQuizById(@PathVariable("quizId") Long id) {

        log.info("GET: /{quizId}");
        return this.quizService.getQuizById(id);
    }
    @GetMapping("/quizBycategory/{categoryId}")
    public List<Quiz> getQuizzesByCategoryId(@PathVariable("categoryId") Long id) {

        log.info("GET: /{categoryId}");
        return this.quizService.getQuizByCategoryId(id);
    }
    
    @GetMapping("/all")
    public List<Quiz> getAllQuizzes() {
        log.info("GET: /all");
        return this.quizService.getAllQuizzes();
    }

    @PutMapping("/update")
    public Quiz updateQuiz(@RequestBody Quiz quiz) {
        log.info("PUT: /update");
        return this.quizService.updateQuiz(quiz);
    }
    
    @DeleteMapping("/{quizId}")
    public void deleteQuiz(@PathVariable("quizId") Long id) {
        log.info("DELETE: /{quizId}");
        this.quizService.deleteQuiz(id);
    }


}
