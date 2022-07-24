package com.intellias.marketplace.controllers;

import com.intellias.marketplace.models.Product;
import com.intellias.marketplace.models.User;
import com.intellias.marketplace.repo.ProductRepository;
import com.intellias.marketplace.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/products")
    public String productMain(Model model) {
        Iterable<Product> products = productRepository.findAll();
        Iterable<User> users = userRepository.findAll();

        model.addAttribute("products", products);
        model.addAttribute("users", users);

        return "products";
    }

}
