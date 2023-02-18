package com.quiz.app.service;

import java.util.List;

import com.quiz.app.model.Role;
import com.quiz.app.model.User;



public interface UserService {
    
    // create a new user
    public User createUser(User user,Role role);

    // get by user name
    public User getUser(String username);

    //  get by email 
    public User getUserByEmail(String email);

    // delete by id
    public void deleteUser(Long id);
    // public void getall
    public List<User> getAllUsers();
}
