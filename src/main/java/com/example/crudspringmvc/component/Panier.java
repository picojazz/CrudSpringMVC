package com.example.crudspringmvc.component;

import com.example.crudspringmvc.entities.LigneCommande;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Panier {
    private List<LigneCommande> listesProds;

    public Panier() {
        this.listesProds = new ArrayList<>();
    }

    public Panier(List<LigneCommande> listesProds) {
        this.listesProds = listesProds;
    }

    public List<LigneCommande> getListesProds() {
        return listesProds;
    }

    public void setListesProds(List<LigneCommande> listesProds) {
        this.listesProds = listesProds;
    }

    public int total(){
        int total = 0;
        for (LigneCommande l : listesProds ) {
            total += l.getProduit().getPrix()*l.getQte();

        }

        return total;
    }
}
