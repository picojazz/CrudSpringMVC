package com.example.crudspringmvc.web;


import com.example.crudspringmvc.component.Counter;
import com.example.crudspringmvc.dao.CategorieRepository;
import com.example.crudspringmvc.dao.ProduitRepository;
import com.example.crudspringmvc.entities.Produit;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class ProduitController {
    @Autowired
    private ProduitRepository pr ;
    @Autowired
    private CategorieRepository cr;
    @Value("${imgDir}")
    private String dirImage;
    @Autowired
    private Counter counter;

    @RequestMapping(value = "/admin/all")
    public String index(Model model , @RequestParam(name = "motcle",defaultValue = "")String mc,
                        @RequestParam(name = "page",defaultValue = "0")int p,
                        @RequestParam(name = "size",defaultValue = "8") int s){

        Page<Produit> produits = pr.rechParMc("%"+mc+"%",new PageRequest(p,s));
        int[] pageTotals = new int[produits.getTotalPages()];

        model.addAttribute("produits",produits);
        model.addAttribute("mc",mc);
        model.addAttribute("pageTot",pageTotals);
        model.addAttribute("pc",p);
        System.out.println(dirImage);


    return "index";

    }
    @RequestMapping(value = "/admin/delete")
    public String delete(RedirectAttributes redirectAttributes,Long id,String mc,int p){
        pr.delete(id);
        redirectAttributes.addFlashAttribute("type","alert alert-success");
        redirectAttributes.addFlashAttribute("message","le produit "+id+" a bien été supprimé !");


        return "redirect:all?motcle="+mc+"&page="+p;
    }
    @RequestMapping(value = "/admin/new",method = RequestMethod.GET )
    public String ajouter(Model model){
        Produit p = new Produit();
        model.addAttribute("categories",cr.findAll());
        model.addAttribute("produit",p);

        return "new";
    }
    @RequestMapping(value = "/admin/new", method = RequestMethod.POST)
    public String add(@Valid Produit p, BindingResult result, RedirectAttributes redirectAttributes,
                      @RequestParam(name = "image") MultipartFile file) throws IOException {
        if (result.hasErrors()){
            return "new";
        }
        pr.save(p);
        if (!(file.isEmpty())){
            file.transferTo(new File(dirImage+p.getId()));
        }
        redirectAttributes.addFlashAttribute("type","alert alert-success");
        redirectAttributes.addFlashAttribute("message","le produit "+p.getDesignation()+" a bien été enregistrer !");
        return "redirect:all";
    }
    @RequestMapping(value = "/admin/edit",method = RequestMethod.GET )
    public String edit(Model model, Long id){
        model.addAttribute("categories",cr.findAll());
        model.addAttribute("produit", pr.findOne(id));

        return "edit";
    }
    @RequestMapping(value = "/admin/edit",method = RequestMethod.POST )
    public String editP(@Valid Produit p,BindingResult result,RedirectAttributes redirectAttributes,
                        @RequestParam(name = "id") Long id ,@RequestParam(name = "image") MultipartFile file) throws IOException {
        p.setId(id);
        if (result.hasErrors()){
            return "edit";
        }
        pr.save(p);
        if (!(file.isEmpty())){
            file.transferTo(new File(dirImage+p.getId()));
        }
        redirectAttributes.addFlashAttribute("type","alert alert-success");
        redirectAttributes.addFlashAttribute("message","le produit "+p.getId()+" a bien été modifier !");

        return "redirect:all";
    }
    /*@RequestMapping(value = "/")
    public String home(){
        return "redirect:all";
    }*/

    @RequestMapping(value = "/getPhoto",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] photo(Long id ) throws Exception {

        File file = new File(dirImage+id);
        return IOUtils.toByteArray(new FileInputStream(file));
    }

}
