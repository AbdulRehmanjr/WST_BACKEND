package com.quiz.app.service.Implement;






import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.quiz.app.model.User;
import com.quiz.app.repository.UserRepository;
 

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        User resultUser = this.userRepo.findByEmail(email);
        if(resultUser == null){
            log.error("No user with {} given email ",email);
        }
        return new org.springframework.security.core.userdetails.User(resultUser.getEmail(),resultUser.getUserPassword(),resultUser.getRoles());
    }


    
}
