package com.example.crudspringmvc.web;


import com.example.crudspringmvc.dao.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontController {
    @Autowired
    private ProduitRepository pr;

    @RequestMapping(value = "/")
    public String home(Model model){
        model.addAttribute("produits",pr.findAll());
        return "front/index";
    }
    @RequestMapping(value = "/product-all")
    public String product(Model model){

        model.addAttribute("produits",pr.findAll());
        return "front/product-all";
    }
}
