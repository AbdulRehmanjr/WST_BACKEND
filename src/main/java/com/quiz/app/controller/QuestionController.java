package com.quiz.app.controller;


import java.util.Collections;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.quiz.app.model.Question;
import com.quiz.app.model.Quiz;
import com.quiz.app.service.QuestionService;
import com.quiz.app.service.QuizService;

@RestController
@RequestMapping("/question")
@CrossOrigin("http://localhost:4200")
public class QuestionController {
    

    private Logger log= LoggerFactory.getLogger(QuestionController.class);
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuizService quizService;

    @PostMapping("/{quizId}/addquestion")
    public ResponseEntity<?> addQuestion(@RequestParam("file") MultipartFile file,
            @PathVariable("quizId") long quizId) {
        
                log.info("Adding the quiz {}",quizId);
        Quiz quiz = this.quizService.getQuizById(quizId);
        
            log.info("Quiz: {}",quiz);
       List<Question> response =  this.questionService.addList(file,quiz);
       if(response==null){
        return ResponseEntity.badRequest().body("Error in Savinf Question");
       }

       return ResponseEntity.status(200).body("Question Saved");
    }

    @GetMapping("/{questionId}")
    public Question getQuestionById(@PathVariable("questionId") Long id) {

        
        return this.questionService.getQuestionById(id);
    }

    @GetMapping("/quiz/{quizId}")
    public List<Question> getAllQuestionsByQuizId(@PathVariable("quizId") Long id) {

        Quiz quiz = this.quizService.getQuizById(id);
        
        List<Question> list = this.questionService.getQuestionByQuiz(id);

        list = list.subList(0, quiz.getNumberOfQuestions());
        

        Collections.shuffle(list);

        return list;

    }

    @PutMapping("/update")
    public Question updateQuestion(@RequestBody Question question) {

        
        return this.questionService.updateQuestion(question);
    }

    @DeleteMapping("/{questionId}")
    public void deleteQuestion(@PathVariable("questionId") Long id) {
        
        this.questionService.deleteQuestion(id);
    }

}
