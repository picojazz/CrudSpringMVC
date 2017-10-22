package com.example.crudspringmvc.web;


import com.example.crudspringmvc.component.Counter;
import com.example.crudspringmvc.dao.ProduitRepository;
import com.example.crudspringmvc.entities.Produit;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:8080")
@RestController
public class RestProduitController {

    @Autowired
    private ProduitRepository pr;




    @Value("${imgDir}")
    private String dirImage;

    @RequestMapping(value = "/produits")
    public List<Produit> getProduits(){


        return pr.findAll();
    }
    @RequestMapping(value = "/produits/{id}", method = RequestMethod.GET)
    public Produit getProduit(@PathVariable(name = "id") Long id){

        return pr.findOne(id);
    }
    @RequestMapping(value = "/produits/new", method = RequestMethod.POST)
    public Boolean newProduit(@Valid Produit p){
        pr.save(p);
        return true;
    }
    @RequestMapping(value = "/produits/{id}", method = RequestMethod.PUT    )
    public Boolean updateProduit(@PathVariable(name = "id") Long id,@Valid Produit p){
        p.setId(id);
        pr.save(p);
        return true;
    }
    @RequestMapping(value = "/produits/{id}", method = RequestMethod.DELETE)
    public Boolean deleteProduit(@PathVariable(name = "id") Long id){
        pr.delete(id);
        return true;
    }
    @RequestMapping(value = "/produits", method = RequestMethod.GET)
    public Page<Produit> pageProduits( @RequestParam(name = "motcle",defaultValue = "")String mc,
                                       @RequestParam(name = "page",defaultValue = "0")int p,
                                       @RequestParam(name = "size",defaultValue = "8") int s){

        return pr.rechParMc("%"+mc+"%",new PageRequest(p,s));
    }
    @RequestMapping(value = "/getPhoto/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] photo(@PathVariable(value = "id") Long id ) throws Exception {

        File file = new File(dirImage+id);
        return IOUtils.toByteArray(new FileInputStream(file));
    }
}
