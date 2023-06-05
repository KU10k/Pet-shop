package com.ku10k.petshop.controller;

import com.ku10k.petshop.models.Product;
import com.ku10k.petshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String products(@RequestParam(value = "title", required = false) String searchRequest, Model model) {
        if (searchRequest == null) model.addAttribute("products", productService.getAll());
        else {
            model.addAttribute("title",searchRequest);
            model.addAttribute("products", productService.searchByTitle(searchRequest));
        }
        return "products";
    }

    @PostMapping
    public String save(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/";
    }


}

//   @GetMapping("/search")
//    public String getTitles(@RequestParam("title") String title, Model model) {
//        List<Product> productList = productService.findByTitle(title);
//        model.addAttribute("title",productList);
//        return "title";
//    }
