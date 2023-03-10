package com.quiz.app.service.Implement;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.quiz.app.model.Question;
import com.quiz.app.model.Quiz;
import com.quiz.app.repository.QuestionRepository;
import com.quiz.app.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {


    private Logger log = LoggerFactory.getLogger(QuestionServiceImpl.class);
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
    public List<Question> getQuestionsOfQuiz(long  quizId) {
        

        return this.questionRepo.findByQuiz(quizId);
    }

    @Override
    public List<Question> addList(MultipartFile file,Quiz quiz) {

        List<Question> questions = new ArrayList<>();

        
        try (InputStream inputStream = file.getInputStream()) {
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {

                String data[] = line.split(",");
                Question question = new Question();

                question.setContent(data[0]);
                question.setOption1(data[1]);
                question.setOption2(data[2]);
                question.setOption3(data[3]);
                question.setOption4(data[4]);
                question.setAnswer(data[5]);
                
                question.setQuiz(quiz);

                questions.add(question);
            }
            return  this.questionRepo.saveAll(questions);
        } catch (Exception e) {
            log.error("ERROR: {}",e.getMessage());
            return null;
        }        
    }

    @Override
    public List<Question> getQuestionByQuiz(Long id) {

        return this.questionRepo.findByQuiz(id);
    }


    
}
