package com.intellias.marketplace.models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Product")
@Table(name = "product")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private float price;

    @ManyToMany(mappedBy = "products")
    @Nullable
    private List<User> users = new ArrayList<>();

    public Product(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public Product() {
    }

    public List<User> getUsers()
    {
        return this.users;
    }
    public void removeUser(User user) {users.remove(user);}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
