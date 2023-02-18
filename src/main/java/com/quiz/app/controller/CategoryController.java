package com.quiz.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.app.model.Category;
import com.quiz.app.service.CategoryService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
    
    private Logger log = LoggerFactory.getLogger(CategoryController.class);

    
    @Autowired
    private CategoryService catService;


    // add a category
    @PostMapping("/add-category")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {

        log.info("POST: /add-category");
        Category cat = this.catService.addCategory(category);
        return ResponseEntity.ok(cat);
    }

    // getting category by id

    @GetMapping("/{categoryId}")
    public Category getCatergoryById(@PathVariable("categoryId") Long id) {

        log.info("GET: /{categoryId}");
        return this.catService.getCategoryById(id);
    }
    
    @GetMapping("/all")
    public List<Category> getAllCategories() {
        log.info("GET: /all");
        return this.catService.getAllCategories();
    }

    @PutMapping("/update")
    public Category updateCategory(@RequestBody Category category) {
        log.info("PUT: /update");
        return this.catService.updateCategory(category);
    }
    
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long id) {
        log.info("DELETE: /{categoryId}");
        this.catService.deleteCategory(id);
    }

}
