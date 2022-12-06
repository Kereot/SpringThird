package com.gb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    public String list(Model model) {
        List<Product> products = productRepository.getProducts();
        model.addAttribute("products", products);
        return "list";
    }

    @GetMapping("/list")
    public String addToList(
            Model model,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Float cost,
            @RequestParam(required = false) Integer idRem) {
        if (idRem != null) {
            productRepository.remove(idRem);
            return "redirect:/list";
        } else if (id != null && !title.isBlank() && cost != null) {
            productRepository.add(id, title, cost);
            return "redirect:/list";
        }
        return list(model);
    }
}