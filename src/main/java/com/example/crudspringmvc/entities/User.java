package com.example.crudspringmvc.entities;

import org.hibernate.validator.constraints.Email;

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
    @Email(message = "email invalide")
    private String Email;
    private int active;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",joinColumns = { @JoinColumn(name = "user_id") },inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles;

    public User(String username, String password,String email) {
        Username = username;
        Password = password;
        Email = email;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
