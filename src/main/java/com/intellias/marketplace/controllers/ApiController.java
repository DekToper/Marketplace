package com.intellias.marketplace.controllers;

import com.intellias.marketplace.models.Order;
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
import java.util.ArrayList;
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
        assert user.getProducts() != null;
        for (Product p: user.getProducts()) {
            p.removeUser(user);
        }
        userRepository.delete(user);
        return "redirect:/products";
    }

    @GetMapping("/myproducts/{id}")
    public String userDetail(@PathVariable(value = "id") int id,
                             Model model)
    {
        User user = userRepository.findById(id).orElseThrow();
        return "redirect:/myproducts/show/" + id;
    }

    @GetMapping("/myproducts/show/{id}")
    public String myProducts(@PathVariable(value = "id") int id,
                             Model model)
    {
        User user = userRepository.findById(id).orElseThrow();
        model.addAttribute("myProducts", user.getProducts());
        return "myproducts";
    }

    @GetMapping("/users/{id}")
    public String productDetail(@PathVariable(value = "id") int id,
                             Model model)
    {
        Product prod = productRepository.findById(id).orElseThrow();
        return "redirect:/users/show/" + id;
    }

    @GetMapping("/users/show/{id}")
    public String userList(@PathVariable(value = "id") int id,
                             Model model)
    {
        Product prod = productRepository.findById(id).orElseThrow();
        model.addAttribute("users", prod.getUsers());
        return "users";
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
        assert product.getUsers() != null;
        for (User user: product.getUsers()) {
            user.removeProduct(product);
        }
        productRepository.delete(product);
        return "redirect:/products";
    }
    @PostMapping("/order")
    public String userSelect(@RequestParam int userId,
                             @RequestParam int  prodId,
                             Model model)
    {
        System.out.println(userId);
        System.out.println(prodId);
        Product product = productRepository.findById(prodId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        user.setMoney(user.getMoney()-product.getPrice());
        System.out.println(prodId);
        System.out.println(userId);
        user.addProduct(product);
        userRepository.save(user);
        return "redirect:/products";
    }
}
