package com.quiz.app.repository;

import java.util.List;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.quiz.app.model.Category;
import com.quiz.app.model.Quiz;




public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM quiz WHERE quiz_id = ?1", nativeQuery = true)
    public void deleteByid(Long id);
    
    // find by category id
    public List<Quiz> findByCategory(Category category);
}
