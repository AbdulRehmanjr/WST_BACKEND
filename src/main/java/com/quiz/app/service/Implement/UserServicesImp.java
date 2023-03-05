package com.quiz.app.service.Implement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.quiz.app.model.Role;
import com.quiz.app.model.User;
import com.quiz.app.repository.UserRepository;
import com.quiz.app.service.UserService;

@Service
public class UserServicesImp implements UserService {

    private Logger log = LoggerFactory.getLogger(UserServicesImp.class);

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public User createUser(User user,Role role){
        
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setUserPassword(encoder.encode(user.getUserPassword()));
        return this.userRepo.save(user);
    }
    @Override
    public User getUser(String username) {
        log.info("Getting user: "+username);
            User user = this.userRepo.findByUserName(username);
            if(user == null){
                log.error("User not found");
                return user;
            }
        return user;
    }
    @Override
    public void deleteUser(Long id) {
        log.info("DELETE with id :{}",id);
        this.userRepo.deleteById(id);
    }
    @Override
    public List<User> getAllUsers() {
        log.info("Getting all users");
        return this.userRepo.findAll();
    }
    @Override
    public User getUserByEmail(String email) {
        log.info("Getting By email");
        return this.userRepo.findByEmail(email);
    }


    
}
