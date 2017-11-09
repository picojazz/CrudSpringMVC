package com.example.crudspringmvc;

import com.example.crudspringmvc.component.Counter;
import com.example.crudspringmvc.dao.*;
import com.example.crudspringmvc.entities.Categorie;
import com.example.crudspringmvc.entities.Commande;
import com.example.crudspringmvc.entities.Produit;
import com.example.crudspringmvc.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import sun.security.provider.MD5;

import java.util.List;


@SpringBootApplication
@EnableAutoConfiguration

public class CrudspringmvcApplication {


	public static void main(String[] args) {

		org.springframework.context.ApplicationContext ctx = SpringApplication.run(CrudspringmvcApplication.class, args);

			ProduitRepository pr = ctx.getBean(ProduitRepository.class);
			CategorieRepository cr = ctx.getBean(CategorieRepository.class);
			CommandeRepository comr = ctx.getBean(CommandeRepository.class);
			UserRepository ur = ctx.getBean(UserRepository.class);
			/*Long id = new Long(9);
			Commande com = new Commande(ur.findOne(id));
			comr.save(com);*/



			pr.findAll().forEach(p -> System.out.println(p.toString()));

	}
}
