package com.example.crudspringmvc;

import com.example.crudspringmvc.dao.ProduitRepository;
import com.example.crudspringmvc.entities.Produit;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration
public class CrudspringmvcApplication {

	public static void main(String[] args) {

		org.springframework.context.ApplicationContext ctx = SpringApplication.run(CrudspringmvcApplication.class, args);
			ProduitRepository pr = ctx.getBean(ProduitRepository.class);
			//pr.save(new Produit("savon",200,35));
            //pr.save(new Produit("bic",100,25));

			pr.findAll().forEach(p -> System.out.println(p.toString()));
	}
}
