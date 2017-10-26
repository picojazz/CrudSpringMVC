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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

        panier.getListesProds().add(new LigneCommande(pr.findOne(id),qte));
        redirectAttributes.addFlashAttribute("type","alert alert-success");
        redirectAttributes.addFlashAttribute("message","le produit "+pr.findOne(id).getDesignation()+" a bien été ajouter au panier !");

        return "redirect:product-all";
    }
    @RequestMapping(value = "/panier")
    public String panier(){

        return "front/panier";
    }
}
