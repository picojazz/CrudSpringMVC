package com.example.crudspringmvc.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontController {

    @RequestMapping(value = "/")
    public String home(){
        return "front/index";
    }
}
