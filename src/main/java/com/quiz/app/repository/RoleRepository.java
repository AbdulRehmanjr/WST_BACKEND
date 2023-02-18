package com.quiz.app.repository;




import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.app.model.Role;



public interface RoleRepository extends JpaRepository<Role, Long> {

    
    public Role findByRoleName(String role);
    
}
