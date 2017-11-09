package com.example.crudspringmvc.entities;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
public class Commande {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String datecom;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @OneToMany(mappedBy = "commande",cascade = CascadeType.ALL)
    private List<LigneCommande> ligneCommandes;

    public Commande() {
    }

    public Commande( User user) {
        Date date1 = new Date();
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.datecom = simpleFormat.format(date1);
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatecom() {
        return datecom;
    }

    public void setDatecom(String datecom) {
        this.datecom = datecom;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
