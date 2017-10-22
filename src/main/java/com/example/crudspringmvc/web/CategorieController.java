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

    @RequestMapping(value = "/admin/cat-all")
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
    @RequestMapping(value = "/admin/cat-delete")
    public String delete(RedirectAttributes redirectAttributes, Integer id, String mc, int p){
        cr.delete(id);
        redirectAttributes.addFlashAttribute("type","alert alert-success");
        redirectAttributes.addFlashAttribute("message","la categorie "+id+" a bien été supprimé !");


        return "redirect:cat-all?motcle="+mc+"&page="+p;
    }
    @RequestMapping(value = "/admin/cat-new",method = RequestMethod.GET )
    public String ajouter(Model model){
        Categorie cat = new Categorie();

        model.addAttribute("categorie",cat);
        return "cat-new";
    }
    @RequestMapping(value = "/admin/cat-new", method = RequestMethod.POST)
    public String add(@Valid Categorie categorie, BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "cat-new";
        }
        cr.save(categorie);
        redirectAttributes.addFlashAttribute("type","alert alert-success");
        redirectAttributes.addFlashAttribute("message","la categorie "+categorie.getName()+" a bien été enregistrer !");
        return "redirect:cat-all";
    }
    @RequestMapping(value = "/admin/cat-edit",method = RequestMethod.GET )
    public String edit(Model model, Integer id){
        model.addAttribute("categorie", cr.findOne(id));

        return "cat-edit";
    }
    @RequestMapping(value = "/admin/cat-edit",method = RequestMethod.POST )
    public String editC(@Valid Categorie categorie,BindingResult result,RedirectAttributes redirectAttributes,
                        @RequestParam(name = "id") Integer id){
        categorie.setId(id);
        if (result.hasErrors()){
            return "cat-edit";
        }
        cr.save(categorie);
        redirectAttributes.addFlashAttribute("type","alert alert-success");
        redirectAttributes.addFlashAttribute("message","la categorie "+ categorie.getName()+" a bien été modifier !");

        return "redirect:cat-all";
    }
    @RequestMapping(value = "/admin/cat-voir")
    public String voir(Model model, Integer id){
        model.addAttribute("categorie",cr.findOne(id));

        return "cat-voir";

    }

}
