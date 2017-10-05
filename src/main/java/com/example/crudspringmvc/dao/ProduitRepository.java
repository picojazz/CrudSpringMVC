package com.example.crudspringmvc.dao;

import com.example.crudspringmvc.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit,Long>{
}
