package com.example.crudspringmvc.web;


import com.example.crudspringmvc.dao.CategorieRepository;
import com.example.crudspringmvc.dao.ProduitRepository;
import com.example.crudspringmvc.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ProduitController {
    @Autowired
    private ProduitRepository pr ;
    @Autowired
    private CategorieRepository cr;

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
    public String delete(RedirectAttributes redirectAttributes,Long id,String mc,int p){
        pr.delete(id);
        redirectAttributes.addFlashAttribute("type","alert alert-success");
        redirectAttributes.addFlashAttribute("message","le produit "+id+" a bien été supprimé !");


        return "redirect:all?motcle="+mc+"&page="+p;
    }
    @RequestMapping(value = "/new",method = RequestMethod.GET )
    public String ajouter(Model model){
        Produit p = new Produit();
        model.addAttribute("categories",cr.findAll());
        model.addAttribute("produit",p);
        return "new";
    }
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String add(@Valid Produit p, BindingResult result,RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "new";
        }
        pr.save(p);
        redirectAttributes.addFlashAttribute("type","alert alert-success");
        redirectAttributes.addFlashAttribute("message","le produit "+p.getDesignation()+" a bien été enregistrer !");
        return "redirect:all";
    }
    @RequestMapping(value = "/edit",method = RequestMethod.GET )
    public String edit(Model model, Long id){
        model.addAttribute("categories",cr.findAll());
        model.addAttribute("produit", pr.findOne(id));

        return "edit";
    }
    @RequestMapping(value = "/edit",method = RequestMethod.POST )
    public String editP(@Valid Produit p,BindingResult result,RedirectAttributes redirectAttributes,
                        @RequestParam(name = "id") Long id){
        p.setId(id);
        if (result.hasErrors()){
            return "edit";
        }
        pr.save(p);
        redirectAttributes.addFlashAttribute("type","alert alert-success");
        redirectAttributes.addFlashAttribute("message","le produit "+p.getId()+" a bien été modifier !");

        return "redirect:all";
    }
    @RequestMapping(value = "/")
    public String home(){
        return "redirect:all";
    }

}
