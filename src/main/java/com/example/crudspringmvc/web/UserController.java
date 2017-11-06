package com.example.crudspringmvc.web;

import com.example.crudspringmvc.dao.UserService;
import com.example.crudspringmvc.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    @Autowired
    private UserService us;

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest req, HttpServletResponse resp){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(req,resp,auth);
        }
        return "redirect:login?logout";
    }
    @RequestMapping(value = "/reg")
    public String addUser(){
        User user = new User("pico","picojazz","picojazzz@gmail.com");



        us.saveUser(user);
        return "ok";
    }
}
