package com.example.crudspringmvc.web;

import com.example.crudspringmvc.dao.CategorieRepository;
import com.example.crudspringmvc.entities.Categorie;
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
public class CategorieController {
    @Autowired
    private CategorieRepository cr;

    @RequestMapping(value = "/cat-all")
    public String categories(Model model , @RequestParam(name = "motcle",defaultValue = "")String mc,
                             @RequestParam(name = "page",defaultValue = "0")int p,
                             @RequestParam(name = "size",defaultValue = "8") int s){

        Page<Categorie> categories = cr.rechParCat("%"+mc+"%",new PageRequest(p,s));
        int[] pageTotals = new int[categories.getTotalPages()];

        model.addAttribute("categories",categories);
        model.addAttribute("mc",mc);
        model.addAttribute("pageTot",pageTotals);
        model.addAttribute("pc",p);

        return "cat-index";
    }
    @RequestMapping(value = "/cat-delete")
    public String delete(RedirectAttributes redirectAttributes, Integer id, String mc, int p){
        cr.delete(id);
        redirectAttributes.addFlashAttribute("type","alert alert-success");
        redirectAttributes.addFlashAttribute("message","la categorie "+id+" a bien été supprimé !");


        return "redirect:cat-all?motcle="+mc+"&page="+p;
    }
    @RequestMapping(value = "/cat-new",method = RequestMethod.GET )
    public String ajouter(Model model){
        Categorie cat = new Categorie();

        model.addAttribute("categorie",cat);
        return "cat-new";
    }
    @RequestMapping(value = "/cat-new", method = RequestMethod.POST)
    public String add(@Valid Categorie cat, BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "cat-new";
        }
        cr.save(cat);
        redirectAttributes.addFlashAttribute("type","alert alert-success");
        redirectAttributes.addFlashAttribute("message","la categorie "+cat.getCategorie()+" a bien été enregistrer !");
        return "redirect:cat-all";
    }

}
