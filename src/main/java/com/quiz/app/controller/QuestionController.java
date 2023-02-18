package com.quiz.app.controller;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
    
    private Logger log = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuizService quizService;
    
   @PostMapping("/{quizId}/add-quizquestion")
   public ResponseEntity<?> addQuestion(@RequestParam("file") MultipartFile file,@PathVariable("quizId") long quizId) {

       log.info("POST: /add-quizquestions");
       log.info("quiz ID "+quizId);
       List <Question> questions = new ArrayList<>();
        
    Quiz quiz = new Quiz();
    quiz.setQuizId(quizId);
       try (InputStream in = file.getInputStream()) {
        InputStreamReader isr = new InputStreamReader(in,StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while((line = br.readLine())!=null){
            //log.info(line);
            // Spliting the the file data on , 
            String data[] = line.split(",");
            Question q  = new Question();

            q.setContent(data[0]);
            q.setOption1(data[1]);
            q.setOption2(data[2]);
            q.setOption3(data[3]);
            q.setOption4(data[4]);
            q.setAnswer(data[5]);
            
            q.setQuiz(quiz);

            questions.add(q);
        }
       }catch(Exception e){
            log.error("error in stream");
       }
       this.questionService.addList(questions);
       
       return ResponseEntity.ok("Okay");
   }
   @GetMapping("/{questionId}")
   public Question getQuestionById(@PathVariable("questionId") Long id) {

       log.info("GET: /{questionId}");
       return this.questionService.getQuestionById(id);
   }


   @GetMapping("/questions/{quizId}")
    public List<Question> getAllQuestionsByQuizId(@PathVariable("quizId") Long id) {
        
        Quiz quiz = this.quizService.getQuizById(id);
        //System.out.println(quiz);
        List<Question> list = this.questionService.getQuestionByQuiz(id);
        
        list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions()));
        // System.out.println(list);

        Collections.shuffle(list);

        return list;
      
    }

   @PutMapping("/update")
    public Question updateQuestion(@RequestBody Question question) {
    
         log.info("PUT: /update");
         return  this.questionService.updateQuestion(question);
    }

    @DeleteMapping("/{questionId}")
    public void deleteQuestion(@PathVariable("questionId") Long id) {
        log.info("DELETE: /{questionId}");
        this.questionService.deleteQuestion(id);
    }

    
}
