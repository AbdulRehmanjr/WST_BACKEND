package com.quiz.app.repository;

import java.util.List;






import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.quiz.app.model.Question;
import com.quiz.app.model.Quiz;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM question WHERE quiz_quiz_id = ?1", nativeQuery = true)
    List<Question> findByQuiz(Quiz quiz);
}
