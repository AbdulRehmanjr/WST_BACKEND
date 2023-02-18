package com.quiz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.app.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUserName(String userName);

    public User findByEmail(String email);

}
