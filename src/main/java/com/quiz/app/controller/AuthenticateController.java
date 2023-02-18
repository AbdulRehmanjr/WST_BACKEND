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
import org.springframework.web.bind.annotation.RestController;

import com.quiz.app.config.jwt.JwtUtil;
import com.quiz.app.model.JwtRequest;
import com.quiz.app.model.JwtResponse;
import com.quiz.app.model.User;
import com.quiz.app.service.Implement.UserDetailServiceImpl;

@RestController
@CrossOrigin("http://localhost:4200")
public class AuthenticateController {
    
    private Logger log = LoggerFactory.getLogger(AuthenticateController.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    // generate token 
    @PostMapping("/generate-token")
    public ResponseEntity<?>generateToken(@RequestBody JwtRequest request) throws UsernameNotFoundException{

        log.info("/POST  generate-token");
            try {
                
                    authenticate(request.getUsername(), request.getPassword());
                log.info("Authentication {Token class } "+request.getUsername());
            } catch (Exception e) {
                    log.error("User not found exception {Authentication manager}");
                    throw new UsernameNotFoundException("User not found");
            }

            // authentication successful s
         UserDetails user =  this.userDetailsService.loadUserByUsername(request.getUsername());
          log.info("user: "+user.getUsername()+" "+user.getPassword());
          String token =   this.jwtUtil.generateToken(user);
        return ResponseEntity.ok(new JwtResponse(token));
    }




    private void authenticate(String username, String password) throws Exception {
            
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            log.info("Authentication {Authentication manager} "+username);
        } catch (DisabledException e) {
            log.error("USER DISABLED  {Authenticate Controller} "+e.getMessage() );
            throw new Exception("USER DISABLED");
        }
        catch(BadCredentialsException e){
            log.error("INVALID CREDENTIALS {Authenticate Controller} "+e.getMessage() );
            throw new Exception("INVALID CREDENTIALS");
        }

    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){

        return (User)this.userDetailsService.loadUserByUsername(principal.getName());
    }
}
