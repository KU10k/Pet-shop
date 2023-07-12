package com.ku10k.petshop.controller;

import com.ku10k.petshop.controller.utils.ControllerUtils;
import com.ku10k.petshop.models.Product;
import com.ku10k.petshop.models.User;
import com.ku10k.petshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;



    @GetMapping("/")
    public String products(@RequestParam(required = false, defaultValue = "") String searchCity,
                           @RequestParam(required = false, defaultValue = "") String searchWord, Model model) {
        model.addAttribute("cities", ControllerUtils.getAllTowns());
        model.addAttribute("searchWord", searchWord);
        model.addAttribute("searchCity", searchCity);
        model.addAttribute("products", productService.getAllProducts(searchCity, searchWord));
        return "products";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable("id") Long id, Model model) {
        model.addAttribute(productService.getById(id));
        return "product-info";
    }
    @GetMapping("/my/products")
    public String myProducts(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("cities", ControllerUtils.getAllTowns());
        model.addAttribute("products", productService.getProductsByUserId(user.getId()));
        return "my-products";
    }

    @PostMapping("/")
    public String save(@AuthenticationPrincipal User user, @RequestParam("file") MultipartFile image, Product product) throws IOException {
        productService.save(user, product, image);
        return "redirect:/my/products";
    }

    @PostMapping("/product/delete/{id}")
    public String delete(@AuthenticationPrincipal User user, @PathVariable("id") Long id) {
        productService.delete(id, user);
        return "redirect:/my/products";
    }

}


