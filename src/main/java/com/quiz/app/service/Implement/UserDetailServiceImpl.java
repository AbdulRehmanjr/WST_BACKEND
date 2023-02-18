package com.quiz.app.service.Implement;






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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User resultUser = this.userRepo.findByUserName(username);
        if(resultUser == null){
            log.error("User not found of username: "+username);
        }
        return (UserDetails)resultUser;
    }


    
}
