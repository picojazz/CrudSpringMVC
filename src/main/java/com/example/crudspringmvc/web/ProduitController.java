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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProduitController {
    @Autowired
    private ProduitRepository pr ;

    @RequestMapping(value = "/all")
    public String index(Model model , @RequestParam(name = "motcle",defaultValue = "")String mc,
                        @RequestParam(name = "page",defaultValue = "0")int p,
                        @RequestParam(name = "size",defaultValue = "8") int s){

        Page<Produit> produits = pr.rechParMc("%"+mc+"%",new PageRequest(p,s));
        int[] pageTotals = new int[produits.getTotalPages()];

        model.addAttribute("produits",produits);
        model.addAttribute("mc",mc);
        model.addAttribute("pageTot",pageTotals);
        model.addAttribute("pc",p);

    return "index";

    }
    @RequestMapping(value = "/delete")
    public String delete(Model model , RedirectAttributes redirectAttributes,Long id,String mc,int p){
        pr.delete(id);
        redirectAttributes.addFlashAttribute("type","alert alert-success");
        redirectAttributes.addFlashAttribute("message","le produit "+id+" a bien été supprimé !");


        return "redirect:all?motcle="+mc+"&page="+p;
    }

}
