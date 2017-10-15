package com.example.crudspringmvc.dao;

import com.example.crudspringmvc.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProduitRepository extends JpaRepository<Produit,Long>{

    @Query(" select p from Produit p where p.designation like :x ")
    public Page<Produit> rechParMc(@Param("x")String mc , Pageable pageable);
    @Query("select p.id,p.designation,p.prix,p.quantite,p.categorie.name from Produit p")
    public List<Produit> produitRest();
}
