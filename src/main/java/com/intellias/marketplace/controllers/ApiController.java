package com.intellias.marketplace.controllers;

import com.intellias.marketplace.models.Product;
import com.intellias.marketplace.models.User;
import com.intellias.marketplace.repo.ProductRepository;
import com.intellias.marketplace.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.awt.color.ProfileDataException;
import java.util.List;

@Controller
public class ApiController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    private EntityManager entityManager;

    @GetMapping("/products")
    public String productMain(Model model) {
        Product prod = new Product();
        Iterable<Product> products = productRepository.findAll();
        Iterable<User> users = userRepository.findAll();
        for(Product pd: products){
           prod = pd;
           break;
        }
        for(User u: users){
            u.addProduct(prod);
        }

        model.addAttribute("products", products);
        model.addAttribute("users", users);

        return "products";
    }

    @PostMapping("/users/post")
    public String userPost(@RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam float money,
                              Model model)
    {
        User user = new User(firstName, lastName, money);
        userRepository.save(user);
        return "redirect:/products";
    }

    @GetMapping("/users/{id}/remove")
    public String userDelete(@PathVariable(value = "id") int id,
                                Model model)
    {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
        return "redirect:/products";
    }

    @PostMapping("/products/post")
    public String productPost(@RequestParam String name,
                              @RequestParam float price,
                              Model model)
    {
        Product product = new Product(name, price);
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}/remove")
    public String productDelete(@PathVariable(value = "id") int id,
                             Model model)
    {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
        return "redirect:/products";
    }
}
