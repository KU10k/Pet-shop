package com.ku10k.petshop.controller;

import com.ku10k.petshop.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    private List<Product> productList = new ArrayList<>();


    @GetMapping("/")
    public String products(Model model) {
        model.addAttribute("products", productList);
        return "products";
    }

    @PostMapping("/create")
    public String createProduct(Product product) {
        productList.add(product);
        return "redirect:/";
    }
}


