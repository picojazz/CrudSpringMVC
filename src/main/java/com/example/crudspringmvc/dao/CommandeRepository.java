package com.example.crudspringmvc.dao;

import com.example.crudspringmvc.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande,Long>{

}
