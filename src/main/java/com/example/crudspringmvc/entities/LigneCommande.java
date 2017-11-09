package com.example.crudspringmvc.entities;

import javax.persistence.*;

@Entity
public class LigneCommande {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_commande")
    private Commande commande;
    @ManyToOne
    @JoinColumn(name = "id_produit")
    private Produit produit;
    private int qte;

    public LigneCommande() {
    }

    public LigneCommande(Produit produit, int qte) {
        this.produit = produit;
        this.qte = qte;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}
