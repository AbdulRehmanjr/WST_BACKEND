package com.quiz.app.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.app.model.Category;

public interface CategoryRepository  extends JpaRepository<Category, Long>{


    public Category findByTitle(String titleName);    
}
