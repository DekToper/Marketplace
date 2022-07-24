package com.intellias.marketplace.models;

public class Order {
    public User user;
    public Product product;

    public void setProduct(Product p)
    {
        this.product = p;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
