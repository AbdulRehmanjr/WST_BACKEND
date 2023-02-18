package com.quiz.app.service;

import java.util.List;

import com.quiz.app.model.Category;



public interface CategoryService {
    
    public  Category addCategory(Category category);

    public Category updateCategory(Category category);

    public List<Category> getAllCategories();

    public Category getCategoryById(Long id);
    
    public void deleteCategory(Long id);

}
