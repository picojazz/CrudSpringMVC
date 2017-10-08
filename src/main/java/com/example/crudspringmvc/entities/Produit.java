package com.example.crudspringmvc.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class Produit implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2,max = 20)
    @NotEmpty
    private String designation;
    @NotNull
    private int prix;
    @NotNull
    private int quantite;

    public Produit() {
    }

    public Produit( String designation, int prix, int quantite) {
        this.designation = designation;
        this.prix = prix;
        this.quantite = quantite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", designation='" + designation + '\'' +
                ", prix=" + prix +
                ", quantite=" + quantite +
                '}';
    }
}
