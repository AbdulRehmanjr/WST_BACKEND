package com.quiz.app.service.Implement;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.app.model.Category;
import com.quiz.app.repository.CategoryRepository;
import com.quiz.app.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository catRepo;
    @Override
    public Category addCategory(Category category) {
        
        return this.catRepo.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = new Category();
        category.setCategoryId(id);
        this.catRepo.delete(category);
        
    }

    @Override
    public List<Category> getAllCategories() {
       
        return this.catRepo.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        
        return this.catRepo.findById(id).get();
    }

    @Override
    public Category getCategoryByName(String title) {
        
        return this.catRepo.findByTitle(title);
    }


    @Override
    public Category updateCategory(Category category) {
        
        
        return this.catRepo.save(category);
    }

 

    
}
