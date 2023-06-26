package com.ku10k.petshop.controller;

import com.ku10k.petshop.models.Product;
import com.ku10k.petshop.service.ProductService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String products(@RequestParam(value = "title", required = false) String searchRequest, Model model) {
        if (searchRequest == null) model.addAttribute("products", productService.getAll());
        else {
            model.addAttribute("title", searchRequest);
            model.addAttribute("products", productService.searchByTitle(searchRequest));

        }
        return "products";
    }

    @GetMapping("/{id}")
    public String productInfo(@PathVariable("id") Long id, Model model) {
        model.addAttribute(productService.getById(id));
        return "product-info";

    }

    @PostMapping("/")
    public String save( Product product ,@RequestParam("image-file") MultipartFile image) throws IOException {
        productService.save(product, image);
        return "redirect:/";
    }

}

//   @GetMapping("/search")
//    public String getTitles(@RequestParam("title") String title, Model model) {
//        List<Product> productList = productService.findByTitle(title);
//        model.addAttribute("title",productList);
//        return "title";
//    }
