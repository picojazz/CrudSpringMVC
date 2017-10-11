package com.example.crudspringmvc;

import com.example.crudspringmvc.dao.CategorieRepository;
import com.example.crudspringmvc.dao.ProduitRepository;
import com.example.crudspringmvc.entities.Categorie;
import com.example.crudspringmvc.entities.Produit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
@EnableAutoConfiguration
public class CrudspringmvcApplication {

	public static void main(String[] args) {

		org.springframework.context.ApplicationContext ctx = SpringApplication.run(CrudspringmvcApplication.class, args);
			ProduitRepository pr = ctx.getBean(ProduitRepository.class);
			CategorieRepository cr = ctx.getBean(CategorieRepository.class);
			Categorie maison = cr.findCategorieByCategorie("maison");
			Categorie ecole = cr.findCategorieByCategorie("ecole");
			pr.save(new Produit("savon",200,35,maison));
            pr.save(new Produit("bic",100,25,ecole));


			pr.findAll().forEach(p -> System.out.println(p.toString()));
	}
}
