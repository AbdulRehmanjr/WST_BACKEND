package com.quiz.app.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.app.model.Role;
import com.quiz.app.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

    private Logger log = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private RoleService roleService;
    

    @PostMapping("/create")
    public Role registerRole(@RequestBody Role role) {
        log.info("RoleController.registerRole()");
        log.info("Creating new Role");
        return roleService.createRole(role.getRoleName());
        
    }

}
