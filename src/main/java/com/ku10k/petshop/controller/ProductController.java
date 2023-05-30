package com.ku10k.petshop.controller;

import com.ku10k.petshop.models.Product;
import com.ku10k.petshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String products(Model model) {
        model.addAttribute("products", productService.getAll());
        return "products";
    }

    @PostMapping
    public String save(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/";
    }

   @GetMapping("/search")
    public String getTitles(@ModelAttribute("title") String title,Model model) {
        List<Product> productList = productService.findByTitle(title);
        model.addAttribute("title",productList);
        return "title";
    }
}


