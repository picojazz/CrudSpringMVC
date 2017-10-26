package com.example.crudspringmvc.web;


import com.example.crudspringmvc.component.Panier;
import com.example.crudspringmvc.dao.ProduitRepository;
import com.example.crudspringmvc.entities.LigneCommande;
import com.example.crudspringmvc.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FrontController {
    @Autowired
    private ProduitRepository pr;
    @Autowired
    private Panier panier;

    @RequestMapping(value = "/")
    public String home(Model model){
        model.addAttribute("produits",pr.findAll());
        return "front/index";
    }
    @RequestMapping(value = "/product-all")
    public String product(Model model , @RequestParam(name = "motcle",defaultValue = "")String mc,
                          @RequestParam(name = "page",defaultValue = "0")int p,
                          @RequestParam(name = "size",defaultValue = "8") int s){

        Page<Produit> produits = pr.rechParMc("%"+mc+"%",new PageRequest(p,s));
        int[] pageTotals = new int[produits.getTotalPages()];

        model.addAttribute("produits",produits);
        model.addAttribute("mc",mc);
        model.addAttribute("pageTot",pageTotals);
        model.addAttribute("pc",p);
        return "front/product-all";
    }

    @RequestMapping(value = "/product-all/{id}")
    public String details(Model model, @PathVariable("id") Long id){

        model.addAttribute("produit",pr.findOne(id));

        return "front/details";
    }
    @RequestMapping(value = "/add-panier", method = RequestMethod.GET)
    public String addPanier(Long id,int qte, RedirectAttributes redirectAttributes){
        int test = 0;
        for (LigneCommande l : panier.getListesProds()) {
            if (l.getProduit().getId() == id){
               test = 1;
            }
        }

        if (test == 1){
            redirectAttributes.addFlashAttribute("type", "alert alert-danger");
            redirectAttributes.addFlashAttribute("message", "le produit " + pr.findOne(id).getDesignation() + " est deja present dans le panier !");
        }else{

            panier.getListesProds().add(new LigneCommande(pr.findOne(id), qte));
            redirectAttributes.addFlashAttribute("type", "alert alert-success");
            redirectAttributes.addFlashAttribute("message", "le produit " + pr.findOne(id).getDesignation() + " a bien été ajouter au panier !");
        }
        return "redirect:product-all";
    }
    @RequestMapping(value = "/panier")
    public String panier(Model model){

        model.addAttribute("panier",panier.getListesProds());
        model.addAttribute("total",panier.total());

        return "front/panier";
    }
    @RequestMapping(value = "/modif-panier")
    public String modifPanier(Long id,int qte){

        panier.getListesProds().forEach(l ->{
            if (l.getProduit().getId() == id){
                l.setQte(qte);
            }
        });

        return "redirect:panier";
    }
    @RequestMapping(value = "/npanier",produces = "text/plain")
    @ResponseBody
    public String nPanier(){

        return String.valueOf(panier.getListesProds().size());
    }
    @RequestMapping(value = "/delete-panier")
    public String deletePanier(Long id,RedirectAttributes redirectAttributes){

        int i = -1;
        for (LigneCommande l : panier.getListesProds()) {
            if (l.getProduit().getId() == id){
                 i=panier.getListesProds().indexOf(l);
            }
        }
        if (i== -1){
            redirectAttributes.addFlashAttribute("type", "alert alert-danger");
            redirectAttributes.addFlashAttribute("message", "une erreur est survenue !");
        }else{
            panier.getListesProds().remove(i);
            redirectAttributes.addFlashAttribute("type", "alert alert-success");
            redirectAttributes.addFlashAttribute("message", "le produit " + pr.findOne(id).getDesignation() + " a bien été supprimer du panier !");
        }


        return "redirect:panier";
    }
}
