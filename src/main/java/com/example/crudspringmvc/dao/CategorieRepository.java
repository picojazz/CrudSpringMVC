package com.example.crudspringmvc.dao;

import com.example.crudspringmvc.entities.Categorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Integer>{
    public Categorie findCategorieByCategorie(String categorie);
    @Query("select  c from Categorie c where c.categorie like :x")
    public Page<Categorie> rechParCat(@Param("x")String mc , Pageable pageable);
}
