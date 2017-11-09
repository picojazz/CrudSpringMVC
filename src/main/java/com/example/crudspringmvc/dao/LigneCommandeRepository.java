package com.example.crudspringmvc.dao;

import com.example.crudspringmvc.entities.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande,Long>{
}
