package com.quiz.app.repository;

import java.util.List;






import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.quiz.app.model.Question;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM questiontable WHERE quiz_quiz_id = (:quizId)",nativeQuery = true)
    List<Question> findByQuiz(long quizId);
}
