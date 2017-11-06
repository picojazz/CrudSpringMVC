package com.example.crudspringmvc.dao;

import com.example.crudspringmvc.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    public Role findByRole(String role);
}
