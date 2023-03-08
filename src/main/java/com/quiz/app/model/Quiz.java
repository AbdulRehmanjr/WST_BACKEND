package com.quiz.app.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;



@Entity
@Table(name = "QUIZTABLE")
public class Quiz {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long quizId;

    private String title;

    @Column(length = 5000)
    private String description;

    private String time;

    private int numberOfQuestions;

    private boolean active=false;

    private int totalMarks;

    @ManyToOne
    private Category category;

    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long id) {
        this.quizId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    @Override
    public String toString() {
        return "Quiz [quizId=" + quizId + ", title=" + title + ", description=" + description + ", time=" + time
                + ", numberOfQuestions=" + numberOfQuestions + ", active=" + active + ", totalMarks=" + totalMarks
                + ", category=" + category + "]";
    }
        
}
