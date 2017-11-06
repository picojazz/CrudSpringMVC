package com.example.crudspringmvc.dao;

import com.example.crudspringmvc.entities.Role;
import com.example.crudspringmvc.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl  implements UserService{
    @Autowired
    private UserRepository ur;
    @Autowired
    private RoleRepository rr;



    @Override
    public void saveUser(User user) {

            Role role = rr.findByRole("user");
            List<Role> roles = new ArrayList<>();
            roles.add(role);
            user.setRoles(roles);
            ur.save(user);



    }
}
