package com.quiz.app.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.app.model.Role;
import com.quiz.app.model.User;
import com.quiz.app.repository.RoleRepository;
import com.quiz.app.service.UserService;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    
    private Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepo;

    
    @PostMapping("/register")
    public ResponseEntity<String> resgisterUser(@RequestBody User user){

        Role role = this.roleRepo.findByRoleName(user.getUserType());
        User found = this.userService.getUserByEmail(user.getEmail());
        if (found!=null){
            return ResponseEntity.badRequest().body("User already exist with given email");
        }
        if(role!=null){
            if(user.getUserType().equals("USER")){

                this.userService.createUser(user,role);
                return  ResponseEntity.ok().body("User Created successfully with role as USER");
            }
            this.userService.createUser(user,role);
            return  ResponseEntity.ok().body("User Created successfully with role as TEACHER");
        }
        log.error("User creation error");
        return ResponseEntity.badRequest().body("User creation error");
    }

    @GetMapping("/{username}")
    public User getuser(@PathVariable("username") String username){

        User result = this.userService.getUser(username);
        if(result == null){
            log.error("User not found");
            return null;
        }
        log.info("User Found {Controller class user }" );

        return result;

    }
    
    @GetMapping("/all")
    List<User> Allusers(){

        return this.userService.getAllUsers();
    }
    // delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        this.userService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }

}