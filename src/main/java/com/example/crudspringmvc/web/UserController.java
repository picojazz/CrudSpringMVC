package com.example.crudspringmvc.web;

import com.example.crudspringmvc.dao.UserRepository;
import com.example.crudspringmvc.dao.UserService;
import com.example.crudspringmvc.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService us;
    @Autowired
    private UserRepository ur;

    @RequestMapping(value = "/login")
    public String login(Model model){
        model.addAttribute("user",new User());
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
    @RequestMapping(value = "/signup")
    public String addUser(@Valid User user, BindingResult bindingResult){

        if(ur.findByEmail(user.getEmail()) != null){
            bindingResult.rejectValue("email","error.user","Cet email est deja utilisé");
        }
        if(ur.findByUsername(user.getUsername()) != null){
            bindingResult.rejectValue("username","error.user","Ce username est deja utilisé");
        }

        if(bindingResult.hasErrors()){
            return "login";
        }

        us.saveUser(user);

        return "login";
    }
}
