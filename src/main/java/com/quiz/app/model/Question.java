package com.quiz.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "QUESTIONTABLE")
public class Question {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(length = 5000)
    private String content;
    
    private String option1;

    private String option2;

    private String option3;

    private String option4;
    
    private String answer;
    

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @Transient
    private Quiz quiz;


    public Long getQuestionId() {
        return questionId;
    }


    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }


    public String getOption1() {
        return option1;
    }


    public void setOption1(String option1) {
        this.option1 = option1;
    }


    public String getOption2() {
        return option2;
    }


    public void setOption2(String option2) {
        this.option2 = option2;
    }


    public String getOption3() {
        return option3;
    }


    public void setOption3(String option3) {
        this.option3 = option3;
    }


    public String getOption4() {
        return option4;
    }


    public void setOption4(String option4) {
        this.option4 = option4;
    }


    public String getAnswer() {
        return answer;
    }


    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public Quiz getQuiz() {
        return quiz;
    }


    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }




    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "Question [answer=" + answer + ", content=" + content + ", option1=" + option1 + ", option2=" + option2
                + ", option3=" + option3 + ", option4=" + option4 + ", questionId=" + questionId + ", quiz=" + quiz
                + "]";
    }
}
