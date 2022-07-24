package com.intellias.marketplace.controllers;

import com.intellias.marketplace.models.Product;
import com.intellias.marketplace.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public String productMain(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("prod", products);

        return "products";
    }

}
