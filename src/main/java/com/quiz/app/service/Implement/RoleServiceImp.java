package com.quiz.app.service.Implement;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.app.model.Role;
import com.quiz.app.repository.RoleRepository;
import com.quiz.app.service.RoleService;

@Service
public class RoleServiceImp implements RoleService {

    
    @Autowired
    private RoleRepository roleRepo;
    
    @Override
    public Role createRole(String roleName) {
        Role role = new Role();
        role.setRoleName(roleName);
        return roleRepo.save(role);
    }

    
}
