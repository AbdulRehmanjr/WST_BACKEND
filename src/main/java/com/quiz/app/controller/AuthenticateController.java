package com.quiz.app.controller;

import java.security.Principal;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.app.config.jwt.JwtUtil;
import com.quiz.app.model.JwtRequest;
import com.quiz.app.model.JwtResponse;
import com.quiz.app.model.User;
import com.quiz.app.service.UserService;
import com.quiz.app.service.Implement.UserDetailServiceImpl;

@RestController
@RequestMapping("/token")
@CrossOrigin("http://localhost:4200")
public class AuthenticateController {
    
    private Logger log = LoggerFactory.getLogger(AuthenticateController.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailServiceImpl userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    // generate token 
    @PostMapping("/generate")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest request){

        log.info("Data from Request {}",request);
            try {
                
                    authentication(request.getUserEmail(), request.getPassword());
                    log.info("Authentication {} ",request.getUserEmail());
            } catch (Exception e) {
                    log.error("User not found exception {}");
                return ResponseEntity.badRequest().body("Error....");
            }

            // authentication successful s
         UserDetails user =  this.userDetailsService.loadUserByUsername(request.getUserEmail());

          log.info("user: "+user.getUsername()+" "+user.getPassword());

          String token =   this.jwtUtil.generateToken(user);

        return ResponseEntity.ok(new JwtResponse(token));
        
    }




    private void authentication(String username, String password) throws Exception {
            
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            log.info("Authentication {} ",username);
        } catch (DisabledException e) {
            log.error("USER DISABLED  {} ",e.getMessage() );
            throw new Exception("USER DISABLED");
        }
        catch(BadCredentialsException e){
            log.error("INVALID CREDENTIALS {} ",e.getMessage() );
            throw new Exception("INVALID CREDENTIALS");
        }

    }

    @PostMapping("/current-user")
    public User getCurrentUser(@RequestBody JwtRequest request){
        log.info("calling current user");
        User user = this.userService.getUserByEmail(request.getUserEmail());
        log.info("USER {}",user);
        return user;
    }
}
