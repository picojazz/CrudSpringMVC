package com.example.crudspringmvc.web;


import com.example.crudspringmvc.dao.ProduitRepository;
import com.example.crudspringmvc.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProduitController {
    @Autowired
    private ProduitRepository pr ;

    @RequestMapping(value = "/all")
    public String index(Model model , @RequestParam(name = "motcle",defaultValue = "") String mc){
        Page<Produit> produits = pr.rechParMc("%"+mc+"%",new PageRequest(0,15));

        model.addAttribute("produits",produits);
    model.addAttribute("mc",mc);

    return "index";

    }

}
