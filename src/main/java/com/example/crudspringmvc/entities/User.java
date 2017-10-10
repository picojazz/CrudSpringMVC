package com.example.crudspringmvc.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Username;
    @Size(min = 3)
    private String Password;
    private int active;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",joinColumns = { @JoinColumn(name = "user_id") },inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles;

    public User(String username, String password) {
        Username = username;
        Password = password;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
